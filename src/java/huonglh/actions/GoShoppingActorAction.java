/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.ActorDTO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.SceneDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hau Huong
 */
public class GoShoppingActorAction extends ActionSupport {

    private Map<String, String> listActors;
    private Map<String, String> listScenes;

    public GoShoppingActorAction() {
        listActors = new HashMap<>();
        listScenes = new HashMap<>();
    }

    public Map<String, String> getListActors() {
        return listActors;
    }

    public void setListActors(Map<String, String> listActors) {
        this.listActors = listActors;
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
        List<ActorDTO> list1 = dao.getAllActors();
        List<SceneDTO> list2 = dao.getScenesOfDirector(director.getId());
        for (ActorDTO actor : list1) {
            getListActors().put(actor.getId(), actor.getFullname());
        }
        for (SceneDTO scene : list2) {
            getListScenes().put(scene.getId(), scene.getName());
        }
        return "view";
    }

}
