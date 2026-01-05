
package com.example.androidproject.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    User findByUser(String email, String password);

    @Query("SELECT * FROM users WHERE email = :email")
    User findByEmail(String email);

    @Update
    void update(User user);
}
