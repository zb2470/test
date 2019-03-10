package com.test.test.Model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name="created_at")
    private String createAt;
}
