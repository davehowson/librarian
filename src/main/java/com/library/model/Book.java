package com.library.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private long isbn;
    private String title;
    private String author;
    private String date;

    public Book() {
    }

    public Book(long isbn, String title, String author, String date) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.date = dateConv(date);
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = dateConv(date);
    }

    private String dateConv(String date){
        String formattedDate = "";
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(d);
        } catch (Exception e){
            e.printStackTrace();
        }
        return formattedDate;
    }
}
