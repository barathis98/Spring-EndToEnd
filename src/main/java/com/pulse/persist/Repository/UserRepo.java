package com.pulse.persist.Repository;

import com.pulse.persist.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String > {

    public boolean existsByEmail(String email);
    public User findByEmail(String email);
}
