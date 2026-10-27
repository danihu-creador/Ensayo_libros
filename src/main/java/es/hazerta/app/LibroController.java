package es.hazerta.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador: recibe las peticiones del navegador, usa el repositorio
 * y devuelve el nombre de la vista (plantilla HTML).
 */
@Controller
public class LibroController {

    private final LibroRepository repo;

    // Spring nos "inyecta" el repositorio aquí. No tocar.
    public LibroController(LibroRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/libros";
    }

    @GetMapping("/libros")
    public String listar(Model model) {
        model.addAttribute("libros", repo.findAll());
        return "lista";
    }

    @PostMapping("/libros/guardar")
    public String guardar(@ModelAttribute Libro libro) {
        repo.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/libros/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/libros";
    }
}
