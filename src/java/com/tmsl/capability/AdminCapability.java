/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
import com.google.gson.Gson;
import com.tmsl.model.AdminModel;
import com.tmsl.pojo.User;
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
        String uType = loggedInUserType();
        if (uType.equals("1") || uType.equals("2")) {
            return getUsersByType("2");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

    public Map<String, Object> getCoordinator() throws SQLException {
        String uType = loggedInUserType();
        if (uType.equals("1") || uType.equals("2")) {
            return getUsersByType("3");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

    public Map<String, Object> getTeacher() throws SQLException {
        String uType = loggedInUserType();
        if (uType.equals("1") || uType.equals("2")) {
            return getUsersByType("4");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

    public boolean setHod() throws SQLException {
        boolean status = false;

        if (!isLoggedIn()) {
            return false;
        } else {
            User user = compileUserPostData();
            user.setUser_type("2");

            status = adminModel.addUser(user);
            return status;
        }
    }

    public boolean deletetUser() throws SQLException {
        String email = gPost("email");
        User user = new User();
        user = getUserByEmail(email);
        if (user == null) {
            return false;
        }else{
            return adminModel.deleteUser(user);
        }
        //
        //return false;
    }

    public boolean setCoordinator() {
        boolean status = false;

        return status;
    }

    public boolean setTeacher() {
        boolean status = false;

        return status;
    }

    public String checkUserNonUniqueNess() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            User user = compileUserPostData();
            user = adminModel.getUser(user);
            //if(user == null) return null;
            return adminModel.existsUser(user);   
        }
    }
    
    public boolean doesExist() throws SQLException {
        String email = gPost("email");
        User user;
        if(email != null){
            user = adminModel.getFacultyByEmail(email);
            if(user == null){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}
