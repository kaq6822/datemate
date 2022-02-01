package com.datemate.api.user.service;

import com.datemate.api.user.model.User;

public interface UserService {
    User selectUserByEmail(String email) ;

    User selectUserBySeq(Integer userSeq) ;

    void updateUser(User user) ;
}
