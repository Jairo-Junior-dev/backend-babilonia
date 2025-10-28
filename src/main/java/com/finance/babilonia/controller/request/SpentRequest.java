package com.finance.babilonia.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finance.babilonia.model.SpentType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class SpentRequest
{
    private UUID uuid;
    private String title;
    private SpentType type;
    private BigDecimal value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
}
