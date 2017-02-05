package com.example.chinz_000.silverchef;

/**
 * Created by chinz_000 on 5/2/2017.
 */
public class User{
    private int user_id;
    private String name;
    private boolean old;
    private int tel;

    public User() {}
    public User(int userId, boolean age) {
        user_id = userId;
        old = age;
    }

    public void setUser(int userId, boolean age){
        user_id = userId;
        old = age;
    }
    public void setName(String Name) {
        name = Name;
    }
    public void setTel(int Tel) {
        tel = Tel;
    }

    public int getId() {
        return user_id;
    }

    public boolean getAge() {
        return old;
    }

    public int getTel() {return tel; }

    public String getName() {return name;}
}
