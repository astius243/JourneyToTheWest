/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huonglh.daos.MainDAO;
import huonglh.dtos.ActorDTO;
import huonglh.dtos.ActorSceneDTO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.RegistrationDTO;
import huonglh.dtos.SceneDTO;
import huonglh.models.ShoppingActorCart;
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
public class AddActorCartAction extends ActionSupport {

    private String sceneID, actorID, role;
    private Map<String, String> listActors;
    private Map<String, String> listScenes;

    public AddActorCartAction() {
        listActors = new HashMap<>();
        listScenes = new HashMap<>();
    }

    public Map<String, String> getListActors() throws Exception {
        MainDAO dao = new MainDAO();
        List<ActorDTO> list = dao.getAllActors();
        for (ActorDTO actor : list) {
            listActors.put(actor.getId(), actor.getFullname());
        }
        return listActors;
    }

    public void setListActors(Map<String, String> listActors) {
        this.listActors = listActors;
    }

    public Map<String, String> getListScenes() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        DirectorDTO director = dao.findDirectorByUsername(dto.getUsername());
        List<SceneDTO> list = dao.getScenesOfDirector(director.getId());
        for (SceneDTO scene : list) {
            listScenes.put(scene.getId(), scene.getName());
        }
        return listScenes;
    }

    public void setListScenes(Map<String, String> listScenes) {
        this.listScenes = listScenes;
    }

    public String getSceneID() {
        return sceneID;
    }

    public void setSceneID(String sceneID) {
        this.sceneID = sceneID;
    }

    public String getActorID() {
        return actorID;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
    }

    public String getRole() {
        return role;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Role can't be blank")
    public void setRole(String role) {
        this.role = role;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        ShoppingActorCart actorCart = (ShoppingActorCart) session.get("ACTORCART");
        if (actorCart == null) {
            actorCart = new ShoppingActorCart();
        }
        SceneDTO scene = dao.findSceneByPrimaryKey(sceneID);
        ActorDTO actor = dao.findActorByPrimaryKey(actorID);
        String existedActor = dao.findRoleByPrimaryKey(sceneID, actorID);
        Iterator<String> keySet = actorCart.getCart().keySet().iterator();
        boolean checkRole = false;
        boolean checkExisted = false;
        //Check Cart
        while (keySet.hasNext()) {
            String key = keySet.next();
            if (role.equals(actorCart.getCart().get(key).getRole()) && sceneID.equals(actorCart.getCart().get(key).getScene().getId())) {
                checkRole = true;
            }
            if (sceneID.equals(actorCart.getCart().get(key).getScene().getId()) && actorID.equals(actorCart.getCart().get(key).getActor().getId())) {
                checkExisted = true;
            }
        }
        if (existedActor == null) {
            if (!checkExisted) {
                if (!checkRole) {
                    if (dao.findActorByRole(sceneID, role) == null) {
                        ActorSceneDTO actorSceneDTO = new ActorSceneDTO(sceneID + actorID, role);
                        actorSceneDTO.setScene(scene);
                        actorSceneDTO.setActor(actor);
                        actorCart.addToCart(actorSceneDTO);
                    } else {
                        request.setAttribute("EXISTEDROLE", "This role of this scene has been taken");
                    }
                } else {
                    request.setAttribute("EXISTEDROLEINCART", "This role of this scene has been added to cart");
                }
            } else {
                request.setAttribute("EXISTEDINCART", "This actor attending this scene has been added to cart");
            }
        } else {
            request.setAttribute("EXISTED", "This actor has attended this scene");
        }
        session.put("ACTORCART", actorCart);
        return "success";
    }

}
