/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huonglh.daos.MainDAO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.RegistrationDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class CreateDirectorAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id, username, password, confirm, fullname, phone, email;

    public CreateDirectorAction() {
    }

    public String getId() {
        return id;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Director ID can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "D[0-9]{4}", message = "ID Format D[xxxx] - Example: D0001")
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Username can't be blank")
    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "5", maxLength = "20", message = "Username requires 5-20 characters")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Password can't be blank")
    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "8", maxLength = "20", message = "Password requires 8-20 characters")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    @FieldExpressionValidator(expression = "confirm==password", message = "Confirm must match Password")
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getFullname() {
        return fullname;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Fullname can't be blank")
    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "5", maxLength = "50", message = "Fullname requires more then 5 characters")
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Phone can't be blank")
    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "\\d{3}-\\d{7}", message = "Format Phone [012-1234567]")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Email can't be blank")
    @EmailValidator(type = ValidatorType.FIELD, message = "Invalid format mail")
    public void setEmail(String email) {
        this.email = email;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        RegistrationDTO dto = new RegistrationDTO(username, fullname, "director");
        dto.setPassword(password);
        DirectorDTO director = new DirectorDTO(id, username, fullname, phone, email);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (dao.insertLogin(dto)) {
            if (dao.insertDirector(director)) {
                url = SUCCESS;
            }
        } else {
            request.setAttribute("ERROR", "Create Director Failed");
            request.setAttribute("LINK", "admin.jsp");
            request.setAttribute("LINKLABEL", "Back to Admin Page");
        }
        return url;
    }

}
