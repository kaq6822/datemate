package com.datemate.api.user.model.id;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupId implements Serializable {
    private Integer userSeq;
    private Integer groupId;
}
