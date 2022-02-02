package com.datemate.api.user.service;

import com.datemate.api.user.model.User;
import com.datemate.api.user.model.UserRelation;

import java.util.List;

public interface UserService {
    User selectUserByEmail(String email);

    User selectUserBySeq(Integer userSeq);

    void updateUser(User user);

    List<UserRelation> selectRelationUserList(Integer userSeq);

    UserRelation selectRelationUser(Integer userSeq, Integer targetUserSeq);

    void addUserRelation(Integer userSeq, Integer targetUserSeq);

    void acceptUserRelation(Integer userSeq, Integer targetUserSeq);

    void deleteUserRelation(Integer userSeq, Integer targetUserSeq);
}
