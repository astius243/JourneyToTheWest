/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.DateRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huonglh.daos.MainDAO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.SceneDTO;
import huonglh.dtos.UpdateDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class UpdateSceneAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String INVALID = "input";

    private String id, name, location, description, dateFrom, dateTo, cuts, directorID, previousID;
    private int props;
    private String lastSearchValue;

    public UpdateSceneAction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "5", maxLength = "50", message = "Scene name requires 5-50 characters")
    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Scene Name can't be blank")
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Location can't be blank")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Date From can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}", message = "Invalid Format [YYYY-MM-DD]")
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Date To can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}", message = "Invalid Format [YYYY-MM-DD]")
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDirectorID() {
        return directorID;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Director ID can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "D[0-9]{4}", message = "ID Format D[xxxx] - Example: D0001")
    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public String getCuts() {
        return cuts;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Total Cuts can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "\\d{1,}", message = "Please input number")
    public void setCuts(String cuts) {
        this.cuts = cuts;
    }

    public int getProps() {
        return props;
    }

    public void setProps(int props) {
        this.props = props;
    }

    public String getPreviousID() {
        return previousID;
    }

    public void setPreviousID(String previousID) {
        this.previousID = previousID;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        SceneDTO scene = new SceneDTO(id, name, location, dateFrom, dateTo, description, Integer.parseInt(cuts), props, directorID);
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        if (Integer.parseInt(cuts) <= 0) {
            addFieldError("cuts", "Total Cuts must > 0");
            url = INVALID;
        } else {
            if (dao.updateScene(scene)) {
                DirectorDTO director = dao.findDirectorByPrimaryKey(previousID);
                Map<String, List<UpdateDTO>> update = (Map<String, List<UpdateDTO>>) session.get("UPDATE");
                java.util.Date date = new java.util.Date(System.currentTimeMillis());
                UpdateDTO updateDTO1 = new UpdateDTO(director.getUsername(), "", date.toString());
                UpdateDTO updateDTO2 = new UpdateDTO(director.getUsername(), "", date.toString());
                List<UpdateDTO> list1 = update.get(director.getUsername());
                if (list1 == null) {
                    list1 = new ArrayList<>();
                }
                if (!directorID.equals(previousID)) {
                    updateDTO1.setMessage(name + " has been changed to another director");
                    updateDTO2.setMessage("You are the director of " + name);
                    DirectorDTO nextDirector = dao.findDirectorByPrimaryKey(directorID);
                    List<UpdateDTO> list2 = update.get(nextDirector.getUsername());
                    if (list2 == null) {
                        list2 = new ArrayList<>();
                    }
                    list2.add(updateDTO2);
                    update.put(nextDirector.getUsername(), list2);
                } else {
                    updateDTO1.setMessage(name + " has been updated");
                }
                list1.add(updateDTO1);
                update.put(director.getUsername(), list1);
                session.put("UPDATE", update);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Updated Failed");
                request.setAttribute("LINK", "ViewSceneAction");
                request.setAttribute("LINKLABEL", "Back to View Scenes Page");
            }
        }
        return url;
    }

    @Override
    public void validate() {
        super.validate();
        try {
            MainDAO dao = new MainDAO();
            if (dao.findDirectorByPrimaryKey(directorID) == null) {
                addFieldError("directorID", "Director ID is unavailable");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(dateFrom);
            Date date2 = sdf.parse(dateTo);
            if (date2.compareTo(date1) < 0) {
                addFieldError("dateTo", "Date To must after Date From");
            }
        } catch (Exception e) {
        }
    }

}
