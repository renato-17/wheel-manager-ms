package pe.edu.upc.usersservice.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@DiscriminatorValue("corporation")
@Data
public class Corporation extends User {
    @Column(length = 15, nullable = false, unique = true)
    private String ruc;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String phone;
}
