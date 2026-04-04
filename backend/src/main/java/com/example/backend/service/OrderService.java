package com.example.backend.service;


import com.example.backend.dto.OrderItemRequestDTO;
import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTO;
import com.example.backend.entities.*;
import com.example.backend.entities.Enum;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.repository.DishRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final TableRepository tableRepository;
    private final OrderMapper orderMapper;

    //get all orders
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //create an order
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        // 1. Kiểm tra bàn có tồn tại không
        RestaurantTable table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new RuntimeException("Bàn không tồn tại"));

        // 2. Khởi tạo đối tượng Order
        Order order = new Order();
        order.setTable(table);
        order.setStatus(Enum.OrderStatus.PENDING);
        order.setPaymentStatus(Enum.PaymentStatus.UNPAID);
        order.setPaymentMethod(Enum.PaymentMethod.valueOf(request.getPaymentMethod()));

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        // 3. Xử lý từng món ăn trong danh sách gửi lên
        for (OrderItemRequestDTO itemReq : request.getItems()) {
            // Tìm món ăn trong DB
            Dish dish = dishRepository.findById(itemReq.getDishId())
                    .orElseThrow(() -> new RuntimeException("Món ăn ID " + itemReq.getDishId() + " không tồn tại"));

            // Kiểm tra số lượng tồn kho
            if (dish.getAvailableStock() < itemReq.getQuantity()) {
                throw new RuntimeException("Món '" + dish.getName() + "' chỉ còn " + dish.getAvailableStock() + " suất. Vui lòng chọn lại!");
            }

            // TRỪ KHO: Cập nhật số lượng còn lại
            dish.setAvailableStock(dish.getAvailableStock() - itemReq.getQuantity());
            dishRepository.save(dish);

            // Tạo bản ghi OrderItem (Chi tiết đơn hàng)
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setDish(dish);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setNote(itemReq.getNote());

            // QUAN TRỌNG: Lấy giá từ DB tại thời điểm đặt, không tin tưởng giá từ FE gửi lên (tránh hack giá)
            orderItem.setPriceAtTime(dish.getPrice());

            orderItems.add(orderItem);

            // Tính tổng tiền: price * quantity
            BigDecimal itemTotal = dish.getPrice().multiply(new BigDecimal(itemReq.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
        }

        // 4. Hoàn thiện đối tượng Order
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        // 5. Cập nhật trạng thái bàn sang "Đang có khách" (OCCUPIED)
        table.setStatus(Enum.TableStatus.OCCUPIED);
        tableRepository.save(table);

        // 6. Lưu đơn hàng vào DB và trả về DTO
        return orderMapper.toResponseDTO(orderRepository.save(order));
    }
}