package com.datemate.api.schedule.model;

import com.datemate.api.schedule.model.id.ScheduleUserId;
import com.datemate.common.model.AbstractTimestampEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@IdClass(ScheduleUserId.class)
@Table(name = "dm_schedule_user")
public class ScheduleUser extends AbstractTimestampEntity {
    @Id
    private int scheduleSeq;

    @Id
    private Integer userSeq;

    private String scheduleName;

    private String scheduleDesc;

    private Date startDate;

    private Date endDate;

    private Character type;

    private Integer status;

    private Integer createUserSeq;
    // target???
}
