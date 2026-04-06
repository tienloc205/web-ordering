package com.example.backend.service;


import com.example.backend.dto.TableResponseDTO;
import com.example.backend.entities.Constants;
import com.example.backend.entities.RestaurantTable;
import com.example.backend.mapper.TableMapper;
import com.example.backend.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

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
}
