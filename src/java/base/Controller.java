/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 */
@WebServlet(name = "Controller", urlPatterns = {"/s"})
public class Controller extends HttpServlet {

    /**
     *  User specified file location for file and image storage.
     */
    public static String USER_UPLOAD_DIR = "D";

    /**
     *  Static HttpServletRequest object for extended use.
     */
    protected static HttpServletRequest request;

    /**
     *  Default view (Not Applicable)
     */
    protected String defaultView = "index.jsp";

    /**
     *  Error view (Not Applicable)
     */
    protected String errorView = "error/404.jsp";

    /**
     *
     * View render function
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/" + errorView);
        rd.forward(request, response);
    }

    /**
     * View render function
     * @param request
     * @param response
     * @param view
     * @throws ServletException
     * @throws IOException
     */
    protected void render(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        if ((view == null) || (view.trim().equals(""))) {
            render(request, response);
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/" + view);
        rd.forward(request, response);
    }


    /**
     * Login checker.
     * @return
     */
    public boolean isLoggedIn() {
        HttpSession session = Controller.request.getSession(true);
        if (session.getAttribute("login_status") == "true") {
            return true;
        }
        return false;
    }

    /**
     * Find the logged in Faculty Type (Admin/HOD/Coordinator/Teacher/Student)
     * @return
     */
    public String loggedInFacultyType() {
        if (!isLoggedIn()) {
            return null;
        }

        HttpSession session = Controller.request.getSession(true);
        return (String) session.getAttribute("faculty_type");
    }

    /**
     * Returns the API context path.
     * @return
     */
    protected String getRequstApi() {
        return Controller.request.getRequestURI().replace(Controller.request.getContextPath() + "/api", "");
    }

    /**
     * Convert object to JSON (using Gson API)
     * @param obj
     * @return
     */
    public String jsonOut(Object obj) {
        Map<String, Object> output = new HashMap<>();
        output.put("response", obj);

        Date date = new Date();
        output.put("responsetime", date.toString());
        output.put("apipath", getRequstApi());

        Gson gson = new Gson();
        String json = gson.toJson(output);
        return json;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        render(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Default Controller";
    }

    /**
     * Finds the HTTP <code>POST</code> parameters.
     * @param name
     * @return
     */
    public String gPost(String name) {
        String s = "";
        if (request.getMethod().equals("POST")) {
            s = request.getParameter(name);
            switch (name) {
                case "pass":
                    s = (s != null) ? s : "Default";
                    break;
                default:
                    //System.out.println("Post : " + name + " : " + s);
                    s = (s != null) ? s : "";
            }
            return s;
        }
        return s;
    }

    /**
     * Finds the HTTP <code>GET</code> parameters.
     * @param name
     * @return
     */
    public String gGet(String name) {
        String s = "";
        if (request.getMethod().equals("GET")) {
            s = request.getParameter(name);
            switch (name) {
                case "pass":
                    s = (s != null) ? s : "Default";
                    break;
                default:
                    //System.out.println("Post : " + name + " : " + s);
                    s = (s != null) ? s : "";
            }
            return s;
        }
        return s;
    }



}
