package org.bedu.atko.repository;

import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
