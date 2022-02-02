package com.datemate.api.user.dao;

import com.datemate.api.user.model.UserGroup;
import com.datemate.api.user.model.pk.UserGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {
}
