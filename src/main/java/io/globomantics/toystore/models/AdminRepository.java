package io.globomantics.toystore.models;

import org.springframework.data.jpa.repository.JpaRepository;

import io.globomantics.toystore.models.data.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUsername(String username);
}
