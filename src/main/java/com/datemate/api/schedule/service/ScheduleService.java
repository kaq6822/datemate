package com.datemate.api.schedule.service;

import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.ScheduleUser;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import com.datemate.api.schedule.model.id.ScheduleUserId;

import java.util.List;


public interface ScheduleService {

    List<ScheduleGroup> selectConfirmScheduleByGroupId(Integer groupId);

    List<ScheduleGroupUser> selectCheckScheduleListByGroupId(Integer groupId);

    void checkGroupSchedule(ScheduleGroupUserId scheduleGroupUserId);

    void uncheckGroupSchedule(ScheduleGroupUserId scheduleGroupUserId);

    void confirmGroupScheduleYN(ScheduleGroupUserId scheduleGroupUserId, Character confirmYN);

    List<ScheduleUser> selectScheduleListByUserSeq(Integer userSeq);

    ScheduleUser selectSchedule(ScheduleUserId scheduleUserId);

    void saveScheduleUser(ScheduleUser scheduleUser);

    void deleteScheduleUser(ScheduleUserId scheduleUserId);
}