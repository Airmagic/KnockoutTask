package com.clara.taskdb.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Task(@Size(min = 1) String text, boolean urgent, boolean completed) {
        this.text = text;
        this.urgent = urgent;
        this.completed = completed;
    }

    @Column(nullable = false)
    @Size(min = 1)

    private String text;

    private boolean urgent;
    private boolean completed = false;

//    Need this empty construtor
    public Task(){}

}
