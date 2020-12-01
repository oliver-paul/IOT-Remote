package com.example.iot_remote;

public class Getter_setter
{
    String name;
    String email;

    public Getter_setter(){}

    public Getter_setter(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {        this.name = name;    }

    public void setEmail(String email) {        this.email = email;    }

    public String getName() {        return name;    }

    public String getEmail() {        return email;    }
}
