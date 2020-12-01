/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.SceneDTO;
import java.util.List;

/**
 *
 * @author Hau Huong
 */
public class SearchSceneAction extends ActionSupport {

    private List<SceneDTO> listScenes;
    private String searchValue;

    public SearchSceneAction() {
    }

    public List<SceneDTO> getListScenes() {
        return listScenes;
    }

    public void setListScenes(List<SceneDTO> listScenes) {
        this.listScenes = listScenes;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        listScenes = dao.findScenesByLikeName(searchValue);
        return "success";
    }

}
