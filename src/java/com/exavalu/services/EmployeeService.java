/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {

    public static EmployeeService employeeService = null;

    public static EmployeeService getInstance() {
        if (employeeService == null) {
            return new EmployeeService();
        } else {
            return employeeService;
        }
    }
    public static final Logger log = Logger.getLogger(EmployeeService.class);

    public static int doAdd(Employee emp) {

        Connection con = JDBCConnectionManager.getConnection();
        String sql = "INSERT INTO employeedb2.employees(firstName,lastName,phone,address,gender,age,departmentId,roleId,basicSalary,carAllaowance) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
        int rs = 0;
        System.out.println(rs);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());

            preparedStatement.setString(7, emp.getDepartmentName());
            preparedStatement.setString(8, emp.getRoleName());
            preparedStatement.setString(9, emp.getBasicSalary());
            preparedStatement.setString(10, emp.getCarAllaowance());
            System.out.println("sql:" + preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("sql" + preparedStatement);

        } catch (SQLException ex) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);

        }
        System.out.println(rs);

        return rs;

    }

    public static Employee getEmployee(String employeeId) {
        Employee emp = new Employee();

        Connection con = JDBCConnectionManager.getConnection();
        String sql = "Select * from employees e, department d, roles r where e.departmentId=d.departmentId and e.roleId=r.rolesId having employeeId=?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            System.out.println("preparedStatement:" + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                emp.setEmployeeId(rs.getString("employeeId"));

                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setAddress(rs.getString("address"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("RolesName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllaowance(rs.getString("carAllaowance"));

            }

        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);

        }
        return emp;

    }

    public static boolean doUpdate(Employee emp) {

        boolean result = false;
        int row = 0;
        Connection con = JDBCConnectionManager.getConnection();
        String sql = "UPDATE employeedb2.employees "
                + "SET firstName = ? ,lastName = ? ,phone = ? ,address = ? ,gender = ? ,age = ? ,departmentId = ? ,roleId = ? ,basicSalary = ? ,carAllaowance = ?"
                + "WHERE employeeId = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setInt(7, Integer.parseInt(emp.getDepartmentId()));
            preparedStatement.setInt(8, Integer.parseInt(emp.getRoleId()));
            preparedStatement.setDouble(9, Double.parseDouble(emp.getBasicSalary()));
            preparedStatement.setDouble(10, Double.parseDouble(emp.getCarAllaowance()));

            preparedStatement.setInt(11, Integer.parseInt(emp.getEmployeeId()));

            System.out.println("SQL = " + preparedStatement);
            row = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }

        if (row == 1) {
            result = true;
        }

        return result;
    }

    public static boolean doDelete(String employeeId) {
        boolean b = false;
        int row = 0;
        Connection con = JDBCConnectionManager.getConnection();
        String sql = "UPDATE employeedb2.employees SET status=0 where employeeId=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            row = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }

        if (row == 1) {
            b = true;
        }

        return b;
    }

    public ArrayList doSearch(Employee emp) {
        ArrayList empList = new ArrayList<>();

        Connection con = JDBCConnectionManager.getConnection();
        String sql = "select * from employees e, department d, roles r where e.departmentId=d.departmentId and e.roleId=r.rolesId and status=1\n"
                + " having firstName like ?\n"
                + " and lastName like ? \n"
                + " and gender like ? \n"
                + " and departmentName like ? \n"
                + " and rolesName like ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emp.getFirstName() + "%");
            preparedStatement.setString(2, emp.getLastName() + "%");
            preparedStatement.setString(3, emp.getGender() + "%");
            preparedStatement.setString(4, emp.getDepartmentName() + "%");
            preparedStatement.setString(5, emp.getRoleName() + "%");
            System.out.println("Prepared statement = " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getString("employeeId"));

                employee.setFirstName(rs.getString("firstName"));
                employee.setLastName(rs.getString("lastName"));
                employee.setAddress(rs.getString("address"));
                employee.setPhone(rs.getString("phone"));
                employee.setGender(rs.getString("gender"));
                employee.setAge(rs.getString("age"));
                employee.setDepartmentName(rs.getString("departmentName"));
                employee.setRoleName(rs.getString("RolesName"));
                employee.setBasicSalary(rs.getString("basicSalary"));
                employee.setCarAllaowance(rs.getString("carAllaowance"));

                empList.add(employee);
            }
        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);

        }
//            Iterator itr=empList.iterator();
//            while(itr.hasNext()){
//                Employee e=(Employee)itr.next();
//                System.out.println(e);
//            }

        return empList;

    }

    public ArrayList getAllEmployees() {
        ArrayList empList = new ArrayList();
        String sql = "select * from employees e, department d, roles r "
                + "where e.departmentId=d.departmentId and e.roleId=r.rolesId and status=1 ";
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();

                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("rolesName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllaowance(rs.getString("carAllaowance"));
                System.out.println("love:" + emp.getFirstName());

                empList.add(emp);
            }

        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }
        System.err.println("Total rows:" + empList.size());
        return empList;
    }

}
