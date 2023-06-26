package com.api.adminhr.service;

import com.api.adminhr.model.User;

public interface UserService {
    User registerUser(String phoneNumber, String name, String password);

    boolean validateUser(String phoneNumber, String password);

    User getUserByPhoneNumber(String phoneNumber);

    String getNameByPhoneNumber(String phoneNumber);

    boolean doesUserExist(String phoneNumber);

    User updateUserName(String phoneNumber, String newName);

    void updateNameByPhoneNumber(String phoneNumber, String newName);
}
