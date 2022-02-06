package com.datemate.api.schedule.dao;

import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.id.ScheduleGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleGroupRepository extends JpaRepository<ScheduleGroup, ScheduleGroupId> {
    List<ScheduleGroup> getScheduleGroupByGroupId(Integer groupId);
}
