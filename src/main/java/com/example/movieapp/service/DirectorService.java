package com.example.movieapp.service;

import com.example.movieapp.model.Director;
import com.example.movieapp.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Director findById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }

    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
