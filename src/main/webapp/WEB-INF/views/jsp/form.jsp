<!--
Copyright 2016 Google Inc. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
<!-- [START base] -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
  <head>

          <script type="text/javascript" src="//www.gstatic.com/authtoolkit/js/gitkit.js"></script>
          <link type=text/css rel=stylesheet href="//www.gstatic.com/authtoolkit/css/gitkit.css" />
          <script type=text/javascript>
            window.google.identitytoolkit.signInButton(
          '#navbar', // accepts any CSS selector
          {
            widgetUrl: "http://localhost:8080/gitkit",
            signOutUrl: "/",
            popupMode: true
          }
        );
        </script>

  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  		<title>Add User</title>

  		<spring:url value="/bootstrap/css/creative.min.css" var="bootstrapUrl" />
  		<link href="${bootstrapUrl}" rel="stylesheet">

  		<spring:url value="/bootstrap/js/creative.js" var="jqueryUrl" />
  		<script src="${jqueryUrl}"></script>

          <!-- Bootstrap Core CSS -->
          <link href="bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

          <!-- Custom Fonts -->
          <link href="bootstrap/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
          <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
          <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

          <!-- Plugin CSS -->
          <link href="bootstrap/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

          <!-- Theme CSS -->
          <link href="bootstrap/css/creative.min.css" rel="stylesheet">

          <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
          <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
          <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
          <![endif]-->

  </head>

  <body id="page-top">

      <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
          <div class="container-fluid">
              <!-- Brand and toggle get grouped for better mobile display -->
              <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                      <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                  </button>
                  <a class="navbar-brand page-scroll" href="#page-top">Movie House</a>
              </div>

              <!-- Collect the nav links, forms, and other content for toggling -->
              <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav navbar-right">
                      <li>
                          <a class="page-scroll" href="/index">My list</a>
                      </li>
                      <li>
                      <div id="navbar" style="margin:auto;background-color:transparent;margin-top: 8px; margin-right: 5px;; margin-bottom: 3px"></div>
                      </li>
                  </ul>
              </div>
              <!-- /.navbar-collapse -->
          </div>
          <!-- /.container-fluid -->
      </nav>



      <section class="bg-soft" id="about">
                      <div class="container">
      <form:form class="form-horizontal" method="POST" action="/createrecord">


      <div class="form-group">
      <div class="col-xs-4"></div>
        <div class="col-xs-4 center">
            <form:label path="title">Title:</form:label>
            <spring:message code="NotEmpty.movie.title" />
            <form:input path="title" type="text" class="form-control" placeholder="${command.title}"/>
            <form:errors path="title" cssClass="error" />


          </div>
          </div>


            <div class="form-group">
                <div class="col-xs-4"></div>
                  <div class="col-xs-4 center">
                    <label class="control-label">Genre:</label>
                    <spring:message code="NotEmpty.movie.genre" />
                    <form:select path="genre" items="${genresList}" multiple="true" size="3" class="form-control"/>
                    <form:errors path="genre" cssClass="error" />
                  </div>
            </div>

          <div class="form-group">
          <div class="col-xs-4"></div>
          <div class="col-xs-4 center">
              <form:label path="seen">Seen</form:label>
              <label class="radio-inline">
              <form:radiobutton path="seen" value="false" />No</label>

              <label class="radio-inline">
              <form:radiobutton path="seen" value="true" />Yes</label>
          </div>
          </div>

          <div class="form-group">
                <div class="col-xs-4"></div>
                  <div class="col-xs-4 center">
                      <form:label path="seenDate">Date of seen:</form:label>
                      <spring:message code="CorrectValue.movie.seenDate"/>
                      <form:input path="seenDate" type="text" class="form-control"/>
                      <form:errors path="seenDate" cssClass="error" />
                    </div>
                    </div>

            <div class="form-group">
                <div class="col-xs-4"></div>
                  <div class="col-xs-4 center">
                    <label class="control-label">Director rating:</label>
                    <form:select path="ratDirector" multiple="true" size="5" class="form-control">
                    <form:option value="" label="undecided"/>
                    <form:options items="${ratingList}"/>
                    </form:select>
                  </div>
            </div>

               <div class="form-group">
                   <div class="col-xs-4"></div>
                     <div class="col-xs-4 center">
                       <label class="control-label">Actors rating:</label>
                       <form:select path="ratActors" multiple="true" size="5" class="form-control">
                       <form:option value="" label="undecided"/>
                       <form:options items="${ratingList}"/>
                       </form:select>
                     </div>
               </div>

             <div class="form-group">
                 <div class="col-xs-4"></div>
                   <div class="col-xs-4 center">
                     <label class="control-label">Rating of story:</label>
                     <form:select path="ratStory" multiple="true" size="5" class="form-control">
                     <form:option value="" label="undecided"/>
                     <form:options items="${ratingList}"/>
                     </form:select>
                   </div>
             </div>

            <div class="form-group">
                <div class="col-xs-4"></div>
                  <div class="col-xs-4 center">
                    <label class="control-label">Visual rating:</label>
                    <form:select path="ratVisual" multiple="true" size="5" class="form-control">
                    <form:option value="" label="undecided"/>
                    <form:options items="${ratingList}"/>
                    </form:select>
                  </div>
            </div>



          <div class="form-group">
          <input type="hidden" name="control" value="${control}"/>
                  <input type="submit" value="Submit"/>
              </div>
      </form:form>
      </div>
      </section>



      <!-- jQuery -->
      <script src="bootstrap/vendor/jquery/jquery.min.js"></script>

      <!-- Bootstrap Core JavaScript -->
      <script src="bootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>

      <!-- Plugin JavaScript -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
      <script src="bootstrap/vendor/scrollreveal/scrollreveal.min.js"></script>
      <script src="bootstrap/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

      <!-- Theme JavaScript -->
      <script src="bootstrap/js/creative.min.js"></script>

  </body>
</html>
<!-- [END base]-->