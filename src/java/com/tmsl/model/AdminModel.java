/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.model;

import base.Model;
import com.tmsl.pojo.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shankha
 */
public class AdminModel extends Model {

    public User getFacultyByEmail(String email) throws SQLException {
        User user = null;


        if (email.trim().equals("")) {
            return user;
        }

        db.selectTable("user_master");
        db.select(new String[]{"*"});
        db.where("email", email);

        ResultSet rs = db.access();
 
        while (rs.next()) {
            User tempUser = new User();
            tempUser.setEmail(rs.getString("email"));
            tempUser.setFirst_name(rs.getString("first_name"));
            tempUser.setLast_name(rs.getString("last_name"));
            tempUser.setMiddle_name(rs.getString("middle_name"));
            tempUser.setMobile_number(rs.getString("mobile_number"));
            tempUser.setPassword("****");
            tempUser.setUser_type(rs.getString("user_type"));

            user = tempUser;
        }

        return user;
    }
    
    
    public ArrayList<User> getAllFacultyByType(String type) throws SQLException {
        ArrayList<User> all_users = new ArrayList<User>();

        if (type.equals("hod")) {
            type = "2";
        }
        if (type.equals("coordinator")) {
            type = "3";
        }
        if (type.equals("teacher")) {
            type = "4";
        }
        if (type.trim().equals("")) {
            return getAllFaculty();
        }

        db.selectTable("user_master");
        db.select(new String[]{"*"});
        db.where("user_type", type);

        ResultSet rs = db.access();

        while (rs.next()) {
            User tempUser = new User();
            tempUser.setEmail(rs.getString("email"));
            tempUser.setFirst_name(rs.getString("first_name"));
            tempUser.setLast_name(rs.getString("last_name"));
            tempUser.setMiddle_name(rs.getString("middle_name"));
            tempUser.setMobile_number(rs.getString("mobile_number"));
            tempUser.setPassword("****");
            tempUser.setUser_type(rs.getString("user_type"));

            all_users.add(tempUser);
        }

        return all_users;
    }

    public ArrayList<User> getAllFaculty() throws SQLException {
        ArrayList<User> all_users = new ArrayList<User>();

        db.selectTable("user_master");
        db.select(new String[]{"*"});
        db.where("user_type", "> 1");

        ResultSet rs = db.access();

        while (rs.next()) {
            User tempUser = new User();
            tempUser.setEmail(rs.getString("email"));
            tempUser.setFirst_name(rs.getString("first_name"));
            tempUser.setLast_name(rs.getString("last_name"));
            tempUser.setMiddle_name(rs.getString("middle_name"));
            tempUser.setMobile_number(rs.getString("mobile_number"));
            tempUser.setUser_type(rs.getString("user_type"));

            all_users.add(tempUser);
        }

        return all_users;
    }

    public boolean addUser(User user) throws SQLException {
        Map<String, String> data = new HashMap<String, String>();

        data.put("username", (String) user.getUsername());
        data.put("email", (String) user.getEmail());
        data.put("first_name", (String) user.getFirst_name());
        data.put("middle_name", (String) user.getMiddle_name());
        data.put("last_name", (String) user.getLast_name());
        data.put("password", (String) user.getPassword());
        data.put("mobile_number", (String) user.getMobile_number());
        data.put("user_type", (String) user.getUser_type());

        //db.selectTable();
        db.insert("user_master", data);
        db.access();

        db.selectTable("user_master");
        db.select_count("id");
        db.where("email", (String) user.getEmail());
        ResultSet rs = db.access();

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT(`id`)")) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean deleteUser(User user) throws SQLException{
        db.selectTable("user_master");
        db.where("email", user.getEmail().trim());
        db.delete();
        db.access();
        
        db.selectTable("user_master");
        db.where("email", user.getEmail().trim());
        db.select_count("id");
        ResultSet rs = db.access();
        
        if(rs.next()){
            if(Integer.parseInt(rs.getString("COUNT(`id`)")) == 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
        
        
    }

    public String existsUser(User user) throws SQLException {
        String result = null;
        
        db.selectTable("user_master");
        db.select_count("*");

        if (user.getPassword().trim().equals("")) {
            return "password not set";
        }
        if (user.getEmail().trim().equals("")) {
            return "email not set";
        }

        db.selectTable("user_master");
        db.select_count("id");
        db.where("email", user.getEmail().trim());
        ResultSet rs = db.access();

        int cnt = 0;
        if (rs.next()) {
            cnt = Integer.parseInt(rs.getString("COUNT(`id`)"));
        }

        if (cnt == 0) {
            db.selectTable("user_master");
            db.select_count("id");
            db.where("mobile_number", user.getMobile_number().trim());
            rs = db.access();
            cnt = 0;
        }else{
            result = "email";
            return result;
        }
        
        if (rs.next()) {
            cnt = Integer.parseInt(rs.getString("COUNT(`id`)"));
        }
        
        if (cnt != 0) {
            result = "mobile";
        }

        return result;
    }

}
