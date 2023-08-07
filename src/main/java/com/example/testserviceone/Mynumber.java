package com.example.testserviceone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mynumbers")
public class Mynumber {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "n1")
    private Integer n1;

    @Column(name = "n2")
    private Integer n2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
    }

}