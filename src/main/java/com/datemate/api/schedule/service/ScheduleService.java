package com.datemate.api.schedule.service;

import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;

import java.util.List;


public interface ScheduleService {

    List<ScheduleGroup> selectConfirmScheduleByGroupId(Integer groupId);

    List<ScheduleGroupUser> selectCheckScheduleListByGroupId(Integer groupId);

    void checkGroupSchedule(ScheduleGroupUserId scheduleGroupUserId);

    void uncheckGroupSchedule(ScheduleGroupUserId scheduleGroupUserId);

    void confirmGroupScheduleYN(ScheduleGroupUserId scheduleGroupUserId, Character confirmYN);
}