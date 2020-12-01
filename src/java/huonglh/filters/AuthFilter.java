/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.filters;

import huonglh.dtos.RegistrationDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau Huong
 */
public class AuthFilter implements Filter {
    
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;
    
    private final List<String> admin;
    private final List<String> director;
    private final List<String> actor;
    private final List<String> guest;
    
    public AuthFilter() {
        admin = new ArrayList<>();
        admin.add("admin.jsp");
        admin.add("error.jsp");
        admin.add("createActor.jsp");
        admin.add("createDirector.jsp");
        admin.add("createProp.jsp");
        admin.add("createScene.jsp");
        admin.add("updateActor.jsp");
        admin.add("updateDirector.jsp");
        admin.add("updateProp.jsp");
        admin.add("updateScene.jsp");
        admin.add("viewActor.jsp");
        admin.add("viewDirector.jsp");
        admin.add("viewScene.jsp");
        admin.add("viewProp.jsp");
        admin.add("LogoutAction");
        admin.add("LogoutAction.action");
        admin.add("CreateActorAction.action");
        admin.add("CreateDirectorAction.action");
        admin.add("CreatePropAction.action");
        admin.add("CreateSceneAction.action");
        admin.add("DeleteActorAction.action");
        admin.add("DeleteDirectorAction.action");
        admin.add("DeletePropAction.action");
        admin.add("DeleteSceneAction.action");
        admin.add("DeleteLoginAction.action");
        admin.add("EditActorAction.action");
        admin.add("EditDirectorAction.action");
        admin.add("EditPropAction.action");
        admin.add("EditSceneAction.action");
        admin.add("UpdateActorAction.action");
        admin.add("UpdateDirectorAction.action");
        admin.add("UpdatePropAction.action");
        admin.add("UpdateSceneAction.action");
        admin.add("SearchActorAction.action");
        admin.add("SearchDirectorAction.action");
        admin.add("SearchPropAction.action");
        admin.add("SearchSceneAction.action");
        admin.add("CreateActorAction");
        admin.add("CreateDirectorAction");
        admin.add("CreatePropAction");
        admin.add("CreateSceneAction");
        admin.add("DeleteActorAction");
        admin.add("DeleteDirectorAction");
        admin.add("DeletePropAction");
        admin.add("DeleteSceneAction");
        admin.add("DeleteLoginAction");
        admin.add("EditActorAction");
        admin.add("EditDirectorAction");
        admin.add("EditPropAction");
        admin.add("EditSceneAction");
        admin.add("UpdateActorAction");
        admin.add("UpdateDirectorAction");
        admin.add("UpdatePropAction");
        admin.add("UpdateSceneAction");
        admin.add("SearchActorAction");
        admin.add("SearchDirectorAction");
        admin.add("SearchPropAction");
        admin.add("SearchSceneAction");
        admin.add("styles.css");
        
        guest = new ArrayList<>();
        guest.add("index.jsp");
        guest.add("invalid.jsp");
        guest.add("pageNotFound.jsp");
        guest.add("");
        guest.add("LoginAction");
        guest.add("LoginAction.action");
        guest.add("''");
        guest.add("styles.css");
        
        director = new ArrayList<>();
        director.add("director.jsp");
        director.add("error.jsp");
        director.add("goShoppingProp.jsp");
        director.add("goShoppingActor.jsp");
        director.add("viewPropCart.jsp");
        director.add("viewActorCart.jsp");
        director.add("viewReport.jsp");
        director.add("GoShoppingPropAction");
        director.add("GoShoppingPropAction.action");
        director.add("GoShoppingActorAction");
        director.add("GoShoppingActorAction.action");
        director.add("AddPropCartAction");
        director.add("AddPropCartAction.action");
        director.add("AddActorCartAction");
        director.add("AddActorCartAction.action");
        director.add("InsertPropCartAction");
        director.add("InsertPropCartAction.action");
        director.add("InsertActorCartAction");
        director.add("InsertActorCartAction.action");
        director.add("ReportPropSceneAction");
        director.add("ReportPropSceneAction.action");
        director.add("LogoutAction");
        director.add("LogoutAction.action");
        director.add("styles.css");
        
        actor = new ArrayList<>();
        actor.add("actor.jsp");
        actor.add("error.jsp");
        actor.add("viewSchedule.jsp");
        actor.add("viewFilmedScene.jsp");
        actor.add("ViewScheduleAction");
        actor.add("ViewScheduleAction.action");
        actor.add("ViewFilmedSceneAction");
        actor.add("ViewFilmedSceneAction.action");
        actor.add("DownloadFileAction");
        actor.add("DownloadFileAction.action");
        actor.add("downloadFile");
        actor.add("LogoutAction");
        actor.add("LogoutAction.action");
    }
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String[] resources = uri.substring(index + 1).split(";");
        String resource = resources[0];
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("USER") == null) {
            if (guest.contains(resource)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("pageNotFound.jsp");
            }
        } else {
            RegistrationDTO dto = (RegistrationDTO) session.getAttribute("USER");
            if (dto != null) {
                if (dto.getRole().equals("admin")) {
                    if (admin.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("admin.jsp");
                    }
                } else if (dto.getRole().equals("director")) {
                    if (director.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("director.jsp");
                    }
                } else if (dto.getRole().equals("actor")) {
                    if (actor.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("actor.jsp");
                    }
                }
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
    
}
