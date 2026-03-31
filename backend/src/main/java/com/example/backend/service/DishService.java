package com.example.backend.service;

import com.example.backend.dto.DishResponseDTO;
import com.example.backend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public List<DishResponseDTO> getAllDishes() {
        return dishRepository.getAllDishes();
    }

    public Map<String, List<DishResponseDTO>> getMenu() {
        List<DishResponseDTO> allDishes = getAllDishes();

        return allDishes.stream()
                .collect(Collectors.groupingBy(
                        DishResponseDTO::getCategoryName,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }
}
