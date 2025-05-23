package com.finance.babilonia.service;

import com.finance.babilonia.model.Spent;
import com.finance.babilonia.repository.SpentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SpentService {

    private final SpentRepository spentRepository;


    public Spent addSpent(Spent spent) {
        return spentRepository.save(spent);
    }

    public List<Spent> listAllUserSpent(UUID userId) {
        return spentRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
