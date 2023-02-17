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
import java.sql.Timestamp;
import org.apache.log4j.Logger;

/**
 *
 * @author Win10
 */
public class ApiDbOperation {

    static boolean doInsert(Api[] obj) {
        Connection con = JDBCConnectionManager.getConnection();

        for (Api data : obj) {
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
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, data.getUserId());
                preparedStatement.setString(2, data.getId());
                preparedStatement.setString(3, data.getTitle());
                preparedStatement.setString(4, data.getCompleted());
                rs = preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger log = Logger.getLogger(ApiDbOperation.class);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                // Construct the error message with date and time
                String errorMessage = timestamp.toString() + ": " + ex.getMessage();
                log.error(errorMessage);
            }

        }
        return true;
    }

}
