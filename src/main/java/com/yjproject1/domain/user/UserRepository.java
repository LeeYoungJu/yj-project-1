package com.yjproject1.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    @Query("select u from User u where u.id in :ids")
    List<User> findByIds(@Param("ids") List<Long> ids);
}
