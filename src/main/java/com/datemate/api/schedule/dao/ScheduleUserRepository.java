package com.datemate.api.schedule.dao;

import com.datemate.api.schedule.model.ScheduleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleUserRepository extends JpaRepository<ScheduleGroup, Integer> {
}
