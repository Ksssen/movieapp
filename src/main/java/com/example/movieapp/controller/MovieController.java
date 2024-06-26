package com.example.movieapp.controller;

import com.example.movieapp.model.Movie;
import com.example.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model, Pageable pageable) {
        Page<Movie> page = movieService.findAll(pageable);
        model.addAttribute("page", page);
        return "movies/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/create";
    }

    @PostMapping
    public String createMovie(@Valid @ModelAttribute Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return "movies/create";
        }
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}")
    public String viewMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "error/404";
        }
        model.addAttribute("movie", movie);
        return "movies/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "error/404";
        }
        model.addAttribute("movie", movie);
        return "movies/edit";
    }

    @PostMapping("/{id}")
    public String updateMovie(@PathVariable Long id, @Valid @ModelAttribute Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return "movies/edit";
        }
        movieService.save(movie);
        return "redirect:/movies";
    }

    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return "redirect:/movies";
    }
}
