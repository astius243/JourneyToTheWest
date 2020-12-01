/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huonglh.daos.MainDAO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.PropDTO;
import huonglh.dtos.PropSceneDTO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.SceneDTO;
import huonglh.models.ShoppingPropCart;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class AddPropCartAction extends ActionSupport {

    private String sceneID, propID, dateFrom, dateTo;
    private Map<String, String> listProps;
    private Map<String, String> listScenes;

    public AddPropCartAction() {
        listProps = new HashMap<>();
        listScenes = new HashMap<>();
    }

    public Map<String, String> getListProps() throws Exception {
        MainDAO dao = new MainDAO();
        List<PropDTO> list = dao.getAllProps();
        for (PropDTO prop : list) {
            listProps.put(prop.getId(), prop.getName());
        }
        return listProps;
    }

    public void setListProps(Map<String, String> listProps) {
        this.listProps = listProps;
    }

    public Map<String, String> getListScenes() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        DirectorDTO director = dao.findDirectorByUsername(dto.getUsername());
        List<SceneDTO> list = dao.getScenesOfDirector(director.getId());
        for (SceneDTO scene : list) {
            listScenes.put(scene.getId(), scene.getName());
        }
        return listScenes;
    }

    public void setListScenes(Map<String, String> listScenes) {
        this.listScenes = listScenes;
    }

    public String getSceneID() {
        return sceneID;
    }

    public void setSceneID(String sceneID) {
        this.sceneID = sceneID;
    }

    public String getPropID() {
        return propID;
    }

    public void setPropID(String propID) {
        this.propID = propID;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Date From can't be blank")
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Date From can't be blank")
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        ShoppingPropCart propCart = (ShoppingPropCart) session.get("PROPCART");
        HttpServletRequest request = ServletActionContext.getRequest();
        if (propCart == null) {
            propCart = new ShoppingPropCart();
        }
        SceneDTO scene = dao.findSceneByPrimaryKey(sceneID);
        PropDTO prop = dao.findPropByPrimaryKey(propID);
        PropSceneDTO propSceneDTO = new PropSceneDTO(sceneID + propID, prop, scene, dateFrom, dateTo);
        if ((propCart.getQuantities(propSceneDTO.getId()) < prop.getQuantities()) && prop.isAvailable()) {
            propSceneDTO.setQuantities(1);
            propCart.addToCart(propSceneDTO);
        } else {
            request.setAttribute("FULL", "Out of Stock");
        }
        session.put("PROPCART", propCart);
        return "success";
    }

    @Override
    public void validate() {
        super.validate();
        try {
            MainDAO dao = new MainDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(dateFrom);
            Date date2 = sdf.parse(dateTo);
            Date currentDate = new java.sql.Date(System.currentTimeMillis());
            SceneDTO scene = dao.findSceneByPrimaryKey(sceneID);
            Date endDate = sdf.parse(scene.getDateTo());
            if (date1.compareTo(endDate) > 0) {
                addFieldError("dateFrom", "Date From must before End Date of Scene");
            }
            if (date2.compareTo(endDate) > 0) {
                addFieldError("dateTo", "Date To must before End Date of Scene");
            }
            if (date2.compareTo(date1) < 0) {
                addFieldError("dateTo", "Date To must after Date From");
            }
        } catch (Exception e) {
        }
    }
}
