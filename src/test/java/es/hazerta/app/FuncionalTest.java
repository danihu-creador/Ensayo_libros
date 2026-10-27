package es.hazerta.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/** Arranca la aplicación entera y prueba las páginas como lo haría un navegador. */
@SpringBootTest(properties = {"spring.jpa.show-sql=false", "logging.level.root=WARN", "spring.main.banner-mode=off"})
@AutoConfigureMockMvc
@Transactional
class FuncionalTest {

    @Autowired MockMvc mvc;
    @Autowired JpaRepository<Libro, Long> repo;   // compila aunque falte el TODO 3

    @Test
    @DisplayName("La aplicación arranca y la base de datos tiene 3 datos de ejemplo")
    void arranca_aplicacion() {
        assertEquals(3, repo.count(), "Deben existir 3 filas de ejemplo (data.sql)");
    }

    @Test
    @DisplayName("TODO 5 y 8: /libros muestra los datos en una tabla")
    void todo5y8_listar() throws Exception {
        mvc.perform(get("/libros"))
           .andExpect(status().isOk())
           .andExpect(content().string(org.hamcrest.Matchers.containsString("Don Quijote")));
    }

    @Test
    @DisplayName("TODO 6: guardar añade una fila")
    void todo6_guardar() throws Exception {
        mvc.perform(post("/libros/guardar").param("titulo", "Nuevo").param("autor", "Autor Test"))
           .andExpect(status().is3xxRedirection());
        assertEquals(4, repo.count(), "Despues de guardar debe haber 4 filas");
    }

    @Test
    @DisplayName("TODO 7: borrar elimina una fila")
    void todo7_borrar() throws Exception {
        Long id = repo.findAll().get(0).getId();
        mvc.perform(get("/libros/borrar/" + id)).andExpect(status().is3xxRedirection());
        assertEquals(2, repo.count(), "Despues de borrar debe haber 2 filas");
    }
}
