package dev.pack.model;

import dev.pack.model.enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User implements Serializable {
    @Id
    private Integer Id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
