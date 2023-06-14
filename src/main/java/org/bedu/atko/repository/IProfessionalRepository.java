package org.bedu.atko.repository;

import org.bedu.atko.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessionalRepository extends JpaRepository<Professional, Long> {

}
