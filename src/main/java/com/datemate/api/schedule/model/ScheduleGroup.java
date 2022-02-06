package com.datemate.api.schedule.model;

import com.datemate.api.schedule.model.id.ScheduleGroupId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@IdClass(ScheduleGroupId.class)
@Table(name = "dm_schedule_group_confirm")
public class ScheduleGroup {
    @Id
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date scheduleDate;

    @Id
    private Integer groupId;
}
