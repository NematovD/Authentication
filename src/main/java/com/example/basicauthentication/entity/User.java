package com.example.basicauthentication.entity;

import com.example.basicauthentication.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true,nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    @Enumerated(EnumType.STRING) // enumerated ishlatmasak enum type ni 0 1 qilib saqlab ketadi,natijda renum type nomini yo'qotib qo'yamiz
    Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        role.getPermissions().stream()
                .peek(permission -> {
                    list.add(new SimpleGrantedAuthority(permission.name()));
                }).collect(Collectors.toList());

        list.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return list;
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
