package com.CinemaHub.CinemaHub.Repository;

import com.CinemaHub.CinemaHub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByusername(String username);


    void deleteByusername(String name);
}
