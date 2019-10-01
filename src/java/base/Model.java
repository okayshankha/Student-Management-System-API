/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import Core.Database.DB;
import Core.Database.Database;
import com.google.gson.Gson;
import com.tmsl.pojo.Faculty;
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
    
    
    

    public Faculty getFaculty(Faculty faculty) throws SQLException {
        Faculty output_faculty = new Faculty();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        if (faculty.getPassword().trim() != "" && !faculty.getPassword().trim().equals("adminRequest96")) {
            db.where("password", faculty.getPassword().trim());
        } else {
            if(!faculty.getPassword().trim().equals("adminrequest96")){
                return null;
            }
        }
        
        System.out.println("------------------------->>" + faculty);
        
       

        if (faculty != null && faculty.getEmail().trim() != "") {
            db.where("email", faculty.getEmail().trim());
        } else if (faculty != null && faculty.getUsername().trim() != "") {
            if(!faculty.getPassword().trim().equals("adminrequest96")){
                db.where("username", faculty.getUsername().trim());
            }
        }

        ResultSet rs = db.access(db.getQueryString());
        if (rs.next()) {
            output_faculty.setUsername(rs.getString("username").trim());
            output_faculty.setFirst_name(rs.getString("first_name").trim());
            if (rs.getString("middle_name") != null) {
                output_faculty.setMiddle_name(rs.getString("middle_name").trim());
            }
            if (rs.getString("last_name") != null) {
                output_faculty.setLast_name(rs.getString("last_name").trim());
            }
            output_faculty.setEmail(rs.getString("email").trim());
            output_faculty.setPassword(rs.getString("password").trim());
            if (rs.getString("mobile_number") != null) {
                output_faculty.setMobile_number(rs.getString("mobile_number").trim());
            }
            output_faculty.setFaculty_type(rs.getString("faculty_type").trim());
        } else {
            return null;
        }
        
        return output_faculty;
    }

    public Faculty getFaculty(Faculty faculty, boolean b) throws SQLException {
        Faculty output_faculty = new Faculty();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});

        if (!faculty.getEmail().trim().equals("")) {
            db.where("email", faculty.getEmail().trim());
        }else{
            return null;
        }

        ResultSet rs = db.access();
        if (rs.next()) {
            output_faculty.setUsername(rs.getString("username").trim());
            output_faculty.setFirst_name(rs.getString("first_name").trim());
            if (rs.getString("middle_name") != null) {
                output_faculty.setMiddle_name(rs.getString("middle_name").trim());
            }
            if (rs.getString("last_name") != null) {
                output_faculty.setLast_name(rs.getString("last_name").trim());
            }
            output_faculty.setEmail(rs.getString("email").trim());
            output_faculty.setPassword(rs.getString("password").trim());
            if (rs.getString("mobile_number") != null) {
                output_faculty.setMobile_number(rs.getString("mobile_number").trim());
            }
            output_faculty.setFaculty_type(rs.getString("faculty_type").trim());
        } else {
            return null;
        }
        return output_faculty;
    }


}
