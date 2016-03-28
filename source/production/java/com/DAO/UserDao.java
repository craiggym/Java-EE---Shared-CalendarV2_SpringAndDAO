package com.DAO;

import com.Calendar.User;

public interface UserDao {
    void createUserTable();
    void dropUserTable();
    void insertUser(User user);
    String selectUser(int id);
}
