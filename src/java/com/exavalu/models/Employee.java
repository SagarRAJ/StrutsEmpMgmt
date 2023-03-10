/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LocationService;
import com.exavalu.services.RoleService;
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
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

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

    private String employeeId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private String age;
    private String departmentId;
    private String roleId;
    private String basicSalary;
    private String carAllaowance;
    private String departmentName;
    private String roleName;
    private String countryName;
    private String countryId;
    private String stateName;
    private String stateId;
    private String districtName;
    private String distrctId;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrctId() {
        return distrctId;
    }

    public void setDistrctId(String distrctId) {
        this.distrctId = distrctId;
    }

    private int status;

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    /**
     * @return the password
     */
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getCarAllaowance() {
        return carAllaowance;
    }

    public void setCarAllaowance(String carAllaowance) {
        this.carAllaowance = carAllaowance;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String doSearch() throws SQLException {
        String result = "FAILURE";

        ArrayList DeptList = DepartmentService.getAllDepartment();
        sessionMap.put("DeptList", DeptList);
        ArrayList RoleList = RoleService.getAllRole();

        sessionMap.put("RoleList", RoleList);
        ArrayList EmpList = EmployeeService.getInstance().doSearch(this);

        if (!EmpList.isEmpty()) {

            sessionMap.put("EmpList", EmpList);
            result = "SUCCESS";
        }

        return result;
    }

    public String doAdd() throws SQLException {
        String result = "SUCCESS";
        int r = EmployeeService.doAdd(this);
        ArrayList DeptList = DepartmentService.getAllDepartment();
        sessionMap.put("DeptList", DeptList);
        ArrayList RoleList = RoleService.getAllRole();
        sessionMap.put("RoleList", RoleList);

        ArrayList empList = EmployeeService.getInstance().getAllEmployees();
        sessionMap.put("EmpList", empList);

        return result;
    }

    public String doPreAdd() {
        String result = "SUCCESS";

        ArrayList DeptList = DepartmentService.getAllDepartment();
        sessionMap.put("DeptList", DeptList);
        ArrayList RoleList = RoleService.getAllRole();
        sessionMap.put("RoleList", RoleList);

        return result;
    }

    public String doEdit() throws SQLException {
        String result = "SUCCESS";
        Employee emp = EmployeeService.getEmployee(this.employeeId);
        sessionMap.put("Emp", emp);

        ArrayList DeptList = DepartmentService.getAllDepartment();
        sessionMap.put("DeptList", DeptList);
        ArrayList RoleList = RoleService.getAllRole();
        sessionMap.put("RoleList", RoleList);

        ArrayList empList = EmployeeService.getInstance().getAllEmployees();
        sessionMap.put("EmpList", empList);

        return result;
    }

    public String doUpdate() throws SQLException {
        String result = "FAILURE";
        boolean update = EmployeeService.doUpdate(this);
        if (update == true) {
            result = "SUCCESS";
        }

        return result;
    }

    public String doDelete() throws SQLException {
        String result = "FAILURE";

        boolean del = EmployeeService.doDelete(this.employeeId);
        if (del == true) {
            result = "SUCCESS";
        }
        return result;
    }

}
