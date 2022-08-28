package com.example.ia;

import java.util.ArrayList;

public class User {
    public String passcode;
    public boolean isLogIn;

    public User()
    {

    }

    public User(String passcode)
    {
        this.passcode = passcode;
    }

    public String getPasscode()
    {
        return passcode;
    }

}
