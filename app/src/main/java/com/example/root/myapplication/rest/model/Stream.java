package com.example.root.myapplication.rest.model;

import java.util.Date;

/**
 * Created by root on 14.03.15.
 */
public class Stream {
    private String game;
    private Date created_at;
    private int id;

    public String getGame(){return game;}
    public Date getCreated_at(){return created_at;}
    public int getId(){return id;}
}
