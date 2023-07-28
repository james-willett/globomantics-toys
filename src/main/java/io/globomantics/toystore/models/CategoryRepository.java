package io.globomantics.toystore.models;

import org.springframework.data.jpa.repository.JpaRepository;

import io.globomantics.toystore.models.data.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);

    Category findBySlug(String slug);
}
