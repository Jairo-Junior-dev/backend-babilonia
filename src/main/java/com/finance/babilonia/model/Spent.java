package com.finance.babilonia.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

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
    @CurrentTimestamp
    private LocalDate date;

}
