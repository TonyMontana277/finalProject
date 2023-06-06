package com.homework.finalProject.repository;

import com.homework.finalProject.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>{
    Optional<Visitor> findVisitorByName(String name);

    Optional<Visitor> findVisitorByPassportId(Long passportId);
}
