package by.ivanovsky.crudapp.Model;

import lombok.Data;


import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String first_name;
    @Column(name = "last_name")
    String last_name;
    @Column(name = "email")
    String email;
    @Column(name = "website")
    String website;
    @Column(name = "phone")
    String phone;
}
