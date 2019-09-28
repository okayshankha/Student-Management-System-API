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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 *
 */
public class AuthModel extends Model {


    public boolean validate(User user) throws SQLException {
        boolean result = false;
        db.selectTable("user_master");
        db.select_count("id");

        if (!user.getPassword().trim().equals("")) {
            db.where("password", user.getPassword().trim());
        } else {
            return result;
        }

        if (!user.getEmail().trim().equals("")) {
            db.where("email", user.getEmail().trim());
        } else if (!user.getUsername().trim().equals("")) {
            db.where("username", user.getUsername().trim());
        }

        System.out.println(db.getQueryString());

        ResultSet rs = db.access();

        while (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT(`ID`)").trim()) > 0) {
                result = true;
                
            }
        }

        return result;
    }

}
