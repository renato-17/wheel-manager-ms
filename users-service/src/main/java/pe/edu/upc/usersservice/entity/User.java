package pe.edu.upc.usersservice.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
@Data
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 9, nullable = false)
    private String email;

    // por buenas practicas
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(length = 1, nullable = true)
    private String status;

    //Relation With Subscription
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
