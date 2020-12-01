/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class DeleteLoginAction extends ActionSupport {

    private String username, link;

    public DeleteLoginAction() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        if (dao.deleteLogin(username)) {
            url = SUCCESS;
        } else {
            request.setAttribute("ERROR", "Delete Login Failed");
            request.setAttribute("LINK", "admin.jsp");
            request.setAttribute("LINKLABEL", "Back to Admin Page");
        }
        return url;
    }
}
