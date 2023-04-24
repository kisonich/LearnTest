package com.kisonich.learntest.magaz.repository;

import com.kisonich.learntest.magaz.model.Book;
import com.kisonich.learntest.magaz.model.Order;
import com.kisonich.learntest.magaz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByBook(Book book);
}