<%-- 
    Document   : sidePanel
    Created on : 25 Sep, 2019, 1:33:05 AM
    Author     : Shankha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String project_name = "Student_Management_System"; 
    String view = (String) request.getAttribute("view");
%>


<div class="site-menubar">
    <div class="site-menubar-body">
        <div>
            <div>
                <ul class="site-menu" data-plugin="menu">
                    <li class="site-menu-category">Faculty</li>
                    <li class="site-menu-item <% if(view.equals("dashboard")) out.print("active"); %>">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard">
                            <i class="site-menu-icon md-view-dashboard" aria-hidden="true"></i>
                            <span class="site-menu-title">Home</span>
                        </a>
                    </li>
                    <li class="site-menu-item <% if(view.equals("coordinators")) out.print("active"); %>">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard/coordinator">
                            <i class="site-menu-icon md-view-dashboard" aria-hidden="true"></i>
                            <span class="site-menu-title">Coordinators</span>
                        </a>
                    </li>
                    <li class="site-menu-item <% if(view.equals("hod")) out.print("active"); %>">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard/hod">
                            <i class="site-menu-icon md-view-dashboard" aria-hidden="true"></i>
                            <span class="site-menu-title">HODs</span>
                            <!-- <span class="site-menu-arrow"></span>   -->
                        </a>

                    </li>
                    <li class="site-menu-item <% if(view.equals("teacher")) out.print("active"); %>">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard/teacher">
                            <i class="site-menu-icon md-view-dashboard" aria-hidden="true"></i>
                            <span class="site-menu-title">Teachers</span>
                        </a>
                    </li>
                    
                    <li class="site-menu-item has-sub">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard/student">
                            <i class="site-menu-icon md-view-dashboard"aria-hidden="true"></i>
                            <span class="site-menu-title">Students</span>
                        </a>
                    </li>
                    <li class="site-menu-category">Notice</li>
                    <li class="site-menu-item">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard/faculty">
                            <i class="site-menu-icon md-view-dashboard" aria-hidden="true"></i>
                            <span class="site-menu-title">All Notices</span>
                        </a>
                    </li>
                    <li class="site-menu-item">
                        <a class="animsition-link" href="/<%= project_name %>/dashboard/faculty">
                            <i class="site-menu-icon md-view-dashboard" aria-hidden="true"></i>
                            <span class="site-menu-title">Create New</span>
                        </a>
                    </li>

                </ul>     
            </div>
        </div>
    </div>

    <div class="site-menubar-footer">
        <a href="javascript: void(0);" class="fold-show" data-placement="top" data-toggle="tooltip"
           data-original-title="Settings">
            <span class="icon md-settings" aria-hidden="true"></span>
        </a>
        <a href="javascript: void(0);" data-placement="top" data-toggle="tooltip" data-original-title="Lock">
            <span class="icon md-eye-off" aria-hidden="true"></span>
        </a>
        <a href="javascript: void(0);" data-placement="top" data-toggle="tooltip" data-original-title="Logout">
            <span class="icon md-power" aria-hidden="true"></span>
        </a>
    </div></div>

