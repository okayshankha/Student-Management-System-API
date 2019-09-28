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

/**
 *
 * @author Shankha
 */
public class DashboardModel extends Model {

        public ArrayList<User> getAllFaculty(String type) throws SQLException {
        ArrayList<User> all_users = new ArrayList<User>();

        if(type.equals("hod")) type = "2";
        if(type.equals("coordinator")) type = "3";
        if(type.equals("teacher")) type = "4";
        if(type.trim().equals("")){
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
            //tempUser.setPassword(rs.getString(email));
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
            //tempUser.setPassword(rs.getString(email));
            tempUser.setUser_type(rs.getString("user_type"));

            all_users.add(tempUser);
        }

        return all_users;
    }


}
