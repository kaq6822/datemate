package com.datemate.api.user.model;

import com.datemate.api.user.model.pk.UserGroupId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "dm_user_group")
@IdClass(UserGroupId.class)
public class UserGroup implements Serializable {
    @Id
    private Integer userSeq;

    @Id
    private Integer groupId;

    private String groupName;
}