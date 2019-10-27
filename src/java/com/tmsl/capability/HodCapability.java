/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import com.tmsl.model.AdminModel;
import com.tmsl.model.HodModel;
import com.tmsl.pojo.Faculty;
import com.tmsl.pojo.Student;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shankha
 */
public class HodCapability extends CommonCapability {

    HodModel hodModel = new HodModel();
    
    public void closeConnection() throws SQLException{
        hodModel.closeConnection();
    }

    public Map<String, Object> getAllFaculty() throws SQLException {
        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            return getFacultysByType("");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "unauthorized");
            return data;
        }
    }

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


    public boolean addCoordinator() throws SQLException {
        boolean status = false;
        if (!isLoggedIn()) {
            return false;
        } else {
            Faculty user = compileFacultyPostData();
            user.setFaculty_type("3");

            status = hodModel.addFaculty(user);
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

            status = hodModel.addFaculty(user);
            return status;
        }
    }

    public boolean deletetFaculty() throws SQLException {
        String email = gPost("email");
        Faculty faculty = new Faculty();
        faculty = getFacultyByEmail(email);
        int userLevel = Integer.parseInt(faculty.getFaculty_type());
        if (faculty == null && userLevel > 2) {
            return false;
        } else {
            return hodModel.deleteFaculty(faculty);
        }
    }

    public String checkFacultyUnUniqueNess() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            Faculty faculty = compileFacultyPostData();
            return hodModel.existsFaculty(faculty);
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
            ArrayList<Faculty> faculties;

            if (!email.equals("")) {
                faculties = hodModel.getAllFacultyByType(s, "email", email);
            } else {
                faculties = hodModel.getAllFacultyByType(s);
            }

            if (faculties.size() == 0) {
                data.put("info", "no user found");
                return data;
            } else {
                data.put("status", "success");
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
            Faculty user = hodModel.getFacultyByEmail(email);
            return user;
        }
    }

    protected Faculty getFacultyByMobile(String mob) throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            //AdminModel adminModel = new AdminModel();
            Faculty user = hodModel.getFacultyByMobile(mob);
            return user;
        }
    }

    /**
     * Student Part
     */
    public Map<String, Object> uploadStudentFile(String dir, String name) {
        Map<String, Object> output = new HashMap<>();
        output.put("status", "failed");
        String UPLOAD_DIRECTORY = dir;
        try {
            if (org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<org.apache.commons.fileupload.FileItem> multiparts = new org.apache.commons.fileupload.servlet.ServletFileUpload(
                            new org.apache.commons.fileupload.disk.DiskFileItemFactory()).parseRequest(request);

                    for (org.apache.commons.fileupload.FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            String fileExt = item.getName().split("\\.")[1];
                            if (!fileExt.equals("xls")) {
                                output.put("info", "invalid file extention");
                                return output;
                            }
                            name = new File(name).getName();
                            item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        }
                    }

                    //File uploaded successfully
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
    
    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> list = null;
        list = hodModel.getAllStudent();
        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    public ArrayList<Student> getStudents(String[] filters, String[] values) throws SQLException {
        ArrayList<Student> list = null;

        list = hodModel.getAllStudent(filters, values);

        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    public boolean setStudnetMarks(String roll, String subjectCode, String marks, String year) throws SQLException {
        return hodModel.setStudentMarks(roll, subjectCode, marks, year);
    }

    public boolean setStudentSemester(String roll, String sem) throws SQLException {
        return hodModel.setStudentSemester(roll, sem);
    }

    public boolean promoteStudentSemester(String roll) throws SQLException {
        return hodModel.promoteStudentSemester(roll);
    }

    public Map<String, Object> getStudnetMarks(String roll, String filters_post_data, String values_post_data, String orderBy) throws SQLException {

        Map<String, Object> data = new HashMap<>();
        if (!filters_post_data.equals("")) {
            ArrayList<String> validFilters = new ArrayList<>();
            validFilters.add("marks_above");
            validFilters.add("subject_code");
            validFilters.add("roll");
            validFilters.add("year");
            validFilters.add("passing_year");

            String[] filters = filters_post_data.split("\\|", -1);
            String[] values = values_post_data.split("\\|", -1);
            for (String s : filters) {
                if (!validFilters.contains(s)) {
                    data.put("error", "true");
                    data.put("filter", "invalid filter value is provided");
                    return data;
                }
            }

            if (filters.length != values.length) {
                data.put("error", "true");
                data.put("filter", "filter count and value count are not same");
                return data;
            }

            data = hodModel.getStudentMarks(roll, filters, values, orderBy);
            if (data == null) {
                data = new HashMap<String, Object>();
                data.put("error", "true");
                data.put("database", "error accessing database");
            }

            return data;
        } else {
            return hodModel.getStudentMarks(roll, new String[]{}, new String[]{}, "");
        }
    }

    /*
    public Map<String, Object> getRunnerups(String year) throws SQLException {
        return hodModel.getRunnerups(year);
    }*/

    public Map<String, Object> getSubjects() throws SQLException {
        return hodModel.getSubjects();
    }

    public boolean assignFacultyToSubject(String email, String subjectCode) throws SQLException {

        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            String subjectID = hodModel.getSubjectID(subjectCode);
            String facultyID = hodModel.getFacultyID(email);
            if (hodModel.assignFacultyToSubject(facultyID, subjectID)) {
                return true;
            }
        }
        return false;
    }

    public boolean unassignFacultyToSubject(String email, String subjectCode) throws SQLException {
        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            String subjectID = hodModel.getSubjectID(subjectCode);
            String facultyID = hodModel.getFacultyID(email);
            if (hodModel.unassignFacultyToSubject(facultyID, subjectID)) {
                return true;
            }
        }
        return false;
    }
}

