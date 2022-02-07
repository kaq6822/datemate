package com.datemate.api.schedule.service;

import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.ScheduleUser;
import com.datemate.api.schedule.model.id.ScheduleGroupId;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import com.datemate.api.schedule.model.id.ScheduleUserId;
import io.swagger.models.auth.In;

import java.util.List;


public interface ScheduleService {

    List<ScheduleGroup> selectConfirmScheduleByGroupId(Integer groupId);

    ScheduleGroup selectConfirmSchedule(ScheduleGroupId scheduleGroupId);

    void deleteConfirmSchedule(ScheduleGroupId scheduleGroupId);

    List<ScheduleGroupUser> selectCheckScheduleListByGroupId(Integer groupId);

    void checkGroupSchedule(ScheduleGroupUserId scheduleGroupUserId);

    void uncheckGroupSchedule(ScheduleGroupUserId scheduleGroupUserId);

    void confirmGroupScheduleYN(ScheduleGroupUserId scheduleGroupUserId, Character confirmYN);

    Long countConfirmGroupSchedule(ScheduleGroupId scheduleGroupId);

    List<ScheduleUser> selectScheduleListByUserSeq(Integer userSeq);

    ScheduleUser selectSchedule(Integer scheduleSeq);

    void saveScheduleUser(ScheduleUser scheduleUser);

    void deleteScheduleUser(ScheduleUserId scheduleUserId);

    void saveConfirmSchedule(ScheduleGroup scheduleGroup);
}