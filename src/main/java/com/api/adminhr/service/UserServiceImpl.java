package com.api.adminhr.service;

import com.api.adminhr.model.User;
import com.api.adminhr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String phoneNumber, String name, String password) {
        // Validate phone number
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.length() < 10 || phoneNumber.length() > 13
                || !phoneNumber.startsWith("08")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        // Validate name
        if (name == null || name.isEmpty() || name.length() > 60) {
            throw new IllegalArgumentException("Invalid name");
        }

        // Validate password
        if (password == null || password.isEmpty() || password.length() < 6 || password.length() > 16
                || !password.matches("^(?=.*[A-Z])(?=.*[0-9]).*$")) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Salt and hash the password
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setName(name);
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    @Override
    public boolean validateUser(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User updateUserName(String phoneNumber, String newName) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            user.setName(newName);
            return userRepository.save(user);
        }
        return null;
    }
}
