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
import huonglh.dtos.PropDTO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.SceneDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hau Huong
 */
public class GoShoppingPropAction extends ActionSupport {
    
    private Map<String, String> listProps;
    private Map<String, String> listScenes;
    
    public GoShoppingPropAction() {
        listProps = new HashMap<>();
        listScenes = new HashMap<>();
    }
    
    public Map<String, String> getListProps() {
        return listProps;
    }
    
    public void setListProps(Map<String, String> listProps) {
        this.listProps = listProps;
    }
    
    public Map<String, String> getListScenes() {
        return listScenes;
    }
    
    public void setListScenes(Map<String, String> listScenes) {
        this.listScenes = listScenes;
    }
    
    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        DirectorDTO director = dao.findDirectorByUsername(dto.getUsername());
        List<PropDTO> list1 = dao.getAllProps();
        List<SceneDTO> list2 = dao.getScenesOfDirector(director.getId());
        for (PropDTO prop : list1) {
            getListProps().put(prop.getId(), prop.getName());
        }
        for (SceneDTO scene : list2) {
            getListScenes().put(scene.getId(), scene.getName());
        }
        return "view";
    }
    
}
