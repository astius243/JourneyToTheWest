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
public class DeletePropAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id;
    private String lastSearchValue;

    public DeletePropAction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (dao.deletePropSceneByPropID(id)) {
            if (dao.deleteProp(id)) {
                url = SUCCESS;
            }
            request.setAttribute("ERROR", "Delete Prop Failed");
            request.setAttribute("LINK", "SearchPropAction");
            request.setAttribute("LINKLABEL", "Back to View Props Page");
        } else {
            request.setAttribute("ERROR", "Delete Prop Failed");
            request.setAttribute("LINK", "SearchPropAction");
            request.setAttribute("LINKLABEL", "Back to View Props Page");
        }
        return url;
    }

}
