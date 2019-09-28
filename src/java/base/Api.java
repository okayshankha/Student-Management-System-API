/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.Controller;
import com.mysql.cj.x.protobuf.MysqlxConnection.Capability;
import com.tmsl.capability.AdminCapability;
import com.tmsl.capability.CommonCapability;
import com.tmsl.model.AuthModel;
import com.tmsl.pojo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 *
 * <pre>
 * 1. Path: /api/auth
 *    Post: a. (username or email) and  b. pass
 * {
 *   "response": {
 *       "user": {
 *           "username": "admin",
 *           "first_name": "Admin",
 *           "middle_name": "",
 *           "last_name": "",
 *           "email": "admin@admin.com",
 *           "password": "****",
 *           "mobile_number": "-1",
 *           "user_type": "1"
 *       },
 *       "status": "success"
 *   },
 *   "apipath": "/auth",
 *   "responsetime": "Thu Sep 26 21:33:22 IST 2019"
 * }
 *
 * </pre>
 *
 *
 *
 */
@WebServlet(name = "Api", urlPatterns = {"/api/*"})
public class Api extends Controller {

    @Override
    protected void processRequest(HttpServletRequest _request, HttpServletResponse response) throws ServletException, IOException {
        Controller.request = _request;
        boolean route = true;
        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        Object output = null;
        String requestApi = getRequstApi();
        String userLevel = loggedInUserType();
        /**
         * user level : 
         * 1 -> admin 2 -> hod 3 -> coordinator 4 -> teacher 5 -> student
         */

        if (!requestApi.equals("/auth")) {
            if (isLoggedIn()) {

                switch (userLevel) {
                    case "1": // Admin
                        output = admin(requestApi);
                        break;
                    case "2": // Hod
                        break;
                    case "3": // Coordinator
                        break;
                    case "4": // Teacher
                        break;
                    case "5": // Student
                        break;
                    default:
                        output = new AbstractMap.SimpleEntry<String, String>("status", "invalid user level");
                }
            } else {
                Map data = new HashMap<String, String>();
                data.put("status", "failed");
                data.put("info", "login required");
                output = data;
            }
        } else if (requestApi.equals("/auth")) {
            try {
                output = auth();
            } catch (SQLException ex) {
            }
        } else {
            Map data = new HashMap<String, String>();
            data.put("status", "failed");
            data.put("info", "invalid request");
            output = data;
        }

        
        
        
        
        
        /**
         * *****************************************************
         */

        PrintWriter out = response.getWriter();
        try {
            out.print(jsonOut(output));
        } finally {
            out.close();
        }

    }

    private Map<String, Object> auth() throws SQLException {
        if (isLoggedIn()) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "already logged in");
            return data;
        }

        AuthModel authModel = new AuthModel();

        User user = new User();
        String username = null;
        String email = null;
        String pass = null;

        authModel.validate(user);

        if (request.getMethod().equals("POST")) {
            username = request.getParameter("username");
            pass = request.getParameter("pass");
        }

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("status", "failed");

        if (username != null && pass != null) {
            String regex = "^(.+)@(.+)$"; //Email pattern

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(username);

            if (matcher.matches()) {
                email = username;
                username = null;
            }

            if (email != null) {
                user.setEmail(email);
            }
            if (username != null) {
                user.setUsername(username);
            }
            if (pass != null) {
                user.setPassword(pass);
            }

            try {
                user = authModel.getUser(user);

                if (user != null) {
                    output.put("status", "success");
                    HttpSession session = request.getSession();
                    session.setAttribute("login_status", "true");
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("user_type", user.getUser_type());
                    session.setAttribute("username", user.getUsername());
                }
            } catch (SQLException ex) {

            }
        }

        if (output.get("status").equals("success")) {
            user.setEmail("****");
            user.setPassword("****");
            user.setMobile_number("****");
            output.put("user", user);
        }

        return output;
    }

    private Map<String, Object> admin(String requestApi) {
        AdminCapability capability = new AdminCapability();
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("status", "invalid api path");
        switch (requestApi) {
            case "/all-coordinator":
                try {
                    output = capability.getCoordinator();
                } catch (SQLException ex) {}
                break;
            case "/all-hod":
                try {
                    output = capability.getHod();
                } catch (SQLException ex) {}
                break;
            case "/all-teacher":
                try {
                    output = capability.getTeacher();
                } catch (SQLException ex) {}
                break;
            case "/add-coordinator":
                try {
                    String sameAttr = capability.checkUserNonUniqueNess();
                    if(sameAttr != null){
                        output.put("status", "failed");
                        output.put("ununique", sameAttr);
                        output.put("info", sameAttr + " needs to be unique");
                    }else if(capability.setCoordinator()){
                        output.put("status", "success");
                    }else{
                        output.put("status", "failed");
                        output.put("info", "update error");
                    }
                } catch (Exception ex) {System.out.println(ex);}
                break;
            case "/add-hod":  //Done
                try {
                    String sameAttr = capability.checkUserNonUniqueNess();
                    if(sameAttr != null){
                        output.put("status", "failed");
                        output.put("ununique", sameAttr);
                        output.put("info", sameAttr + " needs to be unique");
                    }else if(capability.setHod()){
                        output.put("status", "success");
                    }else{
                        output.put("status", "failed");
                        output.put("info", "update error");
                    }
                } catch (Exception ex) {System.out.println(ex);}
                break;
            case "/delete-user":  //Done
                try {
                    boolean sameAttr = capability.doesExist();
                    if(sameAttr){
                        if(capability.deletetUser()){
                            output.put("status", "success");
                            output.put("info", "hod deleted");
                        }else{
                            output.put("status", "failed");
                            output.put("info", "failed to delete");
                        }
                    }else{
                        output.put("status", "failed");
                        output.put("info", "non-existing user");
                    }
                } catch (SQLException ex) {System.out.println(ex);}
                break;
            case "/add-teacher":
                try {
                    String sameAttr = capability.checkUserNonUniqueNess();
                    if(sameAttr != null){
                        output.put("status", "failed");
                        output.put("ununique", sameAttr);
                        output.put("info", sameAttr + " needs to be unique");
                    }else if(capability.setTeacher()){
                        output.put("status", "success");
                    }else{
                        output.put("status", "failed");
                        output.put("info", "update error");
                    }
                } catch (Exception ex) {System.out.println(ex);}
                break;
        }

        return output;
    }

}
