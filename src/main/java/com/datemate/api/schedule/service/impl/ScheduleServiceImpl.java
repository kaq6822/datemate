package com.datemate.api.schedule.service.impl;

import com.datemate.api.schedule.dao.ScheduleGroupRepository;
import com.datemate.api.schedule.dao.ScheduleGroupUserRepository;
import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.id.ScheduleGroupId;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import com.datemate.api.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleGroupRepository scheduleGroupRepository;

    @Resource
    private ScheduleGroupUserRepository scheduleGroupUserRepository;

    @Override
    public List<ScheduleGroup> selectConfirmScheduleByGroupId(Integer groupId) {
        return scheduleGroupRepository.getScheduleGroupByGroupId(groupId);
    }

    @Override
    public List<ScheduleGroupUser> selectCheckScheduleListByGroupId(Integer groupId) {
        return scheduleGroupUserRepository.getScheduleGroupUserByGroupIdAndCheckYn(groupId, 'Y');
    }

    @Override
    public void checkGroupSchedule(ScheduleGroupUserId scheduleGroupUserId) {
        scheduleGroupUserRepository.save(new ScheduleGroupUser(scheduleGroupUserId, 'Y', null));
    }

    @Override
    public void uncheckGroupSchedule(ScheduleGroupUserId scheduleGroupUserId) {
        scheduleGroupUserRepository.deleteById(scheduleGroupUserId);
    }

    @Override
    public void confirmGroupScheduleYN(ScheduleGroupUserId scheduleGroupUserId, Character confirmYN) {
        // TODO: Will find more better way
        ScheduleGroupUser scheduleGroupUser = scheduleGroupUserRepository.findById(scheduleGroupUserId).orElseThrow();
        scheduleGroupUser.setConfirmYn(confirmYN);
        scheduleGroupUserRepository.save(scheduleGroupUser);
    }
}
