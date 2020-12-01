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
import huonglh.dtos.ActorSceneDTO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.SceneDTO;
import huonglh.dtos.UpdateDTO;
import huonglh.models.ShoppingActorCart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Hau Huong
 */
public class InsertActorCartAction extends ActionSupport {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private Map<String, String> listActors;
    private Map<String, String> listScenes;

    public InsertActorCartAction() {
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
        String url = ERROR;
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
        HttpServletRequest request = ServletActionContext.getRequest();
        ShoppingActorCart actorCart = (ShoppingActorCart) session.get("ACTORCART");
        if (actorCart != null) {
            List<ActorSceneDTO> list = new ArrayList<>();
            Iterator<String> keySet = actorCart.getCart().keySet().iterator();
            while (keySet.hasNext()) {
                String key = keySet.next();
                ActorSceneDTO actorSceneDto = actorCart.getCart().get(key);
                actorSceneDto.setScript("text.txt");
                list.add(actorSceneDto);
            }
            if (dao.insertActorScene(list)) {
                actorCart.getCart().clear();
                url = SUCCESS;
                Map<String, List<UpdateDTO>> update = (Map<String, List<UpdateDTO>>) session.get("UPDATE");
                List<UpdateDTO> listRole = null;
                UpdateDTO updateDTO = null;
                for (ActorSceneDTO asDTO : list) {
                    System.out.println(asDTO.getActor().getUsername());
                    listRole = update.get(asDTO.getActor().getUsername());
                    java.util.Date date = new java.util.Date(System.currentTimeMillis());
                    if (listRole == null) {
                        listRole = new ArrayList<>();
                    }
                    updateDTO = new UpdateDTO(asDTO.getActor().getUsername(), "You have been added to role " + asDTO.getRole() + " of scene "
                            + asDTO.getScene().getName(), date.toString());
                    listRole.add(updateDTO);
                    update.put(asDTO.getActor().getUsername(), listRole);
                }
                session.put("UPDATE", update);
            }
        } else {
            request.setAttribute("ERROR", "Insert Failed");
            request.setAttribute("LINK", "director.jsp");
            request.setAttribute("LINKLABEL", "Back to Director Page");
        }
        session.put("ACTORCART", actorCart);
        return url;
    }

}
