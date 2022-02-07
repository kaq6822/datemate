package com.datemate.api.user.model;

import com.datemate.api.group.model.Group;
import com.datemate.api.user.model.id.UserGroupId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "dm_user_group")
@IdClass(UserGroupId.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class UserGroup implements Serializable {
    @Id
    private Integer userSeq;

    @Id
    private Integer groupId;

    @Column(nullable = false)
    private Integer status = 0;

    @ManyToOne(targetEntity = Group.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId", insertable = false, updatable = false)
    private Group group;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userSeq", insertable = false, updatable = false)
    private User user;

    public UserGroup(Integer userSeq, Integer groupId) {
        this.userSeq = userSeq;
        this.groupId = groupId;
    }
}