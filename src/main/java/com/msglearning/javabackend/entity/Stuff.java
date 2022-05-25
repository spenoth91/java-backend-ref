package com.msglearning.javabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = Stuff.TABLE_NAME)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stuff {

    static final String TABLE_NAME = "stuff";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}
