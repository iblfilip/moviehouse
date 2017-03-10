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
        widgetUrl: "http://localhost:8080/gitkit",
        signOutUrl: "/",
        popupMode: true
      }
    );
    </script>

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
                          <a class="page-scroll" href="/create">Add movie</a>
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

 <section class="bg-soft" id="services">
                 <div class="container">

<div class="row">
<div class="col-sm-6">
<div class="well">
<div class="page-header">
<h1>${command.title}</h1>
</div>
<p>Genre: ${command.genre}</p>
<p>Seen: ${command.seen}</p>
<p>Last seen: ${command.seenDate}</p>
</div>
</div>
<div class="col-sm-2">
<div class="well">
<div class="page-header">
    <h1>Overall: ${command.ratTotal}%</h1>
</div>
<table>
<tbody>
<td>
<form action="${pageContext.request.contextPath}/detailbuttons" method="POST" role="form" class="form-horizontal">
<input type="hidden" name="movieId" value=${command.movieId} />
<input type="image" src="media/icons/cancel.png" height="25" width="25" name="erase" value="something"/>
</td>
<td>
<form action="${pageContext.request.contextPath}/detailbuttons" method="post">
<input type="image" src="media/icons/pencil.png" height="25" width="25" name="edit" value="something" /
</td>
</table>
</tbody>
</div>
</div>
<div class="col-sm-4">
<img src="media/star.jpg" width="220" height="290">
</div>
</div>
<div class="row">
<div class="well">
<p>Total rating:
<div class="progress">
  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${command.ratTotal}"
  aria-valuemin="0" aria-valuemax="100" style="width:${command.ratTotal}%">
    ${command.ratTotal}%
  </div>
</div>
</p>
<p>Director rating:
<div class="progress">
  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${command.ratDirector}"
  aria-valuemin="0" aria-valuemax="100" style="width:${command.ratDirector}%">
    ${command.ratDirector}%
  </div>
</div>
</p>
<p>Actors rating:
<div class="progress">
  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${command.ratActors}"
  aria-valuemin="0" aria-valuemax="100" style="width:${command.ratActors}%">
    ${command.ratActors}%
  </div>
</div>
</p>
<p>Story rating:
<div class="progress">
  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${command.ratStory}"
  aria-valuemin="0" aria-valuemax="100" style="width:${command.ratStory}%">
    ${command.ratStory}%
  </div>
</div>
</p>
<p>Visual rating:
<div class="progress">
  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${command.ratVisual}"
  aria-valuemin="0" aria-valuemax="100" style="width:${command.ratVisual}%">
    ${command.ratVisual}%
  </div>
</div>
</p>
<input type="hidden" name="selectedMovie" value="${command.movieId}"/>
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