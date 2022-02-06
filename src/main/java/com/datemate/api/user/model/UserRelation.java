package com.datemate.api.user.model;

import com.datemate.api.user.model.id.UserRelationId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data
@IdClass(UserRelationId.class)
@Table(name = "dm_user_relation")
public class UserRelation implements Serializable {
    @Id
    @NonNull
    private Integer userSeq;

    @Id
    @NonNull
    private Integer relationUserSeq;

    @Column(nullable = false)
    @NonNull
    private Character status;

    @OneToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "relationUserSeq", insertable = false, updatable = false)
    private User user;
}
