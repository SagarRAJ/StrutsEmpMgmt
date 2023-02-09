/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.Province;
import com.exavalu.models.State;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Win10
 */
public class LocationService {

    public static ArrayList getAllCountry() throws SQLException {
        ArrayList contList = new ArrayList();
        String sql = "select * from countries";
        Connection con = JDBCConnectionManager.getConnection();
        PreparedStatement prepareStatement = con.prepareStatement(sql);
        ResultSet rs = prepareStatement.executeQuery();
        while (rs.next()) {
            Country cont = new Country();
            cont.setCountryCode(rs.getString("countryCode"));
            cont.setCountryName(rs.getString("countryName"));
            contList.add(cont);

        }
        return contList;

    }

    public static ArrayList getAllState(String countryCode) throws SQLException {
        ArrayList stateList = new ArrayList();
        String sql = "select * from state where countryCode=?";
        Connection con = JDBCConnectionManager.getConnection();
        PreparedStatement prepareStatement = con.prepareStatement(sql);
        prepareStatement.setString(1, countryCode);
        ResultSet rs = prepareStatement.executeQuery();
        while (rs.next()) {
            State state = new State();
            state.setStateCode(rs.getString("stateCode"));
            state.setStateName(rs.getString("stateName"));
            stateList.add(state);

        }
        return stateList;

    }

    public static ArrayList getAllProvince(String provincecode) throws SQLException {
        ArrayList districtList = new ArrayList();
        String sql = "select * from districts where stateCode=?";
        Connection con = JDBCConnectionManager.getConnection();
        PreparedStatement prepareStatement = con.prepareStatement(sql);
        prepareStatement.setString(1, provincecode);
        ResultSet rs = prepareStatement.executeQuery();
        while (rs.next()) {
            Province province = new Province();
            province.setProvinceCode(rs.getString("districtCode"));
            province.setProvinceName(rs.getString("districtName"));
            districtList.add(province);

        }
        return districtList;
    }

}
