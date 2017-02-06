package com.example.chinz_000.silverchef;

/**
 * Created by chinz_000 on 5/2/2017.
 */
public class User{
    private String name;
    private boolean old;
    private int tel; //tel serves as id

    public User() {}
    public User(boolean age) {
        old = age;
    }

    public void setUser(boolean age){
        old = age;
    }
    public void setName(String Name) {
        name = Name;
    }
    public void setTel(int Tel) {
        tel = Tel;
    }


    public boolean getAge() {
        return old;
    }

    public int getTel() {return tel; }

    public String getName() {return name;}
}
