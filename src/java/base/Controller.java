/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

    protected Class model;
    protected static HttpServletRequest request;
    protected String defaultView = "index.jsp";
    protected String errorView = "error/404.jsp";

    protected void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/" + errorView);
        rd.forward(request, response);
    }

    protected void render(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        if ((view == null) || (view.trim() == "")) {
            render(request, response);
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/" + view);
        rd.forward(request, response);
    }

    public boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login_status") == "true") {
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        HttpSession session = Controller.request.getSession(true);
        if (session.getAttribute("login_status") == "true") {
            return true;
        }
        return false;
    }

    public String loggedInFacultyType() {
        if (!isLoggedIn()) {
            return null;
        }

        HttpSession session = Controller.request.getSession(true);
        return (String) session.getAttribute("faculty_type");
    }

    protected String getRequstApi() {
        return Controller.request.getRequestURI().replace(Controller.request.getContextPath() + "/api", "");
    }

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
