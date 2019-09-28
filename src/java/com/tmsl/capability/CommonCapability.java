/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
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
public class CommonCapability extends Controller {

    protected User compileUserPostData() {
        String username = gPost("username");
        String email = gPost("email");
        String first_name = gPost("first_name");
        String middle_name = gPost("middle_name");
        String last_name = gPost("last_name");
        String pass = "Default";
        String mobile_number = gPost("mobile_number");
        String user_type = "-1";

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirst_name(first_name);
        user.setMiddle_name(middle_name);
        user.setLast_name(last_name);
        user.setPassword(pass);
        user.setMobile_number(mobile_number);
        user.setUser_type(user_type);

        return user;
    }

    protected Map<String, Object> getUsersByType(String s) throws SQLException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("status", "failed");
        if (!isLoggedIn()) {
            data.put("info", "login required");
            return data;
        } else {
            AdminModel adminModel = new AdminModel();
            ArrayList<User> users = adminModel.getAllFacultyByType(s);
            if (users.size() == 0) {
                data.put("info", "no coordinator found");
                return data;
            } else {
                data.put("users", users);
                return data;
            }

        }
    }

    protected User getUserByEmail(String email) throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            AdminModel adminModel = new AdminModel();
            User user = adminModel.getFacultyByEmail(email);
            return user;
        }
    }

}
