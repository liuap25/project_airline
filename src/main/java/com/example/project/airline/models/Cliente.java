package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String password; // Nueva columna para contraseña

    @JsonManagedReference 
    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    public void actualizarDatos(String nuevoEmail) {
        this.email = nuevoEmail;
    }

    // Métodos de UserDetails para integración con Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Aquí puedes definir roles si los necesitas
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