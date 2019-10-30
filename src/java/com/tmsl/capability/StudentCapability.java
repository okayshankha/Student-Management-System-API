/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
import com.tmsl.model.StudentModel;
import com.tmsl.pojo.Student;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 */
public class StudentCapability extends Controller {

    StudentModel studentModel = new StudentModel();

    /**
     * Ends MySQL connection
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        studentModel.closeConnection();
    }

    /**
     * Returns All Notices (Optional filter by faculty email)
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getNotice() throws SQLException {
        String faculty_id = studentModel.getFacultyID(gPost("email"));
        if (faculty_id == null) {
            faculty_id = "";
        }
        return studentModel.getNotice(faculty_id);
    }

    /**
     * Get All Students
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> list = null;
        list = studentModel.getAllStudent();
        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    /**
     * Get All Students (With filters)
     * @param filters
     * @param values
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents(String[] filters, String[] values) throws SQLException {
        ArrayList<Student> list = null;

        list = studentModel.getAllStudent(filters, values);

        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    /**
     * Update Student date by student himself/herself
     * @param updateFields
     * @param values
     * @param roll
     * @return
     * @throws SQLException
     */
    public int update(String[] updateFields, String[] values, String roll) throws SQLException {
        return studentModel.update(updateFields, values, roll);
    }

    /**
     * Upload or update student image
     * @return
     * @throws SQLException
     */
    public Map<String, Object> studentImageUpload() throws SQLException {
        Map<String, Object> output = new HashMap<>();
        String uploadPath = "";
        String TEMP_FILE_DIR = Controller.USER_UPLOAD_DIR + ":\\project_5th_sem\\uploads";
        HttpSession session = request.getSession();
        String TEMP_FILE_NAME = session.getAttribute("roll").toString();
        String name = TEMP_FILE_NAME;
        
        output.put("status", "failed");
        String UPLOAD_DIRECTORY = TEMP_FILE_DIR;
        try {
            if (org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<org.apache.commons.fileupload.FileItem> multiparts = new org.apache.commons.fileupload.servlet.ServletFileUpload(
                            new org.apache.commons.fileupload.disk.DiskFileItemFactory()).parseRequest(request);

                    for (org.apache.commons.fileupload.FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            String fileExt = item.getName().split("\\.")[1];
                            ArrayList<String> validExtentions = new ArrayList<>();
                            validExtentions.add("jpg");
                            validExtentions.add("png");
                            validExtentions.add("webp");
                            
                            if (!validExtentions.contains(fileExt)) {
                                output.put("info", "invalid file extention");
                                return output;
                            }
                            name = new File(name).getName() + "." + fileExt;
                            item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                            uploadPath = UPLOAD_DIRECTORY + File.separator + name;
                        }
                    }
                    //File uploaded successfully
                    
                    uploadPath = uploadPath.replaceAll("\\\\", "%%");
                    studentModel.updateStudentImage(session.getAttribute("roll").toString(), uploadPath);
                    
                    output.put("status", "success");
                    output.put("info", "File Uploaded Successfully");
                } catch (Exception ex) {
                    output.put("info", "File Upload Failed due to " + ex);
                }

            } else {
                output.put("info", "Sorry this Servlet only handles file upload request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
