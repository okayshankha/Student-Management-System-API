/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.Controller;
import static base.Controller.request;
import com.sha.ExcelHandler;
import static com.sha.ExcelHandler.getDataPositionMapping;
import static com.sha.ExcelHandler.readExcel;
import com.tmsl.capability.AdminCapability;
import com.tmsl.model.AuthModel;
import com.tmsl.pojo.Faculty;
import com.tmsl.pojo.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 *
 * <pre>
 * 1. Path: /api/auth
 *    Post: a. (username or email) and  b. pass
 * {
 *   "response": {
 *       "user": {
 *           "username": "admin",
 *           "first_name": "Admin",
 *           "middle_name": "",
 *           "last_name": "",
 *           "email": "admin@admin.com",
 *           "password": "****",
 *           "mobile_number": "-1",
 *           "faculty_type": "1"
 *       },
 *       "status": "success"
 *   },
 *   "apipath": "/auth",
 *   "responsetime": "Thu Sep 26 21:33:22 IST 2019"
 * }
 *
 * </pre>
 *
 *
 *
 */
@WebServlet(name = "Api", urlPatterns = {"/api/*"})

public class Api extends Controller {

    @Override
    protected void processRequest(HttpServletRequest _request, HttpServletResponse response) throws ServletException, IOException {
        Controller.request = _request;
        boolean route = true;
        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        Object output = null;
        String requestApi = getRequstApi();
        String userLevel = loggedInFacultyType();
        /**
         * user level : 1 -> admin 2 -> hod 3 -> coordinator 4 -> teacher 5 ->
         * student
         */

        if (!requestApi.equals("/auth")) {
            if (isLoggedIn()) {
                switch (userLevel) {
                    case "1": // Admin
                        output = admin(requestApi);
                        break;
                    case "2": // Hod
                        break;
                    case "3": // Coordinator
                        break;
                    case "4": // Teacher
                        break;
                    case "5": // Student
                        break;
                    default:
                        output = new AbstractMap.SimpleEntry<String, String>("status", "invalid user level");
                }
            } else {
                Map data = new HashMap<String, String>();
                data.put("status", "failed");
                data.put("info", "login required");
                output = data;
            }
        } else if (requestApi.equals("/auth")) {
            try {
                output = auth();
            } catch (SQLException ex) {
            }
        } else {
            Map data = new HashMap<String, String>();
            data.put("status", "failed");
            data.put("info", "invalid request");
            output = data;
        }

        /**
         * *****************************************************
         */
        PrintWriter out = response.getWriter();
        try {
            out.print(jsonOut(output));
        } finally {
            out.close();
        }

    }

    private Map<String, Object> auth() throws SQLException {
        if (isLoggedIn()) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", "failed");
            data.put("info", "already logged in");
            return data;
        }

        AuthModel authModel = new AuthModel();

        Faculty faculty = new Faculty();
        String username = null;
        String email = null;
        String pass = null;

        authModel.validate(faculty);

        username = gPost("username");
        pass = gPost("pass");

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("status", "failed");

        if (username != null && pass != null) {
            String regex = "^(.+)@(.+)$"; //Email pattern

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(username);

            if (matcher.matches()) {
                email = username;
                username = null;
            }

            if (email != null) {
                faculty.setEmail(email);
            }
            if (username != null) {
                faculty.setUsername(username);
            }
            if (pass != null) {
                faculty.setPassword(pass);
            }

            try {
                faculty = authModel.getFaculty(faculty);

                if (faculty != null) {
                    output.put("status", "success");
                    HttpSession session = request.getSession();
                    session.setAttribute("login_status", "true");
                    session.setAttribute("email", faculty.getEmail());
                    session.setAttribute("faculty_type", faculty.getFaculty_type());
                    session.setAttribute("username", faculty.getUsername());
                }
            } catch (SQLException ex) {

            }
        }

