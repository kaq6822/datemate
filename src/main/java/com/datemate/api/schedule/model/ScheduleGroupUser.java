package com.datemate.api.schedule.model;

import com.datemate.api.schedule.model.id.ScheduleGroupId;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@IdClass(ScheduleGroupUserId.class)
@Table(name = "dm_schedule_group_vote")
public class ScheduleGroupUser {
    @Id
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date scheduleDate;

    @Id
    private Integer groupId;

    @Id
    private Integer userSeq;

    // 캘린더에 가능 여부 표기 YN
    private Character checkYn;

    // 좋아요 싫어요 표기 YN (NULL: 선택 X)
    private Character confirmYn;

    public ScheduleGroupUser(ScheduleGroupUserId scheduleGroupUserId, Character checkYn, Character confirmYn) {
        this.scheduleDate = scheduleGroupUserId.getScheduleDate();
        this.groupId = scheduleGroupUserId.getGroupId();
        this.userSeq = scheduleGroupUserId.getUserSeq();
        this.checkYn = checkYn;
        this.confirmYn = confirmYn;
    }

}
