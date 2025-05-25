package com.unemploymenthouse.unemploymenthouse.login;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
