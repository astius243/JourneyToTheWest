/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
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
import huonglh.dtos.UpdateDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class UpdateDirectorAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id, username, password, confirm, fullname, phone, email;
    private String lastSearchValue;

    public UpdateDirectorAction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

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
    @EmailValidator(type = ValidatorType.FIELD, message = "Invalid Mail Format")
    public void setEmail(String email) {
        this.email = email;
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
        RegistrationDTO dto = new RegistrationDTO(username, fullname, "director");
        dto.setPassword(password);
        DirectorDTO director = new DirectorDTO(id, username, fullname, phone, email);
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        UpdateDTO updateDTO = null;
        if (dao.updateLogin(dto)) {
            if (dao.updateDirector(director)) {
                Map<String, List<UpdateDTO>> update = (Map<String, List<UpdateDTO>>) session.get("UPDATE");
                java.util.Date date = new java.util.Date(System.currentTimeMillis());
                updateDTO = new UpdateDTO(username, "You have been updated", date.toString());
                List<UpdateDTO> list = update.get(username);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(updateDTO);
                update.put(username, list);
                session.put("UPDATE", update);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Update Failed");
                request.setAttribute("LINK", "ViewDirectorAction");
                request.setAttribute("LINKLABEL", "Back to View Directions Page");
            }
        } else {
            request.setAttribute("ERROR", "Update Failed");
            request.setAttribute("LINK", "ViewDirectorAction");
            request.setAttribute("LINKLABEL", "Back to View Directions Page");
        }
        return url;
    }

}
