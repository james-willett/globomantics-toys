package io.globomantics.toystore.models;

import org.springframework.data.jpa.repository.JpaRepository;

import io.globomantics.toystore.models.data.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
