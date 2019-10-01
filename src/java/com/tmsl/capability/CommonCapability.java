/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
import base.Model;
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
public class CommonCapability extends Controller {
    
    public Model adminModel;

    protected Faculty compileFacultyPostData() {
        String username = gPost("username");
        String email = gPost("email");
        String first_name = gPost("first_name");
        String middle_name = gPost("middle_name");
        String last_name = gPost("last_name");
        String pass = "Default";
        String mobile_number = gPost("mobile_number");
        String faculty_type = "-1";

        Faculty faculty = new Faculty();
        faculty.setUsername(username);
        faculty.setEmail(email);
        faculty.setFirst_name(first_name);
        faculty.setMiddle_name(middle_name);
        faculty.setLast_name(last_name);
        faculty.setPassword(pass);
        faculty.setMobile_number(mobile_number);
        faculty.setFaculty_type(faculty_type);

        return faculty;
    }


}
