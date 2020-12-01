package huonglh.actions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huonglh.daos.MainDAO;
import huonglh.dtos.ActorDTO;
import huonglh.dtos.ActorSceneDTO;
import huonglh.dtos.RegistrationDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hau Huong
 */
public class ViewFilmedSceneAction extends ActionSupport {

    private List<ActorSceneDTO> listScenes;

    public ViewFilmedSceneAction() {
    }

    public List<ActorSceneDTO> getListScenes() {
        return listScenes;
    }

    public void setListScenes(List<ActorSceneDTO> listScenes) {
        this.listScenes = listScenes;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        ActorDTO actor = dao.findActorByUsername(dto.getUsername());
        listScenes = dao.getFilmedScene(actor.getId());
        return "view";
    }

}
