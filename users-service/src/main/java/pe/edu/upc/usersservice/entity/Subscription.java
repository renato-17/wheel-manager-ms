package pe.edu.upc.usersservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "suscriptions")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(name = "date_start",length = 60, nullable = false)
    private Date dateStart;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(length = 1, nullable = true)
    private String status;

    //Relation with SubscriptionType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

    //Relation with UsersProfile
    @OneToMany(mappedBy = "subscription")
    private List<User> users;

    public Subscription(){users = new ArrayList<>();}
}
