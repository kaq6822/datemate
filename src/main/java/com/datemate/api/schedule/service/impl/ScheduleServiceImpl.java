package com.datemate.api.schedule.service.impl;

import com.datemate.api.schedule.dao.ScheduleGroupRepository;
import com.datemate.api.schedule.dao.ScheduleGroupUserRepository;
import com.datemate.api.schedule.dao.ScheduleUserRepository;
import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.ScheduleUser;
import com.datemate.api.schedule.model.id.ScheduleGroupId;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import com.datemate.api.schedule.model.id.ScheduleUserId;
import com.datemate.api.schedule.service.ScheduleService;
import com.datemate.common.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleGroupRepository scheduleGroupRepository;

    @Resource
    private ScheduleGroupUserRepository scheduleGroupUserRepository;

    @Resource
    private ScheduleUserRepository scheduleUserRepository;

    @Override
    public List<ScheduleGroup> selectConfirmScheduleByGroupId(Integer groupId) {
        return scheduleGroupRepository.getScheduleGroupByGroupId(groupId);
    }

    @Override
    public ScheduleGroup selectConfirmSchedule(ScheduleGroupId scheduleGroupId) {
        return scheduleGroupRepository.getById(scheduleGroupId);
    }

    @Override
    public void deleteConfirmSchedule(ScheduleGroupId scheduleGroupId) {
        scheduleGroupRepository.deleteById(scheduleGroupId);
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
        ScheduleGroupUser scheduleGroupUser = scheduleGroupUserRepository.findById(scheduleGroupUserId).orElse(new ScheduleGroupUser(scheduleGroupUserId, null, null));
        scheduleGroupUser.setConfirmYn(confirmYN);
        scheduleGroupUserRepository.save(scheduleGroupUser);
    }

    @Override
    public Long countConfirmGroupSchedule(ScheduleGroupId scheduleGroupId) {
        return scheduleGroupUserRepository.countScheduleGroupUserByGroupIdAndScheduleDateAndConfirmYn(scheduleGroupId.getGroupId(), scheduleGroupId.getScheduleDate(), 'Y');
    }

    @Override
    public List<ScheduleUser> selectScheduleListByUserSeq(Integer userSeq) {
        return scheduleUserRepository.findAllByUserSeq(userSeq);
    }

    @Override
    public ScheduleUser selectSchedule(Integer scheduleSeq) {
        return scheduleUserRepository.findById(scheduleSeq).orElseThrow(() -> new IllegalArgumentException("No such Data"));
    }

    @Override
    public void saveScheduleUser(ScheduleUser scheduleUser) {
        scheduleUserRepository.save(scheduleUser);
    }

    @Override
    public void deleteScheduleUser(ScheduleUserId scheduleUserId) {
        scheduleUserRepository.deleteById(scheduleUserId.getScheduleSeq());
    }

    @Override
    public void saveConfirmSchedule(ScheduleGroup scheduleGroup) {
        scheduleGroupRepository.save(scheduleGroup);
    }
}
