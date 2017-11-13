<%@page session="false" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring MVC 4 + Ajax Hello World</title>

    <c:set var="ctx" value="${pageContext.request.contextPath }"/>
    <c:out value="${ctx}"/>
    <c:out value="${pageContext.request.contextPath}"/>

    <c:url var="home" value="/" scope="request"/>

    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css"
                var="bootstrapCss"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
    <!--
     <link href="/resources/core/css/hello.css" rel="stylesheet" />
     <link href="/resources/core/css/bootstrap.min.css" rel="stylesheet" />
 -->
    <spring:url value="/resources/core/js/jquery.1.10.2.min.js"
                var="jqueryJs"/>
    <script src="${jqueryJs}"></script>

    <!-- Angular Js -->
    <spring:url value="/resources/core/js/angular.1.2.32.js"
                var="angularJs"/>
    <script src="${angularJs}"></script>

    <script>

        var myAPP = angular.module("myAPP", []);


        var baseUrl = '${pageContext.request.contextPath}';


        myAPP.controller("myController", function ($scope, $http) {

                $scope.login = function () {




                    //var sentUrl = baseUrl + "/repository/models/"+ modelId +"/source-extra";
                    var sentUrl = baseUrl + "/login";

                    var params_ = {userId: $scope.userId, password: $scope.password};

                    //var sentUrl = baseUrl + "/repository/process-definitions/"+ modelId +"/image";
                    //var sentData = {modelId : ""};
                    //sentData.modelId = modelId;

                    $http({
                        method: 'POST'
                        , url: sentUrl
                        , params: params_

                    }).then(function successCallback(response) {

                        //alert(response.data);
                        //var str = _arrayBufferToBase64(response.data);
                        //alert(response.data);

                        //console.log(str);
                        //$scope.image = response.data;
                        //alert("success")
                        //alert(response.statusCode);

                        //$location.path('/local/modelList').replace();
                        //$scope.$apply();
                        window.location.href = baseUrl + "/local/modeList";


                    }, function errorCallback(response) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        //$scope.data = response.data || 'Request failed';
                        //$scope.status = response.status;

                        alert(response.status);

                        //alert($scope.data);
                        //alert($scope.status);
                        //alert(response.statusCode);
                        alert("failed");
                    });


                }
            }
        );


    </script>
</head>

<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring 4 MVC Ajax Hello World</a>
        </div>
    </div>
</nav>

<div class="container" style="min-height: 500px">

    <div ng-app="myAPP" ng-controller="myController">
        <div class="starter-template">
            <h2>Search Form</h2>
            <br>

            <div id="feedback"></div>

            <form class="form-inline" id="search-form">

                <!--
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <input type=text class="form-control" id="username">
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="email">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="bth-search"
                                class="btn btn-primary btn-lg">Search
                        </button>
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg' href="${pageContext.request.contextPath}/search">Activiti
                            Search</a>
                    </div>


                </div>

                <div class="form-group">

                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg'
                           href="${pageContext.request.contextPath}/local/taskServiceXmlTransit">
                            Run TaskService With Xml Data Transit
                        </a>
                    </div>


                </div>

                <div class="form-group">

                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg'
                           href="${pageContext.request.contextPath}/local/taskServiceVariableTransit">
                            Run TaskService With Variable Data Transit
                        </a>
                    </div>


                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg'
                           href="${pageContext.request.contextPath}/local/taskServiceExclusiveGatewayVariableTransit">
                            Run TaskService Using Exclusive Gateway With Variable Data Transit
                        </a>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg'
                           href="${pageContext.request.contextPath}/local/taskServiceParallelGatewayVariableTransit">
                            Run TaskService Using Parallel Gateway With Variable Data Transit
                        </a>
                    </div>
                </div>

                -->
                <div class="form-group">

                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg' href="${pageContext.request.contextPath}/local/modeList">Model
                            List</a>
                    </div>


                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class='btn btn-primary btn-lg' href="${pageContext.request.contextPath}/local/ruleService">
                            Run Rule Service
                        </a>
                    </div>
                </div>
            </form>


            <h2>Login</h2>
            <form class="form-inline">
                <div class="form-group">

                    <input type="text" class="form-control" id="userId" ng-model="userId"
                           placeholder="ID">
                </div>
                <div class="form-group">

                    <input type="text" class="form-control" id="password" ng-model="password"
                           placeholder="Password">
                </div>

                <div class="form-group">
                    <button type="button" class="btn btn-default" ng-click="login()">
                        Login
                    </button>
                </div>
            </form>


        </div>
    </div>

</div>

<div class="container">
    <footer>
        <p>

        </p>
    </footer>
</div>

<script>
    jQuery(document).ready(function ($) {

        $("#search-form").submit(function (event) {

            // Disble the search button
            enableSearchButton(false);

            // Prevent the form from submitting via the browser.
            event.preventDefault();

            searchViaAjax();

        });

    });

    function searchViaAjax() {

        var search = {}
        search["username"] = $("#username").val();
        search["email"] = $("#email").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${home}search/api/getSearchResult",
            data: JSON.stringify(search),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                display(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                display(e);
            },
            done: function (e) {
                console.log("DONE");
                enableSearchButton(true);
            }
        });

    }

    function enableSearchButton(flag) {
        $("#btn-search").prop("disabled", flag);
    }

    function display(data) {
        var json = "<h4>Ajax Response</h4><pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
        $('#feedback').html(json);
    }
</script>

</body>
</html>