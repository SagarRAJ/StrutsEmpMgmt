/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Role;
import static com.exavalu.services.EmployeeService.log;
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
 * @author Win10
 */
public class RoleService {

    private static final Logger log = Logger.getLogger(RoleService.class);

    public static ArrayList getAllRole() {
        ArrayList roleList = new ArrayList();
        Connection con = JDBCConnectionManager.getConnection();
        String sql = "Select * from roles";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getString("rolesId"));
                role.setRoleName(rs.getString("rolesName"));
                roleList.add(role);

            }

        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);

        }

        return roleList;
    }

}
