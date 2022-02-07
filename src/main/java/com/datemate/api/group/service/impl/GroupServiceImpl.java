package com.datemate.api.group.service.impl;

import com.datemate.api.group.dao.GroupRepository;
import com.datemate.api.group.model.Group;
import com.datemate.api.group.service.GroupService;
import com.datemate.api.user.dao.UserGroupRepository;
import com.datemate.api.user.model.UserGroup;
import com.datemate.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupRepository groupRepository;

    @Resource
    private UserGroupRepository userGroupRepository;

    @Override
    public Group selectGroup(int groupId) throws Exception {
        return groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("No such Data"));
    }

    @Override
    public List<UserGroup> selectGroupListByUserSeq(int userSeq) {
        return userGroupRepository.findByUserSeq(userSeq);
    }

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
        userGroupRepository.save(new UserGroup(group.getGroupOwner(), group.getGroupId()));
    }

    @Override
    public void inviteGroup(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    @Override
    @Transactional
    public void deleteGroup(int groupId) {
        userGroupRepository.deleteAllByGroupId(groupId);
        groupRepository.deleteById(groupId);
    }

    @Override
    public void leaveGroup(UserGroup userGroup) {
        userGroup.setStatus(Constants.INACTIVE);
        userGroupRepository.save(userGroup);
    }

    @Override
    public Long countGroupUser(Integer groupId) {
        return userGroupRepository.countUserGroupByGroupId(groupId);
    }
}
