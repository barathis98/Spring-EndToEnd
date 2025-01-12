package com.pulse.persist.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Id
    private String userId;
    private String name;
//    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;
    private String password;


}
