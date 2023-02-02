<%-- 
    Document   : addemployee
    Created on : 02-Feb-2023, 10:13:21 pm
    Author     : Win10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Employee</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <form action="AddEmployee" method="post">
                <div class="form-group mb-3" >

                    <input type="text" class="form-control"   placeholder="First Name" name="firstName">

                </div>
                <div class="form-group mb-3">

                    <input type="text" class="form-control"   placeholder="lastName" name="lastName">

                </div>
                <div class="form-group mb-3">

                    <input type="text" class="form-control"   placeholder="Address" name="address">

                </div>
                <div class="form-group mb-3">

                    <input type="text" class="form-control"   placeholder="age" name="age">

                </div>
                <div class="form-group mb-3">

                    <input type="text" class="form-control"   placeholder="Phone" name="phone">

                </div>
                <div class="form-group mb-3">

                    <select name="Gender" class="form-control">
                        <option value="" disabled>select the gender</option>
                        <option value="male">male</option>
                        <option value="female">female</option>

                    </select>

                </div>
                <div class="form-group mb-3">
                    <select name="departmentName" class="form-select" id="departmentId">
                        <option value="" >Select a Department</option>
                    <c:forEach items="${DeptList}" var="dept">
                        <option value="${dept.getDepartmentId()}"> ${dept.getDepartmentName()}  </option>
                    </c:forEach>
                </select>

            </div>
            <div class="form-group mb-3">


                <select name="roleName" class="form-select" id="roleId">
                    <option value="" >Select a Role</option>

                    <c:forEach items="${RoleList}" var="role">
                        <option value="${role.getRoleId()}"> ${role.getRoleName()}  </option>
                    </c:forEach>
                </select>

            </div>
            <div class="form-group mb-3">

                <input type="text" class="form-control"   placeholder="Basic Salary" name="basicSalary">

            </div>
            <div class="form-group mb-3">

                <input type="text" class="form-control"   placeholder="Car Allowance" name="carAllaowance">

            </div>


            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </body>
</html>
