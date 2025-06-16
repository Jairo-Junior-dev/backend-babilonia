package com.finance.babilonia.service;

import com.finance.babilonia.config.handle.exceptions.SpentNotFoundException;
import com.finance.babilonia.controller.request.SpentRequest;
import com.finance.babilonia.mapper.SpentMapper;
import com.finance.babilonia.model.Spent;
import com.finance.babilonia.repository.SpentRepository;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SpentService {

    private final SpentRepository spentRepository;
    private final SpentMapper mapper;

    public SpentRequest addSpent(Spent spent , Jwt jwt) {
        spent.setUserId(UUID.fromString((String) jwt.getClaim("sub")));
        return mapper.entityToDTO(spentRepository.save(spent));
    }

    public List<SpentRequest> listAllUserSpent(Jwt jwt) {
        UUID userId = (UUID.fromString((String) jwt.getClaim("sub")));
        return mapper.
                dtoToEntitys(spentRepository.
                        findByUserId(userId).
                        orElseThrow(() -> new RuntimeException("Not Found")));
    }
    @Transactional
    public SpentRequest updateById(SpentRequest request, UUID spentId, Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("sub"));
        Spent existingSpent = spentRepository.findById(spentId)
                .orElseThrow(() -> new SpentNotFoundException(HttpStatus.SC_NOT_FOUND, "Spent not found"));
        mapper.updateEntityToDTO(request, existingSpent);
        Spent updated = spentRepository.save(existingSpent);
        return mapper.entityToDTO(updated);
    }
    @Transactional
    public void deleteById(UUID spentId, Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("sub"));
        Spent spent = spentRepository.findById(spentId)
                .orElseThrow(() -> new SpentNotFoundException(HttpStatus.SC_NOT_FOUND,"Spent not found"));;
        if (!spent.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this spent.");
        }
        spentRepository.deleteById(spentId);
    }
    public SpentRequest findById( UUID uuid , Jwt jwt){
        UUID userId = UUID.fromString((String) jwt.getClaim("sub") );
        Spent spent = spentRepository.findById(uuid)
                .orElseThrow(() -> new SpentNotFoundException(HttpStatus.SC_NOT_FOUND,"Spent not found"));;
        if (!spent.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this spent.");
        }
        return mapper.entityToDTO(spent);
    }
}