        if (output.get("status").equals("success")) {
            faculty.setEmail("****");
            faculty.setPassword("****");
            faculty.setMobile_number("****");
            output.put("faculty", faculty);
        }

        return output;
    }

    private Map<String, Object> admin(String requestApi) {
        AdminCapability capability = new AdminCapability();
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("status", "invalid api path");
        // System.out.println(requestApi);
        switch (requestApi) {
            case "/all-hod":  //d - returns all hod user details
                try {
                    output = capability.getHod();
                } catch (SQLException ex) {
                }
                break;
            case "/add-hod":  //d - adds hod user details
                try {
                    String sameAttr = capability.checkFacultyUnUniqueNess();
                    if (sameAttr != null) {
                        output.put("status", "failed");
                        output.put("ununique", sameAttr);
                        output.put("info", sameAttr + " needs to be unique");
                    } else if (capability.addHod()) {
                        output.put("status", "success");
                    } else {
                        output.put("status", "failed");
                        output.put("info", "update error");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Api.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "/all-coordinator":  //d - returns all coordinator user details
                try {
                    String email = gGet("email");
                    output = capability.getCoordinator(email);
                } catch (SQLException ex) {
                }
                break;
            case "/add-coordinator":  //d - adds coordinator user details
                try {
                    String sameAttr = capability.checkFacultyUnUniqueNess();
                    if (sameAttr != null) {
                        output.put("status", "failed");
                        output.put("ununique", sameAttr);
                        output.put("info", sameAttr + " needs to be unique");
                    } else if (capability.addCoordinator()) {
                        output.put("status", "success");
                    } else {
                        output.put("status", "failed");
                        output.put("info", "update error");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                break;

            case "/all-teacher":  //d - returns all teacher user details
                try {
                    output = capability.getTeacher();
                } catch (SQLException ex) {
                }
                break;
            case "/add-teacher":  //d - adds teacher user details
                try {
                    String sameAttr = capability.checkFacultyUnUniqueNess();
                    if (sameAttr != null) {
                        output.put("status", "failed");
                        output.put("ununique", sameAttr);
                        output.put("info", sameAttr + " needs to be unique");
                    } else if (capability.addTeacher()) {
                        output.put("status", "success");
                    } else {
                        output.put("status", "failed");
                        output.put("info", "update error");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                break;
            case "/delete-user":  //d - delete user details
                try {
                    String sameAttr = capability.checkFacultyUnUniqueNess();
                    if (sameAttr == "email") {
                        if (capability.deletetFaculty()) {
                            output.put("status", "success");
                            output.put("info", "hod deleted");
                        } else {
                            output.put("status", "failed");
                            output.put("info", "failed to delete");
                        }
                    } else {
                        output.put("status", "failed");
                        output.put("info", "non-existing user");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                break;
            case "/upload/student_file":
                try {
                    String TEMP_FILE_DIR = "D:\\";
                    String TEMP_FILE_NAME = "px2.xls";
                    Map<String, Object> output_ = capability.uploadStudentFile(TEMP_FILE_DIR, TEMP_FILE_NAME);

                    if (output_.get("status").equals("success")) {
                        HashMap<String, ArrayList<ArrayList<String>>> excelData = ExcelHandler.readExcel(TEMP_FILE_DIR + TEMP_FILE_NAME);

                        if (excelData != null) {
                            String sheetName = null;
                            for (HashMap.Entry<String, ArrayList<ArrayList<String>>> entry : excelData.entrySet()) {
                                sheetName = entry.getKey();
                                break;
                            }
                            Map<String, Integer> dataPosMap = new HashMap<String, Integer>();
                            dataPosMap = ExcelHandler.getDataPositionMapping(excelData.get(sheetName));
                            System.out.println("I am here 1");
                            int cnt = capability.addStudents(excelData.get(sheetName), dataPosMap);

                            output.put("status", "success");
                            output.put("new_student_count", cnt);
                        } else {
                            output.put("status", "failed");
                            output.put("info", "cannot read excel file");
                        }

                    } else {
                        output = output_;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex);
                }
                break;
            case "/student/get/bio":
                try {
                    String filter = gPost("filter");
                    String filterValue = gPost("value");

                    String filters[] = null;
                    String values[] = null;
                    boolean hasFilterRequest = false;
                    boolean validFilterStucture = true;
                    boolean invalidFilter = false;
                    ArrayList<Student> studentList = null;

                    if (!filter.equals("") && !filterValue.equals("")) {
                        filters = filter.split("\\|", -1);
                        values = filterValue.split("\\|", -1);
                        ArrayList<String> validFilters = new ArrayList<String>();
                        validFilters.add("email");              // Filters by email id
                        validFilters.add("mobile");             // Filters by mobile
                        validFilters.add("fname");              // Filters by first name
                        validFilters.add("mname");              // Filters by middle name
                        validFilters.add("lname");              // Filters by last name
                        validFilters.add("roll");               // Filters by university roll number
                        validFilters.add("reg");                // Filters by university registration number
                        validFilters.add("passing_year");       // Filters by year of passing
                        validFilters.add("sem");                // Filters by students current semester number

                        for (String f : filters) {
                            if (!validFilters.contains(f)) {
                                invalidFilter = true;
                                break;
                            }
                        }

                        if (!invalidFilter) {
                            if (filters.length == 0 || values.length == 0) {
                                output.put("status", "failed");
                                output.put("info", "filters/values or both parameters are empty");
                            } else if (filters.length != values.length) {
                                validFilterStucture = false;
                                output.put("status", "failed");
                                output.put("info", "unequal number of filters and values");
                            } else {
                                //do stuff here
                                studentList = capability.getStudents(filters, values);
                                output.put("status", "success");
                                output.put("student_count", (studentList == null) ? 0 : studentList.size());
                                output.put("students", studentList);
                            }
                        } else {
                            output.put("status", "failed");
                            output.put("info", "one or more invalid filters found");
                        }

                    } else {
                        //studentList = capability.getStudents(filters, values);
                        studentList = capability.getStudents();

                        output.put("status", "success");
                        output.put("student_count", (studentList == null) ? 0 : studentList.size());
                        output.put("students", studentList);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex);
                }
                break;
            case "/student/edit":
                try {
                    String filter = gPost("filter");
                    String filterValue = gPost("value");

                    String filters[] = null;
                    String values[] = null;
                    boolean hasFilterRequest = false;
                    boolean validFilterStucture = true;
                    boolean invalidFilter = false;
                    ArrayList<Student> studentList = null;

                    if (!filter.equals("") && !filterValue.equals("")) {
                        filters = filter.split("\\|", -1);
                        values = filterValue.split("\\|", -1);
                        ArrayList<String> validFilters = new ArrayList<String>();
                        validFilters.add("roll");               // Filters by university roll number

                        for (String f : filters) {
                            if (!validFilters.contains(f)) {
                                invalidFilter = true;
                                break;
                            }
                        }

                        if (!invalidFilter) {
                            if (filters.length == 0 || values.length == 0) {
                                output.put("status", "failed");
                                output.put("info", "filters/values or both parameters are empty");
                            } else if (filters.length != values.length) {
                                validFilterStucture = false;
                                output.put("status", "failed");
                                output.put("info", "unequal number of filters and values");
                            } else {
                                //do stuff here
                                studentList = capability.getStudents(filters, values);
                                output.put("status", "success");
                                output.put("student_count", (studentList == null) ? 0 : studentList.size());
                                output.put("students", studentList);
                            }
                        } else {
                            output.put("status", "failed");
                            output.put("info", "one or more invalid filters found");
                        }

                    } else {
                        //studentList = capability.getStudents(filters, values);
                        studentList = capability.getStudents();

                        output.put("status", "success");
                        output.put("student_count", (studentList == null) ? 0 : studentList.size());
                        output.put("students", studentList);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex);
                }
                break;
        }

        return output;
    }
}
