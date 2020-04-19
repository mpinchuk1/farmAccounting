package org.example.dao;

import org.example.entity.Order;

import java.util.List;

public interface OrdersDao {
    boolean addOrder(Order order);
    void deleteOrder(Long id);
    List<Order> selectAllOrders();

}
