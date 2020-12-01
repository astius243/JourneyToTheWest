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
import huonglh.dtos.RegistrationDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hau Huong
 */
public class ViewScheduleAction extends ActionSupport {

    public List<ActorSceneDTO> listSchedule;

    public ViewScheduleAction() {
    }

    public List<ActorSceneDTO> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<ActorSceneDTO> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        ActorDTO actor = dao.findActorByUsername(dto.getUsername());
        listSchedule = dao.getScheduleScene(actor.getId());
        return "view";
    }

}
