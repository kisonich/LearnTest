package com.kisonich.learntest.magaz.services;

import com.kisonich.learntest.magaz.model.Book;
import com.kisonich.learntest.magaz.model.Order;
import com.kisonich.learntest.magaz.model.User;
import com.kisonich.learntest.magaz.repository.UserRepository;
import com.kisonich.learntest.magaz.repository.BookRepository;
import com.kisonich.learntest.magaz.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;

//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void makeOrder(Long userId, Long bookId, int bookQuantity) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));

        if (book.getQuantity() < bookQuantity) {
            throw new Exception("Not enough books in stock");
        }

        double totalPrice = book.getPrice() * bookQuantity;

        Order order = new Order(user, book, totalPrice);

        orderRepository.save(order);

        book.setQuantity(book.getQuantity() - bookQuantity);

        bookRepository.save(book);
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = getOrder(orderId);
        order.setBook(orderDetails.getBook());
        order.setUser(orderDetails.getUser());
        order.setTotalPrice(orderDetails.getTotalPrice());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getBookOrders(Book book) {
        return orderRepository.findByBook(book);
    }

}

//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BookService bookService;
//
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Order getOrderById(long id) throws OrderNotFoundException {
//        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
//    }
//
//    public Order createOrder(Order order) throws BookNotFoundException {
//        User user = userService.getUserById(order.getUser().getUserId());
//        Book book = bookService.getBookById(order.getBook().getBookId());
//        book.setQuantity(book.getQuantity() - 1); // уменьшаем количество книг на единицу
//        bookService.updateBook(book.getBookId(), book);
//        order.setOrderDate(new Date());
//        return orderRepository.save(order);
//    }
//
//    public void deleteOrder(long id) throws OrderNotFoundException {
//        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
//        orderRepository.delete(order);
//    }
//}
