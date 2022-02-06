package com.datemate.api.schedule.model.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleUserId implements Serializable {
    private Integer scheduleSeq;
    private Integer userSeq;
}
