package com.example.backend.service;


import com.example.backend.dto.TableResponseDTO;
import com.example.backend.entities.Constants;
import com.example.backend.entities.RestaurantTable;
import com.example.backend.mapper.TableMapper;
import com.example.backend.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;
    private final QRCodeService qrCodeService;
    @Value("${app.frontend.url}")
    private String frontendUrl;

    public List<TableResponseDTO> getAllTables() {
        List<RestaurantTable> tableList = tableRepository.findAll();
        return tableList.stream()
                .map(tableMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TableResponseDTO updateTableStatus(Long id, String status) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn ID: " + id));

        // Giả sử Status là Enum TableStatus
        table.setStatus(Constants.TableStatus.valueOf(status));

        return tableMapper.toResponseDTO(tableRepository.save(table));
    }

    @Transactional
    public String getTableQRCode(Long tableId) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn ID: " + tableId));

        if (table.getQrCodeUrl() != null && !table.getQrCodeUrl().isEmpty()) {
            return table.getQrCodeUrl();
        }

        String targetUrl = frontendUrl;

        String base64QR = "data:image/png;base64," + qrCodeService.generateQRCodeBase64(targetUrl, 300, 300);

        table.setQrCodeUrl(base64QR);
        tableRepository.save(table);

        return base64QR;
    }
}
