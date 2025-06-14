package com.finance.babilonia.controller.request;

import com.finance.babilonia.model.SpentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SpentRequest
{
    private UUID uuid;
    private String title;
    private SpentType type;
    private BigDecimal value;
}
