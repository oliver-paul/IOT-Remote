package com.example.iot_remote;

public class get_set
{
    String d_name;
    String r_name;

    public get_set() {
    }

    public get_set(String d_name, String r_name) {
        this.d_name = d_name;
        this.r_name = r_name;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }
}
