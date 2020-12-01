/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.DirectorDTO;
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
public class DeleteSceneAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private String id;
    private String lastSearchValue, directorID;

    public DeleteSceneAction() {
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

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public String execute() throws Exception {
        String url = ERROR;
        MainDAO dao = new MainDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        if (dao.deletePropSceneBySceneID(id)) {
            if (dao.deleteActorSceneBySceneID(id)) {
                if (dao.deleteScene(id)) {
                    Map session = ActionContext.getContext().getSession();
                    Map<String, List<UpdateDTO>> update = (Map) session.get("UPDATE");
                    DirectorDTO director = dao.findDirectorByPrimaryKey(directorID);
                    java.util.Date date = new java.util.Date(System.currentTimeMillis());
                    UpdateDTO dto = new UpdateDTO(director.getUsername(), id + " has been deleted", date.toString());
                    List<UpdateDTO> list = update.get(director.getUsername());
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                    update.put(director.getUsername(), list);
                    session.put("UPDATE", update);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Delete Scene Failed");
                    request.setAttribute("LINK", "SearchSceneAction");
                    request.setAttribute("LINKLABEL", "Back to View Scenes Page");
                }
            } else {
                request.setAttribute("ERROR", "Delete Scene Failed");
                request.setAttribute("LINK", "SearchSceneAction");
                request.setAttribute("LINKLABEL", "Back to View Scenes Page");
            }
        } else {
            request.setAttribute("ERROR", "Delete Scene Failed");
            request.setAttribute("LINK", "SearchSceneAction");
            request.setAttribute("LINKLABEL", "Back to View Scenes Page");
        }
        return url;
    }

}
