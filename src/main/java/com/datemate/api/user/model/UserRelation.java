package com.datemate.api.user.model;

import com.datemate.api.user.model.pk.UserRelationId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(UserRelationId.class)
@Table(name = "dm_user_relation")
public class UserRelation implements Serializable {
    @Id
    private Integer userSeq;

    @Id
    private Integer relationUserSeq;

    @Column(nullable = false)
    private Character status;
}
