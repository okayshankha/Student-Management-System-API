/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import com.tmsl.model.AdminModel;
import com.tmsl.pojo.Faculty;
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
public class AdminCapability extends CommonCapability {

    AdminModel adminModel = new AdminModel();
    
    /**
     * Ends MySQL connection
     * @throws SQLException
     */
    public void closeConnection() throws SQLException{
        adminModel.closeConnection();
    }
    
    /**
     * Submits New Notice
     * @return
     * @throws SQLException
     */
    public boolean submitNotice() throws SQLException{
        String content = gPost("content");
        HttpSession session = request.getSession();
        String faculty_type = session.getAttribute("faculty_type").toString();
        
        if(faculty_type.equals("1") || faculty_type.equals("2") || faculty_type.equals("3")){
            String faculty_id = adminModel.getFacultyID(session.getAttribute("email").toString());
            
            adminModel.postNotice(faculty_id, content);
            
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Fetches All Notices (Optionally filtered by faculty email id)
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getNotice() throws SQLException{
        String faculty_id = adminModel.getFacultyID(gPost("email"));
        if(faculty_id == null){
            Map<String, Object> data = new HashMap<>();
            return data;
        }
        return adminModel.getNotice(faculty_id);
    }

    /**
     * Returns the Logged in faculty details
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns All Hod Data
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns All Coordinator Data
     * @param email
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns All Teacher Data
     * @return
     * @throws SQLException
     */
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

    /**
     * Adds new HOD
     * @return
     * @throws SQLException
     */
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

    /**
     * Adds new Coordinator
     * @return
     * @throws SQLException
     */
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

    /**
     * Adds new Teacher
     * @return
     * @throws SQLException
     */
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

    /**
     * Deletes Faculty
     * @return
     * @throws SQLException
     */
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

    /**
     * Checks if their is any common entry for username and email.
     * @return
     * @throws SQLException
     */
    public String checkFacultyUnUniqueNess() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            Faculty faculty = compileFacultyPostData();
            return adminModel.existsFaculty(faculty);
        }
    }

    /**
     * Returns Faculty data filtering by type.
     * @param s
     * @return
     * @throws SQLException
     */
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

            if (!email.equals("")) {
                faculties = adminModel.getAllFacultyByType(s, "email", email);
            } else {
                faculties = adminModel.getAllFacultyByType(s);
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

    /**
     * Returns Faculty data filtering by email.
     * @param email
     * @return
     * @throws SQLException
     */
    protected Faculty getFacultyByEmail(String email) throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            //AdminModel adminModel = new AdminModel();
            Faculty user = adminModel.getFacultyByEmail(email);
            return user;
        }
    }

    /**
     * Returns Faculty data filtering by mobile number.
     * @param mob
     * @return
     * @throws SQLException
     */
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
     * Uploads Student File (.xls)
     * Student Part
     * @param dir
     * @param name
     * @return 
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

    /**
     * Returns Student data.
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> list = null;
        list = adminModel.getAllStudent();
        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    /**
     * Returns Student data applying filters.
     * @param filters
     * @param values
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents(String[] filters, String[] values) throws SQLException {
        ArrayList<Student> list = null;

        list = adminModel.getAllStudent(filters, values);

        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    /**
     * Add Student one by one
     * @param studentData
     * @param dataPosMap
     * @return
     */
    public int addStudents(ArrayList<ArrayList<String>> studentData, Map<String, Integer> dataPosMap) {
        //int length = studentData.size();
        int cnt = 0;
        for (int i = 1; i < studentData.size(); i++) {
            ArrayList<String> sData = studentData.get(i);

            //Student s = new Student();
            try {
                Student s = compileStudentExcelData(sData, dataPosMap);
                System.out.println("I am here 2");
                if (adminModel.addStudent(s)) {
                    cnt++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return cnt;
    }

    /**
     * Sets Student Marks
     * @param roll
     * @param subjectCode
     * @param marks
     * @param year
     * @return
     * @throws SQLException
     */
    public boolean setStudnetMarks(String roll, String subjectCode, String marks, String year) throws SQLException {
        return adminModel.setStudentMarks(roll, subjectCode, marks, year);
    }

    /**
     * Sets Student Semester
     * @param roll
     * @param sem
     * @return
     * @throws SQLException
     */
    public boolean setStudentSemester(String roll, String sem) throws SQLException {
        return adminModel.setStudentSemester(roll, sem);
    }

    /**
     * Promotes Students to the next semester
     * @param roll
     * @return
     * @throws SQLException
     */
    public boolean promoteStudentSemester(String roll) throws SQLException {
        return adminModel.promoteStudentSemester(roll);
    }

    /**
     * Returns Student Marks (Optional filter)
     * @param filters_post_data
     * @param values_post_data
     * @param orderBy
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getStudnetMarks(String filters_post_data, String values_post_data, String orderBy) throws SQLException {

        Map<String, Object> data = new HashMap<>();
        if (!filters_post_data.equals("")) {
            ArrayList<String> validFilters = new ArrayList<>();
            validFilters.add("marks_above");
            validFilters.add("subject_code");
            validFilters.add("roll");
            validFilters.add("year");
            validFilters.add("passing_year");
            validFilters.add("sem");

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

            data = adminModel.getStudentMarks(filters, values, orderBy);
            if (data == null) {
                data = new HashMap<String, Object>();
                data.put("error", "true");
                data.put("database", "error accessing database");
            }

            return data;
        } else {
            return adminModel.getStudentMarks(new String[]{}, new String[]{}, "");
        }
    }

    /**
     * Returns Student Runner Ups (Optional filter by  year) grouped by semester
     * @param year
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getRunnerups(String year) throws SQLException {
        return adminModel.getRunnerups(year);
    }

    /**
     * returns all subject data
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getSubjects() throws SQLException {
        return adminModel.getSubjects();
    }

    /**
     * Assign Faculty to a Subject.
     * @param email
     * @param subjectCode
     * @return
     * @throws SQLException
     */
    public boolean assignFacultyToSubject(String email, String subjectCode) throws SQLException {

        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            String subjectID = adminModel.getSubjectID(subjectCode);
            String facultyID = adminModel.getFacultyID(email);
            if (adminModel.assignFacultyToSubject(facultyID, subjectID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Unassign Faculty from a Subject.
     * @param email
     * @param subjectCode
     * @return
     * @throws SQLException
     */
    public boolean unassignFacultyToSubject(String email, String subjectCode) throws SQLException {

        String uType = loggedInFacultyType();
        if (uType.equals("1") || uType.equals("2")) {
            String subjectID = adminModel.getSubjectID(subjectCode);
            String facultyID = adminModel.getFacultyID(email);
            if (adminModel.unassignFacultyToSubject(facultyID, subjectID)) {
                return true;
            }
        }
        return false;
    }

}
