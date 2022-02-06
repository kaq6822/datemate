package com.datemate.api.schedule.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class ScheduleGroupUserId implements Serializable {
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date scheduleDate;
    private Integer groupId;
    private Integer userSeq;

    public ScheduleGroupId getScheduleGroupId() {
        return new ScheduleGroupId(this.scheduleDate, this.groupId);
    }

    public void setScheduleGroupId(ScheduleGroupId scheduleGroupId) {
        this.scheduleDate = scheduleGroupId.getScheduleDate();
        this.groupId = scheduleGroupId.getGroupId();
    }

    public ScheduleGroupUserId(ScheduleGroupId scheduleGroupId, Integer userSeq) {
        this.scheduleDate = scheduleGroupId.getScheduleDate();
        this.groupId = scheduleGroupId.getGroupId();
        this.userSeq = userSeq;
    }
}
