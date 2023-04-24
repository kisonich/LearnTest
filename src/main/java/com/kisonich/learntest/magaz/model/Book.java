package com.kisonich.learntest.magaz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "books2")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    public Book(String name, String author, int quantity, double price) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;}}