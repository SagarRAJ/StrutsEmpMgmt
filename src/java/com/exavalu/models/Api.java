/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.ApiConsume;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Win10
 */
public class Api {

    private String userId;
    private String id;
    private String title;
    private String completed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String doApiCall() throws IOException, InterruptedException, SQLException {
        String result = "FAILURE";

        boolean res = ApiConsume.apiConsume();
        if (res == true) {
            result = "SUCCESS";
        }
        return result;
    }

}
