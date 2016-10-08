<%-- 
    Document   : hobbies
    Created on : 08-10-2016, 13:16:32
    Author     : HazemSaeid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="page-header">
            <h2>Add a hobby to a person:</h2>
        </div>
        <div class="row">
            <form class="form-inline">
                <div class="form-group col-md-4">
                    <label>ID of the Name</label>
                    <input type="text" class="form-control" id="ID">
                </div>
                <div class="form-group col-md-4">
                    <label for="">Hobby</label>
                    <input type="text" class="form-control" id="hobby">
                </div>
                <div class="form-group col-md-4">
                    <label for="email">description:</label>
                    <input type="text" class="form-control" id="description" required>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-md-2">
                <a href="http://localhost:8084/CA2REST/people.jsp">
                <button style="margin-top: 20px;" id="add" type="submit" class="btn btn-primary">Submit</button>
                </a>
            </div>
        </div>
    </body>
</html>
<%@ include file="footer.jsp" %>