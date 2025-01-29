package com.csk.worklangcorrector.repository;

import com.csk.worklangcorrector.model.CorrectionDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorrectionDictionaryRepository extends JpaRepository<CorrectionDictionary, Long> {
    Optional<CorrectionDictionary> findByCorrectedText(String correctedText);
}
