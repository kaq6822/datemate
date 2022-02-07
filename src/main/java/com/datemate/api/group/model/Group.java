package com.datemate.api.group.model;

import com.datemate.api.user.model.UserGroup;
import com.datemate.common.model.AbstractTimestampEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "dm_group_master")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Group extends AbstractTimestampEntity {
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;
    private String groupName;
    private Integer groupOwner;
    private String groupDesc;

    @OneToMany(targetEntity = UserGroup.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId", insertable = false, updatable = false)
    private List<UserGroup> userGroupList;
}
