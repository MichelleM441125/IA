package com.example.ia;

import java.util.ArrayList;

public class User {
    public String passcode;
    public boolean isLogIn;


    public User(String passcode, boolean isLogIn)
    {
        this.passcode = passcode;
        this.isLogIn = isLogIn;
    }

    public String getPasscode()
    {
        return passcode;
    }

    public boolean getIsLogIn() {
        return isLogIn;
    }
}
