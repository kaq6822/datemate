package com.datemate.api.group.service.impl;

import com.datemate.api.group.dao.GroupRepository;
import com.datemate.api.group.model.Group;
import com.datemate.api.group.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupRepository groupRepository;

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }
}
