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
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @Column(name="id")
    private long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name="created_at")
    private String createAt;

}
