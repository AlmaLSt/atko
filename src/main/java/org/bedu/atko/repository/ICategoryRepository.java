package org.bedu.atko.repository;

import org.bedu.atko.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
