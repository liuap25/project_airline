package com.example.project.airline.services;

import com.example.project.airline.models.Pais;
import com.example.project.airline.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {
    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> obtenerTodos() {
        return paisRepository.findAll();
    }

    public Optional<Pais> obtenerPorId(Long id) {
        return paisRepository.findById(id);
    }

    public Pais guardar(Pais pais) {
        return paisRepository.save(pais);
    }
}
