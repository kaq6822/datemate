package com.datemate.api.schedule.dao;

import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleGroupUserRepository extends JpaRepository<ScheduleGroupUser, ScheduleGroupUserId> {
    List<ScheduleGroupUser> getScheduleGroupUserByGroupIdAndCheckYn(Integer groupId, Character checkYn);
}
