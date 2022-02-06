package com.datemate.api.user.model;

import com.datemate.common.constants.Constants;
import com.datemate.common.model.AbstractTimestampEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "dm_user_master")
public class User extends AbstractTimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;

    @Column(unique = true)
    private String email;

    private String userId;

    private String userName;

    @ApiModelProperty(hidden = true)
    private Integer status = Constants.ACTIVE;
}
