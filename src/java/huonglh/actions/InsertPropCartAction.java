/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.PropDTO;
import huonglh.dtos.PropSceneDTO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.SceneDTO;
import huonglh.models.ShoppingPropCart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class InsertPropCartAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private Map<String, String> listProps;
    private Map<String, String> listScenes;

    public InsertPropCartAction() {
        listProps = new HashMap<>();
        listScenes = new HashMap<>();
    }

    public Map<String, String> getListProps() {
        return listProps;
    }

    public void setListProps(Map<String, String> listProps) {
        this.listProps = listProps;
    }

    public Map<String, String> getListScenes() {
        return listScenes;
    }

    public void setListScenes(Map<String, String> listScenes) {
        this.listScenes = listScenes;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        DirectorDTO director = dao.findDirectorByUsername(dto.getUsername());
        List<PropDTO> list1 = dao.getAllProps();
        List<SceneDTO> list2 = dao.getScenesOfDirector(director.getId());
        for (PropDTO prop : list1) {
            getListProps().put(prop.getId(), prop.getName());
        }
        for (SceneDTO scene : list2) {
            getListScenes().put(scene.getId(), scene.getName());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        ShoppingPropCart propCart = (ShoppingPropCart) session.get("PROPCART");
        if (propCart != null) {
            List<PropSceneDTO> listPropScene = new ArrayList<>();
            List<PropDTO> listProp = new ArrayList<>();
            Iterator<String> keySet = propCart.getCart().keySet().iterator();
            PropSceneDTO propSceneDTO = null;
            PropDTO propDTO = null;
            while (keySet.hasNext()) {
                String key = keySet.next();
                propSceneDTO = propCart.getCart().get(key);
                propDTO = dao.findPropByPrimaryKey(propSceneDTO.getProp().getId());
                propDTO.setQuantities(propDTO.getQuantities() - propSceneDTO.getQuantities());
                if(propDTO.getQuantities()==0){
                    propDTO.setAvailable(false);
                }
                listPropScene.add(propSceneDTO);
                listProp.add(propDTO);
            }
            if (dao.updateListProp(listProp)) {
                if (dao.insertPropScene(listPropScene)) {
                    propCart.getCart().clear();
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Update List Prop Failed");
                    request.setAttribute("LINK", "viewPropCart");
                    request.setAttribute("LINKLABEL", "View Prop Cart");
                }
            }
        } else {
            request.setAttribute("ERROR", "Insert Failed");
            request.setAttribute("LINK", "director.jsp");
            request.setAttribute("LINKLABEL", "Back to Director Page");
        }
        session.put("PROPCART", propCart);
        return url;
    }

}
