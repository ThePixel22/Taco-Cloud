package com.example.tacocloud.Repositories;

import com.example.tacocloud.Models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
