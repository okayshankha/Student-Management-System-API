<%-- 
    Document   : dataField
    Created on : 25 Sep, 2019, 12:37:42 PM
    Author     : Shankha
--%>

<%@page import="com.tmsl.pojo.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String project_name = "Student_Management_System";
    ArrayList<User> userList = (ArrayList<User>) application.getAttribute("list");
    int i = 1;
%>





<div class="page">
    <!-- Page Content -->
    <div class="page-content container-fluid">
        <div class="bg-white">
            <!--Table-->
            <table class="table table-hover table-fixed">

                <!--Table head-->
                <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <!--Table head-->

                <!--Table body-->
                <tbody>
                    <%
                    for(User u : userList){
                    %>    
                    <tr id="<%= "row-" + u.getEmail()%>">
                        <th scope="row"><%= i++ %></th>
                        <td id="<%= "row-" + u.getEmail() + "-first_name"%>"><%= u.getFirst_name() %></td>
                        <td id="<%= "row-" + u.getEmail() + "-middle_name"%>"><%= u.getMiddle_name()%></td>
                        <td id="<%= "row-" + u.getEmail() + "-last_name"%>"><%= u.getLast_name()%></td>
                        <td id="<%= "row-" + u.getEmail() + "-email"%>"><%= u.getEmail()%></td>
                        <td id="<%= "row-" + u.getEmail() + "-mobile"%>"><%= u.getMobile_number()%></td>
                        <td>
                            <% 
                            if(u.getUser_type().equals("2")){
                                out.print("HOD"); 
                            }else if(u.getUser_type().equals("3")){ 
                                out.print("Coordinator");
                             }else if(u.getUser_type().equals("4")){ 
                                out.print("Teacher");
                             }
                            %>
                        </td>
                    </tr>
                         
                    <%
                    }
                    %>
                </tbody>
                <!--Table body-->

            </table>
            <!--Table-->
        </div>
    </div>
</div>
