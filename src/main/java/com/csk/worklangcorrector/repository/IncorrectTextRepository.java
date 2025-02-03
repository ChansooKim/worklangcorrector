package com.csk.worklangcorrector.repository;

import com.csk.worklangcorrector.model.IncorrectText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncorrectTextRepository extends JpaRepository<IncorrectText, Long> {
    Optional<IncorrectText> findByWrongText(String word);
}
