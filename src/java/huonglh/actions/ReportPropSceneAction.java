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
import huonglh.dtos.PropCartDTO;
import huonglh.dtos.RegistrationDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hau Huong
 */
public class ReportPropSceneAction extends ActionSupport {

    private List<PropCartDTO> listProps;

    public ReportPropSceneAction() {
    }

    public List<PropCartDTO> getListProps() {
        return listProps;
    }

    public void setListProps(List<PropCartDTO> listProps) {
        this.listProps = listProps;
    }

    public String execute() throws Exception {
        MainDAO dao = new MainDAO();
        Map session = ActionContext.getContext().getSession();
        RegistrationDTO dto = (RegistrationDTO) session.get("USER");
        DirectorDTO director = dao.findDirectorByUsername(dto.getUsername());
        listProps = dao.getAllPropCart(director.getId());
        return "view";
    }

}
