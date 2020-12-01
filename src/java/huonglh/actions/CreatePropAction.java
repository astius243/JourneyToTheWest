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
import huonglh.dtos.PropDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class CreatePropAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id, name, description, quantities;
    private boolean available;

    public CreatePropAction() {
    }

    public String getId() {
        return id;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Prop ID can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "P[0-9]{4}", message = "ID Format P[xxxx] - Example: P0001")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Prop Name can't be blank")
    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "3", maxLength = "50", message = "Username requires 5-20 characters")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantities() {
        return quantities;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Quantities can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "\\d{1,}", message = "Please input number")
    public void setQuantities(String quantities) {
        this.quantities = quantities;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        String image = "https://www.pngitem.com/pimgs/m/325-3256246_fa-fa-product-icon-transparent-cartoons-fa-fa.png";
        PropDTO prop = new PropDTO(id, name, image, description, Integer.parseInt(this.quantities), available);
        if(prop.getQuantities()>0){
            prop.setAvailable(true);
        }
        if (dao.insertProp(prop)) {
            url = SUCCESS;
        } else {
            request.setAttribute("ERROR", "Create Prop Failed");
            request.setAttribute("LINK", "admin.jsp");
            request.setAttribute("LINKLABEL", "Back to Admin Page");
        }
        return url;
    }

}
