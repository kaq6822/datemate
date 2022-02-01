package com.datemate.api.user.model.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRelationId implements Serializable {
    private Integer userSeq;
    private Integer relationUserSeq;
}
