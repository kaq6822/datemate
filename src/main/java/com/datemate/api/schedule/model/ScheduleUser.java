package com.datemate.api.schedule.model;

import com.datemate.common.constants.Constants;
import com.datemate.common.model.AbstractTimestampEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "dm_schedule_user")
public class ScheduleUser extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleSeq;

    @Column(nullable = false)
    private Integer userSeq;

    private String scheduleName;

    private String scheduleDesc;

    @JsonFormat(pattern = "yyyyMMddHHmm")
    private Date startDate;

    @JsonFormat(pattern = "yyyyMMddHHmm")
    private Date endDate;

    // P: 개인, O: 오픈
    private Character type = 'P';

    // 0: 미완료, 1: 완료, 2: 약속 신청 중
    @Column(nullable = false)
    private Integer status = Constants.ACTIVE;

    // 약속 신청자, 신청 대상
    private Integer targetUserSeq;
}
