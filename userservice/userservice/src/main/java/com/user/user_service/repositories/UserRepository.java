package com.user.user_service.repositories;

import com.user.user_service.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    // if you want to implement any custom methods

}
