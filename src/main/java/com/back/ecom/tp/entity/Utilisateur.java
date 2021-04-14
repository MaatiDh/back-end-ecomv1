package com.back.ecom.tp.entity;

import com.back.ecom.tp.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import static com.back.ecom.tp.constant.ColumnNameConstants.*;
import static com.back.ecom.tp.constant.TableNameConstants.UTILISATEUR;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = UTILISATEUR)
public class Utilisateur implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = NOM)
    private String nom;

    @Column(name = PRENOM)
    private String prenom;

    @Column(name = NOM_UTILISATEUR,unique = true)
    private String nomUtilisateur;

    @Column(name = EMAIL,unique = true)
    private String email;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = VILLE)
    private String ville;

    @Column(name = TEL)
    private String tel;

    @Column(name = ROLE)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
