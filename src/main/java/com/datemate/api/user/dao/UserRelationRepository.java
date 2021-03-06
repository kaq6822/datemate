package com.datemate.api.user.dao;

import com.datemate.api.user.model.UserRelation;
import com.datemate.api.user.model.id.UserRelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, UserRelationId> {
    List<UserRelation> findByUserSeq(Integer userSeq);
    List<UserRelation> findByUserSeqAndStatus(Integer userSeq, Character status);
}
