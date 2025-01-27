package com.csk.worklangcorrector.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CorrectionDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String alias;
    private final String correctedText;
    private final String category;
    private final Integer priority;
    private final String description;
    private final Boolean active;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "correctionDictionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncorrectText> incorrectTextList = new ArrayList<>();
}
