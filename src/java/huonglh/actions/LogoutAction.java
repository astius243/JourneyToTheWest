/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author Hau Huong
 */
public class LogoutAction extends ActionSupport {

    private static final String SUCCESS = "success";

    public LogoutAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("USER");
        session.remove("PROPCART");
        session.remove("ACTORCART");
        session.remove("FULLNAME");
        return SUCCESS;
    }

}
