<%-- 
    Document   : landingPage
    Created on : 22-Dec-2022, 12:00:33 PM
    Author     : Avijit Chattopadhyay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:if test="${Loggedin==null}">
    <c:redirect url="landingPage.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        <link href="css/header.css" rel="stylesheet">-->
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="css/reset-min.css">
        <link rel="stylesheet" href="css/algolia-min.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/docs.min.css">
        <link rel="stylesheet" href="css/index.css">
        <title>Employee Management</title>
    </head>
    
    <!<!-- I want to check my session before showing any content to the user -->




    <jsp:include page="menu.jsp"></jsp:include>

        <div id="example">
            <script>
                init({
                    title: 'Large data',
                    desc: 'Use `virtualScroll` to enable the virtual scroll to play with large data sets (10000 rows).',
                    links: ['bootstrap-table.min.css'],
                    scripts: ['bootstrap-table.min.js']
                })
            </script>



            <div class="bootstrap-table bootstrap5">
                <div class="fixed-table-toolbar"><div class="bs-bars float-left">

                    </div><div class="columns columns-right btn-group float-right"><div class="keep-open btn-group" title="Columns">
                            <div class="dropdown-menu dropdown-menu-right" style=""><label class="dropdown-item dropdown-item-marker"><input type="checkbox" data-field="id" value="0" checked="checked"> <span>ID</span></label><label class="dropdown-item dropdown-item-marker"><input type="checkbox" data-field="name" value="1" checked="checked"> <span>Item Name</span></label><label class="dropdown-item dropdown-item-marker"><input type="checkbox" data-field="price" value="2" checked="checked"> <span>Item Price</span></label></div></div></div></div>

                <div class="table-responsive" style="height: 542px; padding-bottom: 50.5px;">

                    <div class="fixed-table-body">
                        <table id="table" data-height="400" data-virtual-scroll="true" class="table table-bordered table-hover" style="margin-top: -9.5px;">
                            <thead>
                            <c:choose>
                                <c:when test = "${requestScope.noData != null}">
                                    <tr>
                                        <td colspan="5">
                                            <h2 style="border:2px solid rgb(255, 99, 71); background-color:rgba(255, 99, 71, 0.5); font-size:15px;">
                                                <c:out value="${requestScope.noData}"> </c:out>
                                                </h2>
                                            </td>
                                        </tr>
                                </c:when>
                            </c:choose>
                            <tr>
                                <th scope="col">
                                    Employee Id
                                </th>
                                <th scope="col">
                                    First Name
                                </th>
                                <th scope="col">
                                    Last Name
                                </th>
                                <th scope="col">
                                    Address
                                </th>
                                <th scope="col">
                                    Phone
                                </th>
                                <th scope="col">
                                    Gender
                                </th>
                                <th scope="col">
                                    Age
                                </th>
                                <th scope="col">
                                    Department
                                </th>
                                <th scope="col">
                                    Role
                                </th>
                                <th scope="col">
                                    Basic Salary
                                </th>
                                <th scope="col">
                                    Car Allowance
                                </th>
                                <th scope="col">
                                    Action
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${EmpList}" var="emp">
                                <tr>
                                    <td>
                                        ${emp.getEmployeeId()}
                                    </td>
                                    <td>
                                        ${emp.getFirstName()}
                                    </td>
                                    <td>
                                        ${emp.getLastName()}
                                    </td>
                                    <td>
                                        ${emp.getAddress()}
                                    </td>
                                    <td>
                                        ${emp.getPhone()}
                                    </td>
                                    <td>
                                        ${emp.getGender()}
                                    </td>
                                    <td>
                                        ${emp.getAge()}
                                    </td>
                                    <td>
                                        ${emp.getDepartmentName()}
                                    </td>
                                    <td>
                                        ${emp.getRoleName()}
                                    </td>
                                    <td>
                                        ${emp.getBasicSalary()}
                                    </td>
                                    <td>
                                        ${emp.getCarAllaowance()}
                                    </td>
                                    <td> 

                                        <a href='EditEmpolyee?employeeId=${emp.employeeId}'>
                                            <button class="btn-dark">Edit</button>
                                        </a>
                                             <a href='DeleteEmployee?employeeId=${emp.employeeId}'>
                                    <button class="btn-dark">Delete</button>
                                </a>

                                    </td>
                                    
                                </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <div class="fixed-table-footer" style="display: none;"></div>
                </div>
                <div class="fixed-table-pagination" style="display: none;"></div>
            </div>
            <div class="clearfix"></div>

            <script>
                var $table = $('#table')
                var total = 0

                function getData(number, isAppend) {
                    if (!isAppend) {
                        total = 0
                    }
                    var data = []
                    for (var i = total; i < total + number; i++) {
                        data.push({
                            'id': i,
                            'name': 'Item ' + i,
                            'price': '$' + i
                        })
                    }
                    if (isAppend) {
                        total += number
                    } else {
                        total = number
                    }
                    $('#total').text(total)
                    return data
                }

                function mounted() {
                    $table.bootstrapTable({data: getData(20)})

                    $('#load').click(function () {
                        $table.bootstrapTable('load', getData(10000))
                    })

                    $('#append').click(function () {
                        $table.bootstrapTable('append', getData(10000, true))
                    })
                }
            </script>
        </div>

</html>
