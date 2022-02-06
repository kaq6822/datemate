package com.datemate.api.schedule.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUserId implements Serializable {
    private Integer scheduleSeq;
    private Integer userSeq;
}
