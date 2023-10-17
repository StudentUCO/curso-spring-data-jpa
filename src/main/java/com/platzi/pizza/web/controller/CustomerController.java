package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.service.CustomerService;
import com.platzi.pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(customerService.findByPhone(phone));
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }
}
