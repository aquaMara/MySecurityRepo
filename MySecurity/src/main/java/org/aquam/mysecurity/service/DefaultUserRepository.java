package org.aquam.mysecurity.service;

import org.aquam.mysecurity.users.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DefaultUserRepository extends JpaRepository<DefaultUser, Long> {

    Optional<DefaultUser> findByUsername(String username);

}
