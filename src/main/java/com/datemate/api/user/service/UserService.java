package com.datemate.api.user.service;

import com.datemate.api.user.model.User;
import com.datemate.api.user.model.UserRelation;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserService {
    User selectUserByEmail(String email);

    User selectUserBySeq(Integer userSeq);

    void updateUser(User user);

    List<UserRelation> selectRelationUserList(Integer userSeq);

    List<UserRelation> selectRelationUserList(Integer userSeq, Character status);

    UserRelation selectRelationUser(Integer userSeq, Integer targetUserSeq) throws EntityNotFoundException;

    void addUserRelation(Integer userSeq, Integer targetUserSeq);

    void acceptUserRelation(Integer userSeq, Integer targetUserSeq);

    void deleteUserRelation(Integer userSeq, Integer targetUserSeq);
}
