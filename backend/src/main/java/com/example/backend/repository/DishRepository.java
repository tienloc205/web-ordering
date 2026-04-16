package com.example.backend.repository;

import com.example.backend.dto.DishResponseDTO;
import com.example.backend.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query("SELECT new com.example.backend.dto.DishResponseDTO(" +
            "d.id, " +
            "d.name, " +
            "d.description, " +
            "d.price, " +
            "d.imageUrl, " +
            "d.category.name, " +
            "d.availableStock, " + // Lấy thẳng giá trị từ DB
            "d.isActive) " +       // Lấy trạng thái kích hoạt của món
            "FROM Dish d " +
            "WHERE d.isActive = true") // Chỉ lấy những món đang kinh doanh
    List<DishResponseDTO> getAllDishes();


    @Query("SELECT d FROM Dish d " +
            "JOIN FETCH d.category " + // Lấy luôn category để tránh N+1
            "WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND d.isActive = true")
    List<Dish> searchByName(@Param("keyword") String keyword);
}
