package com.datemate.api.task.model;

import com.datemate.common.model.AbstractTimestampEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "dm_task_master")
public class Task extends AbstractTimestampEntity implements Serializable {
    @Id
    private Integer taskId;
    private String taskName;
    private String taskDesc;
    private Date startDate;
    private Date endDate;

}
