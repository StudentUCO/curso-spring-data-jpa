package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.persistence.repository.OrderRepository;
import com.platzi.pizza.service.dto.RandomOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    public static final String DELIVERY = "D";
    public static final String CARRYOUT = "C";
    public static final String ON_SITE = "S";


    private final OrderRepository orderRepository;

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();

//        Recupera el customer de la base de datos cuando se utiliza el get debido al fecth de LAZY de la relacion
//        configurada en la entidad
//        orders.forEach(order -> System.out.println(order.getCustomer().getName()));

        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT, ON_SITE);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummary(int orderId) {
        return orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDTO randomOrderDTO) {
        return orderRepository.saveRandomOrder(randomOrderDTO.getIdCustomer(), randomOrderDTO.getMethod());
    }
}
