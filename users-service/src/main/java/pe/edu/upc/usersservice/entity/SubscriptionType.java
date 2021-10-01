package pe.edu.upc.usersservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "suscription_types")
@Data
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @Column(length = 250, nullable = false)
    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(length = 1, nullable = true)
    private String status;


    //Relation with Subscription
    @OneToMany(mappedBy = "subscriptionType")
    private List<Subscription> subscriptions;

    public SubscriptionType(){subscriptions = new ArrayList<>();}
}
