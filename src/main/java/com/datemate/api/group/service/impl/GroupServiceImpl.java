package com.datemate.api.group.service.impl;

import com.datemate.api.group.dao.GroupRepository;
import com.datemate.api.group.model.Group;
import com.datemate.api.group.service.GroupService;
import com.datemate.api.user.dao.UserGroupRepository;
import com.datemate.api.user.model.UserGroup;
import com.datemate.common.constants.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupRepository groupRepository;

    @Resource
    private UserGroupRepository userGroupRepository;

    @Override
    public Group selectGroup(int groupId) {
        return groupRepository.getById(groupId);
    }

    @Override
    public List<UserGroup> selectGroupListByUserSeq(int userSeq) {
        return userGroupRepository.findByUserSeq(userSeq);
    }

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void inviteGroup(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    @Override
    public void deleteGroup(int groupId) {
        userGroupRepository.deleteAllByGroupId(groupId);
        groupRepository.deleteById(groupId);
    }

    @Override
    public void leaveGroup(UserGroup userGroup) {
        userGroup.setStatus(Constants.INACTIVE);
        userGroupRepository.save(userGroup);
    }
}
