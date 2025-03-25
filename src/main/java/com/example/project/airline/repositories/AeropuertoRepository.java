package com.example.project.airline.repositories;
import com.example.project.airline.models.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
}