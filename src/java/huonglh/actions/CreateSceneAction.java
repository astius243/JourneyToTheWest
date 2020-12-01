/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huonglh.daos.MainDAO;
import huonglh.dtos.SceneDTO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class CreateSceneAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String INVALID = "input";

    private String id, name, location, description, dateFrom, dateTo, cuts, directorID;
    private int props;

    public CreateSceneAction() {
    }

    public String getId() {
        return id;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Scene ID can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "S[0-9]{4}", message = "ID Format S[xxxx] - Example: S0001")
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

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Date From can't be blank")
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

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        if (Integer.parseInt(cuts) <= 0) {
            addFieldError("cuts", "Total Cuts must > 0");
            url = INVALID;
        }
        if (dao.findDirectorByPrimaryKey(directorID) == null) {
            addFieldError("directorID", "Director ID is unavailable");
            url = INVALID;
        } else {
            SceneDTO scene = new SceneDTO(id, name, location, dateFrom, dateTo, description, Integer.parseInt(cuts), props, directorID);
            if (dao.insertScene(scene)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Create Scene Failed");
                request.setAttribute("LINK", "admin.jsp");
                request.setAttribute("LINKNAME", "Back to Admin Page");
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
            Date currentDate = new java.sql.Date(System.currentTimeMillis());
            if (date2.compareTo(date1) < 0) {
                addFieldError("dateTo", "Date To must after Date From");
            }
        } catch (Exception e) {
        }
    }

}
