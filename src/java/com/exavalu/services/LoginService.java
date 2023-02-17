/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class LoginService {

    public static LoginService loginService = null;
    private static final Logger log = Logger.getLogger(LoginService.class);

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (loginService == null) {
            return new LoginService();
        } else {
            return loginService;
        }
    }

    public boolean doLogin(User user) {
        boolean success = false;

        String sql = "Select * from users where emailAddress=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = "SQL error occurred at " + timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }

        return success;
    }

    public boolean doSignUp(User user) throws SQLException {
        Connection con = JDBCConnectionManager.getConnection();
        try {
            String sql = "INSERT INTO employeedb2.users(emailAddress,password,firstName,lastName,countryCode,stateCode,provinceCode,phoneNumber,address)\n"
                    + "VALUES(?,? ,? ,? ,? ,? ,? ,?,? )";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, user.getEmail());
            prepareStatement.setString(2, user.getPassword());

            prepareStatement.setString(3, user.getFirstname());

            prepareStatement.setString(4, user.getLastName());

            prepareStatement.setString(5, user.getCountryCode());

            prepareStatement.setString(6, user.getStateCode());

            prepareStatement.setString(7, user.getProvinceCode());

            prepareStatement.setString(8, user.getPhoneNumber());
            prepareStatement.setString(9, user.getAddress());

            int rs = prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }
        return true;

    }

}
