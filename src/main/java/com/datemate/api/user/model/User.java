package com.datemate.api.user.model;

import com.datemate.common.model.AbstractTimestampEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "dm_user_master")
public class User extends AbstractTimestampEntity implements Serializable {
    @ApiModelProperty(hidden = true)
    @Column(insertable = false, updatable = false)
    private Integer userSeq;

    @Id
    private String email;

    private String userName;

    @ApiModelProperty(hidden = true)
    private String status = "0";
}
