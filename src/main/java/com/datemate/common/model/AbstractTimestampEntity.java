package com.datemate.common.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractTimestampEntity {
    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date modifyDate;

    @PrePersist
    protected void onCreate() {
        modifyDate = createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modifyDate = new Date();
    }
}
