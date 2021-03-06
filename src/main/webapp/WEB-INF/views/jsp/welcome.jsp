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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <script type="text/javascript" src="//www.gstatic.com/authtoolkit/js/gitkit.js"></script>
    <link type=text/css rel=stylesheet href="//www.gstatic.com/authtoolkit/css/gitkit.css" />
    <script type=text/javascript>
      window.google.identitytoolkit.signInButton(
    '#navbar', // accepts any CSS selector
    {
      widgetUrl: "https://moviehouse-148209.appspot.com/gitkit",
      signOutUrl: "/",
      popupMode: true
    }
  );
  </script>
  <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">

      <title>Welcome in Moviehouse</title>

          <!-- Bootstrap Core CSS -->
          <link href="bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

          <!-- Custom Fonts -->
          <link href="bootstrap/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
          <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
          <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

          <!-- Plugin CSS -->
          <link href="bootstrap/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

          <!-- Theme CSS -->
          <link href="bootstrap/css/creative.css" rel="stylesheet">

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
                          <a class="page-scroll" href="#about">About</a>
                      </li>
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

      <header>
          <div class="header-content">
              <div class="header-content-inner">
                  <h1 id="homeHeading">All your movies in one place</h1>
                  <hr>
                  <p>Try MovieHouse and keep the names and rating of all movies, you have ever seen. It will never happen again, that you forget what you saw last night. Start now and sign in with your Google account.</p>
                  <button class="btn btn-primary btn-xl page-scroll" onclick="window.google.identitytoolkit.signIn()">Sign in</button>
              </div>
          </div>
      </header>

      <section class="bg-primary" id="about">
          <div class="container">
              <div class="row">
                  <div class="col-lg-8 col-lg-offset-2 text-center">
                      <h2 class="section-heading">This is what you need!</h2>
                      <hr class="light">
                      <p class="text-faded">Dear all, my name is Filip Ibl, welcome to this small web application I have built to show you how I work. I have made this application with Java and Spring MVC framework. Application is running on Google App Engine, moreover Google Identity Toolkit is used to prove identification of users. Datas are saved to MySQL database on Google Cloud Platform. The application is not in a final state, new functionality should be implemented. I was pressed on time so I hope that I will do it in future.</p>
                      <p class="text-faded">Check source code on <a href="https://github.com/iblfilip/moviehouse" target="_blank">GitHub</a> or see my <a href="https://www.linkedin.com/in/filip-ibl-a9402365/" target="_blank">LinkedIn</a>.</p>
                  </div>
              </div>
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