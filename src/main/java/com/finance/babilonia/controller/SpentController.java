package com.finance.babilonia.controller;


import com.finance.babilonia.controller.request.SpentRequest;
import com.finance.babilonia.model.Spent;
import com.finance.babilonia.service.SpentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public ResponseEntity<SpentRequest> postSpent(@RequestBody Spent spent, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok().body(spentService.addSpent(spent, jwt));
    }
    @GetMapping("{id}")
    public  ResponseEntity<SpentRequest> findSpentById( @PathVariable("id") UUID uuid ,@AuthenticationPrincipal Jwt jwt) {
        return  ResponseEntity.ok().body(spentService.findById(uuid,jwt));
    }
    @GetMapping("all")
    public ResponseEntity<List<SpentRequest>> getAllUserSpent(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(spentService.listAllUserSpent(jwt));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteSpentById(@AuthenticationPrincipal Jwt jwt ,  @PathVariable("id")UUID spentId){
        spentService.deleteById(spentId, jwt);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SpentRequest> updateSpentById(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("id") UUID spentId,
            @RequestBody SpentRequest request) {
        SpentRequest updated = spentService.updateById(request, spentId, jwt);
        return ResponseEntity.ok(updated);
    }

}
