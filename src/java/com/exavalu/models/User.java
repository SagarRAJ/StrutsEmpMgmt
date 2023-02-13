/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.EmployeeService;
import com.exavalu.services.LocationService;
import com.exavalu.services.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Win10
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public ApplicationMap getMap() {
        return map;
    }

    public void setMap(ApplicationMap map) {
        this.map = map;
    }
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
        sessionMap.put("Loggedin", NONE);
    }

    public String doPreSignUp() throws SQLException {
        String result = "FAILURE";

        ArrayList contList = LocationService.getAllCountry();
        sessionMap.put("ContList", contList);
        result = "SUCCESS";

        if (this.countryCode != null) {
            ArrayList stateList = LocationService.getAllState(this.countryCode);
            sessionMap.put("StateList", stateList);
            sessionMap.put("User", this);
            result = "SUCCESS";
        }
        if (this.countryCode != null && this.stateCode != null) {
            ArrayList districtList = LocationService.getAllProvince(this.stateCode);
            sessionMap.put("DistrictList", districtList);
            sessionMap.put("User", this);
        }
        if (this.countryCode != null && !"".equals(this.countryCode) && this.stateCode != null && this.stateCode != "" && this.provinceCode != null && this.provinceCode != "") {
            boolean success = LoginService.getInstance().doSignUp(this);
            if (success) {
                ArrayList empList = EmployeeService.getInstance().getAllEmployees();
                sessionMap.put("EmpList", empList);
                System.out.println("returning Success from doLogin method");
                sessionMap.put("Loggedin", this);
                result = "FAILURE";
            } else {
                System.out.println("returning Failure from doLogin method");
                sessionMap.put("Loggedin", null);
            }
        }

        return result;

    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(this);

        if (success) {
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            System.out.println("returning Success from doLogin method");
            sessionMap.put("Loggedin", this);
            result = "SUCCESS";
        } else {
            System.out.println("returning Failure from doLogin method");
            sessionMap.put("Loggedin", null);
        }

        return result;
    }

    public String doLogOut() {
        String result = "SUCCESS";
        sessionMap.clear();
        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    private String email;
    private String password;
    private String Firstname;
    private String LastName;
    private String countryCode;
    private String provinceCode;
    private String stateCode;
    private String phoneNumber;
    private String address;

}
