/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.DirectorDTO;
import java.util.List;

/**
 *
 * @author Hau Huong
 */
public class SearchDirectorAction extends ActionSupport {

    private List<DirectorDTO> listDirectors;
    private String searchValue;

    public SearchDirectorAction() {
    }

    public List<DirectorDTO> getListDirectors() {
        return listDirectors;
    }

    public void setListDirectors(List<DirectorDTO> listDirectors) {
        this.listDirectors = listDirectors;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        listDirectors = dao.findDirectorsByLikeName(searchValue);
        return "success";
    }

}
