/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
import com.google.gson.Gson;
import com.sha.ExcelHandler;
import static com.sha.ExcelHandler.*;
import com.sun.media.jfxmedia.logging.Logger;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import com.tmsl.model.AdminModel;
import com.tmsl.pojo.Faculty;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

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

            //System.out.println("\\\\\\\\\\\\   " + email + " -> " + !email.equals(""));
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

    /**
     * Student Part
     */
    public Map<String, Object> uploadStudentFile() {
        Map<String, Object> output = new HashMap<>();
        output.put("status", "failed");
        String UPLOAD_DIRECTORY = "C:/";
        String name = null;
        try {
            if (org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<org.apache.commons.fileupload.FileItem> multiparts = new org.apache.commons.fileupload.servlet.ServletFileUpload(
                            new org.apache.commons.fileupload.disk.DiskFileItemFactory()).parseRequest(request);

                    for (org.apache.commons.fileupload.FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            String fileExt = item.getContentType();
                            if(!fileExt.equals("xls")){
                                output.put("info", "invalid file extention");
                                return output;
                            }
                            
                            //name = new File(item.getName()).getName();
                            name = new File("hello.pdf").getName();
                            item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        }
                    }

                    System.out.println(name);
                    //File uploaded successfully
                    request.setAttribute("message", "File Uploaded Successfully");
                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                    request.setAttribute("message", "File Upload Failed due to " + ex);
                }

            } else {
                request.setAttribute("message",
                        "Sorry this Servlet only handles file upload request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
