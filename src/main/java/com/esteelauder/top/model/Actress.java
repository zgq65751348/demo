package com.esteelauder.top.model;

public class Actress {
    private Integer id;

    private String name;

    private Integer rank;

    public Actress(Integer id, String name, Integer rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    public Actress() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}