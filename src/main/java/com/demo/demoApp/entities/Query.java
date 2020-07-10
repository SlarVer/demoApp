package com.demo.demoApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "queries")
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String request;

    private String response;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Query(String request, String response, User author) {
        this.request = request;
        this.response = response;
        this.author = author;
    }
}
