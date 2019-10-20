package com.thoughtworks.parking_lot.Repository;

import com.thoughtworks.parking_lot.core.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
