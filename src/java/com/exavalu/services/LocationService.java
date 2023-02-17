/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.Province;
import com.exavalu.models.State;
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
public class LocationService {

    private static final Logger log = Logger.getLogger(LocationService.class);

    public static ArrayList getAllCountry() {
        ArrayList contList = new ArrayList();
        String sql = "select * from countries";
        Connection con = JDBCConnectionManager.getConnection();
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Country cont = new Country();
                cont.setCountryCode(rs.getString("countryCode"));
                cont.setCountryName(rs.getString("countryName"));
                contList.add(cont);

            }
        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);

        }
        return contList;

    }

    public static ArrayList getAllState(String countryCode) {
        ArrayList stateList = new ArrayList();
        String sql = "select * from state where countryCode=?";
        Connection con = JDBCConnectionManager.getConnection();
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, countryCode);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                State state = new State();
                state.setStateCode(rs.getString("stateCode"));
                state.setStateName(rs.getString("stateName"));
                stateList.add(state);

            }
        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);

        }
        return stateList;

    }

    public static ArrayList getAllProvince(String provincecode) {
        ArrayList districtList = new ArrayList();
        String sql = "select * from districts where stateCode=?";
        Connection con = JDBCConnectionManager.getConnection();
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, provincecode);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Province province = new Province();
                province.setProvinceCode(rs.getString("districtCode"));
                province.setProvinceName(rs.getString("districtName"));
                districtList.add(province);

            }
        } catch (SQLException ex) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Construct the error message with date and time
            String errorMessage = timestamp.toString() + ": " + ex.getMessage();
            log.error(errorMessage);
        }
        return districtList;
    }

}
