/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Api;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Win10
 */
public class ApiDbOperation {

    static boolean doInsert(Api[] obj) throws SQLException {
        Connection con = JDBCConnectionManager.getConnection();

        for (Api ap : obj) {
            int rs = 0;
            String sql = "INSERT INTO employeedb2.api\n"
                    + "(userId,\n"
                    + "id,\n"
                    + "title,\n"
                    + "completed)\n"
                    + "VALUES\n"
                    + "(? ,\n"
                    + "? ,\n"
                    + "? ,\n"
                    + "? )";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ap.getUserId());
            preparedStatement.setString(2, ap.getId());
            preparedStatement.setString(3, ap.getTitle());
            preparedStatement.setString(4, ap.getCompleted());
            rs = preparedStatement.executeUpdate();

        }
        return true;
    }

}