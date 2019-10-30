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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 */
public class HodCapability extends CommonCapability {

    HodModel hodModel = new HodModel();

    /**
     * Ends MySQL connection
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        hodModel.closeConnection();
    }

    /**
     * Submits New Notice
     * @return
     * @throws SQLException
     */
    public boolean submitNotice() throws SQLException {
        String content = gPost("content");
        HttpSession session = request.getSession();
        String faculty_type = session.getAttribute("faculty_type").toString();

        if (faculty_type.equals("1") || faculty_type.equals("2") || faculty_type.equals("3")) {
            String faculty_id = hodModel.getFacultyID(session.getAttribute("email").toString());

            hodModel.postNotice(faculty_id, content);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns All Notices (Optional filter by faculty email)
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getNotice() throws SQLException {
        String faculty_id = hodModel.getFacultyID(gPost("email"));
        if (faculty_id == null) {
            faculty_id = "";
        }
        return hodModel.getNotice(faculty_id);
    }

    /**
     * Returns All Faculty Details
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
     * Returns All HOD Details
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
     * Returns All Coordinator Details
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
     * Returns All Teacher Details
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

            status = hodModel.addFaculty(user);
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

            status = hodModel.addFaculty(user);
            return status;
        }
    }

    /**
     * Deletes Teacher or Coordinator
     * @return
     * @throws SQLException
     */
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

    /**
     * Checks Faculty Un-uniqueNess
     * @return
     * @throws SQLException
     */
    public String checkFacultyUnUniqueNess() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            Faculty faculty = compileFacultyPostData();
            return hodModel.existsFaculty(faculty);
        }
    }

    /**
     * Get Faculty By Type
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

    /**
     * Get Faculty By email
     * @param email
     * @return
     * @throws SQLException
     */
    protected Faculty getFacultyByEmail(String email) throws SQLException {
        if (!isLoggedIn()) {
            return null;
        } else {
            //AdminModel adminModel = new AdminModel();
            Faculty user = hodModel.getFacultyByEmail(email);
            return user;
        }
    }

    /**
     * Get Faculty By Mobile
     * @param mob
     * @return
     * @throws SQLException
     */
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
     * Get All Students
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> list = null;
        list = hodModel.getAllStudent();
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

        list = hodModel.getAllStudent(filters, values);

        return ((list != null) && (list.size() != 0)) ? list : null;
    }

    /**
     * Set Student Marks
     * @param roll
     * @param subjectCode
     * @param marks
     * @param year
     * @return
     * @throws SQLException
     */
    public boolean setStudnetMarks(String roll, String subjectCode, String marks, String year) throws SQLException {
        return hodModel.setStudentMarks(roll, subjectCode, marks, year);
    }

    /**
     * Set Student Semester
     * @param roll
     * @param sem
     * @return
     * @throws SQLException
     */
    public boolean setStudentSemester(String roll, String sem) throws SQLException {
        return hodModel.setStudentSemester(roll, sem);
    }

    /**
     * Promote Student To the next Semester 
     * @param roll
     * @return
     * @throws SQLException
     */
    public boolean promoteStudentSemester(String roll) throws SQLException {
        return hodModel.promoteStudentSemester(roll);
    }

    /**
     * Returns Student Marks
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

            data = hodModel.getStudentMarks(filters, values, orderBy);
            if (data == null) {
                data = new HashMap<String, Object>();
                data.put("error", "true");
                data.put("database", "error accessing database");
            }

            return data;
        } else {
            return hodModel.getStudentMarks(new String[]{}, new String[]{}, "");
        }
    }


    /**
     * Returns All Subjects
     * @return
     * @throws SQLException
     */

    public Map<String, Object> getSubjects() throws SQLException {
        return hodModel.getSubjects();
    }

    /**
     * Assign Faculty To Subject
     * @param email
     * @param subjectCode
     * @return
     * @throws SQLException
     */
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

    /**
     * Unassign Faculty To Subject
     * @param email
     * @param subjectCode
     * @return
     * @throws SQLException
     */
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
