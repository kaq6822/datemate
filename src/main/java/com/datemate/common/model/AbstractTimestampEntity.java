package com.datemate.common.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractTimestampEntity {
    @CreatedDate
    @Column(updatable = false)
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
