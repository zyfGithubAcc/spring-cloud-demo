package com.fufu.service;

import com.fufu.domain.User;

public interface UserService {
    void saveUser(User user);
    void createUser(User user);
    User findUserByUsername(String username);
}
