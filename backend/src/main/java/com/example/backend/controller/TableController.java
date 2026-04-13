package com.example.backend.controller;


import com.example.backend.dto.TableResponseDTO;
import com.example.backend.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TableController {
    private final TableService tableService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllTables() {
        try {
            List<TableResponseDTO> list = tableService.getAllTables();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateTableStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String newStatus = request.get("status");
            TableResponseDTO updatedTable = tableService.updateTableStatus(id, newStatus);
            return ResponseEntity.ok(updatedTable);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("{id}/qrCode")
    public ResponseEntity<?> getTableQRCode(@PathVariable Long id) {
        try {
            String qrData = tableService.getTableQRCode(id);
            return ResponseEntity.ok(Map.of("qrCode", qrData));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
