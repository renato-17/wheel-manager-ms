package pe.edu.upc.usersservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("user-profile")
@Data
public class UserProfile extends User{
    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthdate;

    @Column(length = 30, nullable = false)
    private String gender;
}
