/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.PropDTO;
import java.util.List;

/**
 *
 * @author Hau Huong
 */
public class SearchPropAction extends ActionSupport {

    private List<PropDTO> listProps;
    private String searchValue;

    public SearchPropAction() {
    }

    public List<PropDTO> getListProps() {
        return listProps;
    }

    public void setListProps(List<PropDTO> listProps) {
        this.listProps = listProps;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        listProps = dao.findPropsByLikeName(searchValue);
        return "success";
    }

}
