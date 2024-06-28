package com.vm.auth.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Size(max = 20)
    private ERole name;
}

