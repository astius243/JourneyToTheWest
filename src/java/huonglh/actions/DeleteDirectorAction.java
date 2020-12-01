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
public class DeleteDirectorAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id, username;
    private String lastSearchValue;

    public DeleteDirectorAction() {
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

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        if (dao.checkScene(id).size() > 0) {
            request.setAttribute("ERROR", "Delete Failed - There're actors attending scene");
            request.setAttribute("LINK", "SearchDirectorAction");
            request.setAttribute("LINKLABEL", "Back to View Directions Page");
        } else {
            if (dao.deleteAllScene(id)) {
                if (dao.deleteDirector(id)) {
                    if (dao.deleteLogin(username)) {
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("ERROR", "Delete Failed");
                request.setAttribute("LINK", "SearchDirectorAction");
                request.setAttribute("LINKLABEL", "Back to View Directions Page");
            }
        }
        return url;
    }
}
