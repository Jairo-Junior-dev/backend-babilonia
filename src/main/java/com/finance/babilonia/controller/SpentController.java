package com.finance.babilonia.controller;

import com.finance.babilonia.model.Spent;
import com.finance.babilonia.service.SpentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("spent")
@AllArgsConstructor
public class SpentController {

    private final SpentService spentService;

    @GetMapping("hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("oi");
    }

    @PostMapping
    public ResponseEntity<Spent> postSpent(@RequestBody Spent spent) {
        return ResponseEntity.ok(spentService.addSpent(spent));
    }

    @GetMapping("all/{userId}")
    public ResponseEntity<List<Spent>> getAllUserSpent(@PathVariable UUID userId) {
        return ResponseEntity.ok(spentService.listAllUserSpent(userId));
    }

}
