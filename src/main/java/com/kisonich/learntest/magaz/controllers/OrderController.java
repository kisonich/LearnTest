package com.kisonich.learntest.magaz.controllers;


import com.kisonich.learntest.magaz.model.Book;
import com.kisonich.learntest.magaz.model.Order;
import com.kisonich.learntest.magaz.model.User;
import com.kisonich.learntest.magaz.services.BookService;
import com.kisonich.learntest.magaz.services.OrderService;
import com.kisonich.learntest.magaz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired

    private UserService userService;
    @Autowired

    private BookService bookService;


    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

//    @PostMapping
//    public Order createOrder(@RequestBody Order order) {
//        return orderService.createOrder(order);
//    }



    @PostMapping("/{userId}/{bookId}/{bookQuantity}")
    public ResponseEntity<String> makeOrder(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable int bookQuantity) {
        try {
            orderService.makeOrder(userId, bookId, bookQuantity);
            return ResponseEntity.ok("Order created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping("/{userId}/{bookId}/{bookQuantity}")
//    public ResponseEntity<String> makeOrder(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable int bookQuantity) {
//        try {
//            User user = userService.getUser(userId);
//            Book book = bookService.getBook(bookId);
//            orderService.createOrder(user, book, bookQuantity);
//            return ResponseEntity.ok("Order created successfully");
//        } catch (ChangeSetPersister.NotFoundException e) {
//            return ResponseEntity.badRequest().body("User or book not found");
//
//            return ResponseEntity.badRequest().body("Not enough books in stock");
//        }
//    }






    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        User user = new User(); // создаем пустого пользователя
        user.setUserId(userId);
        return orderService.getUserOrders(user);
    }

    @GetMapping("/book/{bookId}")
    public List<Order> getBookOrders(@PathVariable Long bookId) {
        Book book = new Book(); // создаем пустую книгу
        book.setBookId(bookId);
        return orderService.getBookOrders(book);
    }

//    @GetMapping("/total-price")
//    public BigDecimal getOrderTotalPrice(@RequestParam("ids") List<Long> orderIds) {
//        List<Order> orders = new ArrayList<>();
//        for (Long id : orderIds) {
//            orders.add(orderService.getOrder(id));
//        }
//        return orderService.getOrderTotalPrice(orders);
//    }
}

//@RestController
//@RequestMapping("/api/v1/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @GetMapping
//    public List<Order> getAllOrders() {
//        return orderService.getAllOrders();
//    }
//
//    @GetMapping("/{id}")
//    public Order getOrderById(@PathVariable long id) throws OrderNotFoundException {
//        return orderService.getOrderById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Order createOrder(@RequestBody Order order) throws BookNotFoundException {
//        return orderService.createOrder(order);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteOrder(@PathVariable long id) throws OrderNotFoundException {
//        orderService.deleteOrder(id);
//    }
//}