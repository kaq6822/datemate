package com.datemate.api.schedule.dao;

import com.datemate.api.schedule.model.ScheduleUser;
import com.datemate.api.schedule.model.id.ScheduleUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Integer> {
    List<ScheduleUser> findAllByUserSeq(Integer userSeq);

    List<ScheduleUser> findAllByTargetUserSeq(Integer targetUserSeq);
}
