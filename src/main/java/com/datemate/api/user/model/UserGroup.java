package com.datemate.api.user.model;

import com.datemate.api.group.model.Group;
import com.datemate.api.user.model.id.UserGroupId;
import lombok.Data;

import javax.persistence.*;
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

    @Column(nullable = false)
    private Integer status = 0;

    @OneToOne(targetEntity = Group.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "userSeq", insertable = false, updatable = false)
    private Group group;
}