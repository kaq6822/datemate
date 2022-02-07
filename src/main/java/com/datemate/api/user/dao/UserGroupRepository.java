package com.datemate.api.user.dao;

import com.datemate.api.user.model.UserGroup;
import com.datemate.api.user.model.id.UserGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {
    List<UserGroup> findByUserSeq(Integer userSeq);

    void deleteAllByGroupId(Integer groupId);

    Long countUserGroupByGroupId(Integer groupId);
}
