package com.datemate.api.user.service.impl;

import com.datemate.api.user.dao.UserRelationRepository;
import com.datemate.api.user.model.User;
import com.datemate.api.user.dao.UserRepository;
import com.datemate.api.user.model.UserRelation;
import com.datemate.api.user.model.pk.UserRelationId;
import com.datemate.api.user.service.UserService;
import com.datemate.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRelationRepository userRelationRepository;

    @Override
    public User selectUserByEmail(String email) {
        log.debug("select User: {}(email)", email);
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User selectUserBySeq(Integer userSeq) {
        log.debug("select User: {}(email)", userSeq);
        return userRepository.findById(userSeq).get();
    }

    @Override
    public void updateUser(User user) {
        log.debug("update User: {}", user);
        userRepository.save(user);
    }

    @Override
    public List<UserRelation> selectRelationUserList(Integer userSeq) {
        return userRelationRepository.findByUserSeq(userSeq);
    }

    @Override
    public UserRelation selectRelationUser(Integer userSeq, Integer targetUserSeq) {
        return userRelationRepository.findById(new UserRelationId(userSeq, targetUserSeq)).get();
    }

    @Override
    public void addUserRelation(Integer userSeq, Integer targetUserSeq) {
        UserRelation userRelation = new UserRelation(userSeq, targetUserSeq, Constants.WAITING);
        userRelationRepository.save(userRelation);

        UserRelation targetUserRelation = new UserRelation(targetUserSeq, userSeq, Constants.APPROVAL_REQUEST);
        userRelationRepository.save(targetUserRelation);
    }

    @Override
    public void acceptUserRelation(Integer userSeq, Integer targetUserSeq) {
        UserRelation userRelation = new UserRelation(userSeq, targetUserSeq, Constants.COMPLETE);
        userRelationRepository.save(userRelation);

        UserRelation targetUserRelation = new UserRelation(targetUserSeq, userSeq, Constants.COMPLETE);
        userRelationRepository.save(targetUserRelation);
    }

    @Override
    public void deleteUserRelation(Integer userSeq, Integer targetUserSeq) {
        userRelationRepository.deleteById(new UserRelationId(userSeq, targetUserSeq));
        userRelationRepository.deleteById(new UserRelationId(targetUserSeq, userSeq));
    }

}
