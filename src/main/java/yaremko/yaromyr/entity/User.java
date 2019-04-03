package yaremko.yaromyr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alcoholic")
@Getter @Setter
public class User extends  IdHolder{

    private String name;

    private String surName;

    private Role role;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String password;

    private Boolean registered;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();
}
