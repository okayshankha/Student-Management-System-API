<%-- 
    Document   : login
    Created on : 20 Sep, 2019, 10:52:45 AM
    Author     : Shankha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="description" content="bootstrap material admin template">
        <meta name="author" content="">

        <title>Login V3 | Remark Material Admin Template</title>

        <link rel="apple-touch-icon" href="assets/images/apple-touch-icon.png">
        <link rel="shortcut icon" href="assets/images/favicon.ico">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="assets/global/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/global/css/bootstrap-extend.min.css">
        <link rel="stylesheet" href="assets/css/site.min.css">

        <!-- Plugins -->
        <link rel="stylesheet" href="assets/global/vendor/animsition/animsition.css">
        <link rel="stylesheet" href="assets/global/vendor/asscrollable/asScrollable.css">
        <link rel="stylesheet" href="assets/global/vendor/switchery/switchery.css">
        <link rel="stylesheet" href="assets/global/vendor/intro-js/introjs.css">
        <link rel="stylesheet" href="assets/global/vendor/slidepanel/slidePanel.css">
        <link rel="stylesheet" href="assets/global/vendor/flag-icon-css/flag-icon.css">
        <link rel="stylesheet" href="assets/global/vendor/waves/waves.css">
        <link rel="stylesheet" href="assets/examples/css/pages/login-v3.css">


        <!-- Fonts -->
        <link rel="stylesheet" href="assets/global/fonts/material-design/material-design.min.css">
        <link rel="stylesheet" href="assets/global/fonts/brand-icons/brand-icons.min.css">
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Roboto:300,400,500,300italic'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" integrity="sha256-+N4/V/SbAFiW1MPBCXnfnP9QSN3+Keu+NlB+0ev/YKQ=" crossorigin="anonymous" />
        <!--[if lt IE 9]>
        <script src="assets/global/vendor/html5shiv/html5shiv.min.js"></script>
        <![endif]-->

        <!--[if lt IE 10]>
        <script src="assets/global/vendor/media-match/media.match.min.js"></script>
        <script src="assets/global/vendor/respond/respond.min.js"></script>
        <![endif]-->

        <!-- Scripts -->
        <script src="assets/global/vendor/breakpoints/breakpoints.js"></script>
        <script>
            Breakpoints();
        </script>
    </head>
    <body class="animsition page-login-v3 layout-full">
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->


        <!-- Page -->
        <div class="page vertical-align text-center bg-blue-grey-600" data-animsition-in="fade-in" data-animsition-out="fade-out">>
            <div class="page-content vertical-align-middle">
                <div class="panel">
                    <div class="panel-body">
                        <div class="brand">
                            <img class="brand-img" src="assets//images/logo-colored.png" alt="..." width="100">
                            <h2 class="brand-text font-size-18">Student Database Management</h2>
                        </div>
                        <form id="loginform" method="post" action="#">

                            <div class="form-group form-material floating" data-plugin="formMaterial">
                                <input type="text" class="form-control" id="username-field" />
                                <label class="floating-label">Username or Email</label>
                            </div>
                            <div class="form-group form-material floating" data-plugin="formMaterial">
                                <input type="password" class="form-control" id="pass-field" />
                                <label class="floating-label">Password</label>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block btn-lg mt-40">Sign in</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Page -->


        <!-- Core  -->
        <script src="assets/global/vendor/babel-external-helpers/babel-external-helpers.js"></script>
        <script src="assets/global/vendor/jquery/jquery.js"></script>
        <script src="assets/global/vendor/popper-js/umd/popper.min.js"></script>
        <script src="assets/global/vendor/bootstrap/bootstrap.js"></script>
        <script src="assets/global/vendor/animsition/animsition.js"></script>
        <script src="assets/global/vendor/mousewheel/jquery.mousewheel.js"></script>
        <script src="assets/global/vendor/asscrollbar/jquery-asScrollbar.js"></script>
        <script src="assets/global/vendor/asscrollable/jquery-asScrollable.js"></script>
        <script src="assets/global/vendor/ashoverscroll/jquery-asHoverScroll.js"></script>
        <script src="assets/global/vendor/waves/waves.js"></script>

        <!-- Plugins -->
        <script src="assets/global/vendor/switchery/switchery.js"></script>
        <script src="assets/global/vendor/intro-js/intro.js"></script>
        <script src="assets/global/vendor/screenfull/screenfull.js"></script>
        <script src="assets/global/vendor/slidepanel/jquery-slidePanel.js"></script>
        <script src="assets/global/vendor/jquery-placeholder/jquery.placeholder.js"></script>

        <!-- Scripts -->
        <script src="assets/global/js/Component.js"></script>
        <script src="assets/global/js/Plugin.js"></script>
        <script src="assets/global/js/Base.js"></script>
        <script src="assets/global/js/Config.js"></script>

        <script src="assets/js/Section/Menubar.js"></script>
        <script src="assets/js/Section/GridMenu.js"></script>
        <script src="assets/js/Section/Sidebar.js"></script>
        <script src="assets/js/Section/PageAside.js"></script>
        <script src="assets/js/Plugin/menu.js"></script>

        <script src="assets/global/js/config/colors.js"></script>
        <script src="assets/js/config/tour.js"></script>
        <script>Config.set('assets', 'assets/assets');</script>

        <!-- Page -->
        <script src="assets/js/Site.js"></script>
        <script src="assets/global/js/Plugin/asscrollable.js"></script>
        <script src="assets/global/js/Plugin/slidepanel.js"></script>
        <script src="assets/global/js/Plugin/switchery.js"></script>
        <script src="assets/global/js/Plugin/jquery-placeholder.js"></script>
        <script src="assets/global/js/Plugin/material.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js" integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>
        <link href="assets/Notiflix-1.9.1/Minified/notiflix-1.9.1.min.css" rel="stylesheet" type="text/css"/>
        <script src="assets/Notiflix-1.9.1/Minified/notiflix-1.9.1.min.js" type="text/javascript"></script>
        <script>

            var baseURL = "${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/";


            var s;
            (function (document, window, $) {

                'use strict';

                var Site = window.Site;
                $(document).ready(function () {
                    Site.run();
                });

                $(document).ready(function () {
                    Notiflix.Notify.Init({useFontAwesome: true, });

                    $('#loginform').ajaxForm({
                        beforeSubmit: function () {
                            //validate();
                        },
                        success: function (result) {
                            $.ajax({
                                url: baseURL + 'api/auth',
                                data: {
                                    username: $('#username-field').val(),
                                    pass: $('#pass-field').val()
                                },
                                method: 'POST',
                                success: function (result) {
                                    var data = result;
                                    s = result;
                                    console.log(result);
                                    if (data.response.status == 'success') {
                                        Notiflix.Notify.Success('Success! Redirecting...');
                                        setTimeout(function () {
                                            location.reload()
                                        }, 3000);
                                    } else {
                                        Notiflix.Notify.Failure('Invalid Login');
                                    }
                                },
                                error: function (error) {
                                    Notiflix.Notify.Failure('No internet connection!');
                                }
                            });
                        },
                        error: function (error) {
                            //console.log(error);
                        }
                    });

                });
            })(document, window, jQuery);
        </script>

    </body>
</html>

