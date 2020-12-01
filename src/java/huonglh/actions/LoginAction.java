/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huonglh.daos.MainDAO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.UpdateDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class LoginAction extends ActionSupport {

    private static final String ADMIN = "admin";
    private static final String ACTOR = "actor";
    private static final String DIRECTOR = "director";
    private static final String INVALID = "invalid";

    private String username, password;

    public LoginAction() {
    }

    public String getUsername() {
        return username;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Username can't be blank")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Password can't be blank")
    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {
        String url = INVALID;
        MainDAO dao = new MainDAO();
        RegistrationDTO dto = dao.checkLogin(username, password);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (dto == null) {
            request.setAttribute("ERROR", "Invalid username or Password");
            request.setAttribute("LINK", "index.jsp");
            request.setAttribute("LINKLABEL", "Back to Login Page");
        } else {
            Map session = ActionContext.getContext().getSession();
            Map<String, List<UpdateDTO>> update = (Map<String, List<UpdateDTO>>) session.get("UPDATE");
            if (update == null) {
                update = new HashMap();
            }
            session.put("UPDATE", update);
            session.put("USER", dto);
            session.put("FULLNAME", dto.getFullname());
            if (dto.getRole().equals("admin")) {
                session.put("UPDATE", update);
                url = ADMIN;
            } else if (dto.getRole().equals("director")) {
                url = DIRECTOR;
            } else if (dto.getRole().equals("actor")) {
                url = ACTOR;
            } else {
                request.setAttribute("ERROR", "Your action is invalid");
                request.setAttribute("LINK", "index.jsp");
                request.setAttribute("LINKLABEL", "Back to Login Page");
            }
        }
        return url;
    }

}
