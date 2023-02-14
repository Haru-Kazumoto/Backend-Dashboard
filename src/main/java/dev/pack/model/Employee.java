package dev.pack.model;

import dev.pack.model.enumeration.Gender;
import dev.pack.model.enumeration.JobRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_tbl")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    @Column(unique = true, nullable = false)
    private String NumberEmployee;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private JobRole role;

    @Temporal(TemporalType.DATE)
    private Date dateJoin = new Date(System.currentTimeMillis());
}
