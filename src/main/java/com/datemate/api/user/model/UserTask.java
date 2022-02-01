package com.datemate.api.user.model;

import com.datemate.api.user.model.pk.UserTaskId;
import com.datemate.common.model.AbstractTimestampEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@IdClass(UserTaskId.class)
@Table(name = "dm_user_task")
public class UserTask extends AbstractTimestampEntity {
    @Id
    private Integer userSeq;
    @Id
    private Integer taskId;

}
