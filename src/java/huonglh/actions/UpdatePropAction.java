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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class UpdatePropAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id, name, description, quantities;
    private boolean available;
    private String lastSearchValue;

    public UpdatePropAction() {
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

    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "3", maxLength = "50", message = "Scene name requires 5-50 characters")
    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Scene Name can't be blank")
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

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        String image = "https://www.pngitem.com/pimgs/m/325-3256246_fa-fa-product-icon-transparent-cartoons-fa-fa.png";
        PropDTO prop = new PropDTO(id, name, image, description, Integer.parseInt(quantities), available);
        if (prop.getQuantities() > 0) {
            prop.setAvailable(true);
        }
        if (dao.updateProp(prop)) {
            url = SUCCESS;
        } else {
            request.setAttribute("ERROR", "Update Prop Failed");
            request.setAttribute("LINK", "ViewPropAction");
            request.setAttribute("LINKLABEL", "Back to View Props Page");
        }
        return url;
    }

}
