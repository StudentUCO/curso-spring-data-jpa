package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();

//        Recupera el customer de la base de datos cuando se utiliza el get debido al fecth de LAZY de la relacion
//        configurada en la entidad
        orders.forEach(order -> System.out.println(order.getCustomer().getName()));

        return orders;
    }
}
