/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
import com.google.gson.Gson;
import com.tmsl.model.AdminModel;
import com.tmsl.pojo.Faculty;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shankha
 */
public class AdminCapability extends CommonCapability {

    AdminModel adminModel = new AdminModel();

    public Map<String, Object> getHod() throws SQLException {
        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            return getFacultysByType("2");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

    public Map<String, Object> getCoordinator(String email) throws SQLException {
        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            return getFacultysByType("3");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

    public Map<String, Object> getTeacher() throws SQLException {
        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            return getFacultysByType("4");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

    public boolean addHod() throws SQLException {
        boolean status = false;

        if (!isLoggedIn()) {
            return false;
        } else {
            Faculty user = compileFacultyPostData();

            user.setFaculty_type("2");

            status = adminModel.addFaculty(user);
            return status;
        }
    }

    public boolean addCoordinator() throws SQLException {
        boolean status = false;
        if (!isLoggedIn()) {
            return false;
        } else {
            Faculty user = compileFacultyPostData();
            user.setFaculty_type("3");

            status = adminModel.addFaculty(user);
            return status;
        }
    }

    public boolean addTeacher() throws SQLException {
        boolean status = false;
        if (!isLoggedIn()) {
            return false;
        } else {
            Faculty user = compileFacultyPostData();
            user.setFaculty_type("4");

            status = adminModel.addFaculty(user);
            return status;
        }
    }

    public boolean deletetFaculty() throws SQLException {
        String email = gPost("email");
        Faculty faculty = new Faculty();
        faculty = getFacultyByEmail(email);
        if (faculty == null) {
            return false;
        } else {
            return adminModel.deleteFaculty(faculty);
        }
    }

    public String checkFacultyUnUniqueNess() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            Faculty faculty = compileFacultyPostData();
            return adminModel.existsFaculty(faculty);
        }
    }

    protected Map<String, Object> getFacultysByType(String s) throws SQLException {
        String email = gPost("email");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("status", "failed");
        if (!isLoggedIn()) {
            data.put("info", "login required");
            return data;
        } else {
            AdminModel adminModel = new AdminModel();
            ArrayList<Faculty> faculties;
            
            System.out.println("\\\\\\\\\\\\   " + email + " -> " + !email.equals(""));
            if (!email.equals("")) {
                faculties = adminModel.getAllFacultyByType(s, "email", email);
            } else {
                faculties = adminModel.getAllFacultyByType(s);
            }

            if (faculties.size() == 0) {
                data.put("info", "no user found");
                return data;
            } else {
                data.put("users", faculties);
                return data;
            }

        }
    }

    protected Faculty getFacultyByEmail(String email) throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            //AdminModel adminModel = new AdminModel();
            Faculty user = adminModel.getFacultyByEmail(email);
            return user;
        }
    }

    protected Faculty getFacultyByMobile(String mob) throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            //AdminModel adminModel = new AdminModel();
            Faculty user = adminModel.getFacultyByMobile(mob);
            return user;
        }
    }
}
