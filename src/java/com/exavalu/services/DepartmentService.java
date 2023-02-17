/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Department;
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
public class DepartmentService {

    public static ArrayList getAllDepartment() {

        ArrayList deptList = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();

            String sql = "Select * from department";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Department dept = new Department();

                dept.setDepartmentId(rs.getString("departmentId"));
                dept.setDepartmentName(rs.getString("departmentName"));

                deptList.add(dept);

            }

        } catch (SQLException ex) {
            Logger log = Logger.getLogger(DepartmentService.class);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }

        return deptList;
    }

}
