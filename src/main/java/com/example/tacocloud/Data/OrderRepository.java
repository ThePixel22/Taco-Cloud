package com.example.tacocloud.Data;

import com.example.tacocloud.Models.Order;

public interface OrderRepository {
    Order save(Order order);
}
