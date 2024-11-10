package com.CinemaHub.CinemaHub.Repository;

import com.CinemaHub.CinemaHub.Entity.Seat;
import com.CinemaHub.CinemaHub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

}
