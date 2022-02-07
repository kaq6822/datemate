package com.datemate.api.group.service;

import com.datemate.api.group.model.Group;
import com.datemate.api.user.model.UserGroup;

import java.util.List;

public interface GroupService {
    Group selectGroup(int groupId) throws Exception;

    List<UserGroup> selectGroupListByUserSeq(int userSeq);

    void addGroup(Group group);

    void inviteGroup(UserGroup userGroup);

    void deleteGroup(int groupId);

    void leaveGroup(UserGroup userGroup);

    Long countGroupUser(Integer groupId);
}
