/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.ActorDTO;
import java.util.List;

/**
 *
 * @author Hau Huong
 */
public class SearchActorAction extends ActionSupport {

    private List<ActorDTO> listActors;
    private String searchValue;

    public SearchActorAction() {
    }

    public List<ActorDTO> getListActors() {
        return listActors;
    }

    public void setListActor(List<ActorDTO> listActors) {
        this.listActors = listActors;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        listActors = dao.findActorsByLikeName(searchValue);
        return "success";
    }

}
