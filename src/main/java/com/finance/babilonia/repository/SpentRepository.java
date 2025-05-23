package com.finance.babilonia.repository;

import com.finance.babilonia.model.Spent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpentRepository extends CrudRepository<Spent, UUID> {

    Optional<List<Spent>> findByUserId(UUID userId);
}
