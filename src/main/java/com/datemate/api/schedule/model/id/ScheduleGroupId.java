package com.datemate.api.schedule.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class ScheduleGroupId implements Serializable {
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date scheduleDate;
    private Integer groupId;
}
