package com.finance.babilonia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Spent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private UUID userId;
    private BigDecimal value;
    private String title;
    @Enumerated(EnumType.STRING)
    private SpentType type;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;


}
