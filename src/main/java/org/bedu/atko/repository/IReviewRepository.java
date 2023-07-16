package org.bedu.atko.repository;


import org.bedu.atko.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByProfessional_id (long id);
}
