package com.datemate.api.user.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupId implements Serializable {
    private Integer userSeq;
    private Integer groupId;
}
