package com.datemate.api.user.service.impl;

import com.datemate.api.user.model.User;
import com.datemate.api.user.dao.UserRepository;
import com.datemate.api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User selectUserByEmail(String email) {
        log.debug("select User: {}(email)", email);
        return userRepository.findById(email).get();
    }

    @Override
    public User selectUserBySeq(Integer userSeq) {
        log.debug("select User: {}(email)", userSeq);
        return userRepository.findByUserSeq(userSeq).get();
    }

    @Override
    public void updateUser(User user) {
        log.debug("update User: {}", user);
        userRepository.save(user);
    }

}
