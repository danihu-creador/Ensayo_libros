package es.hazerta.app;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio: acceso a la base de datos sin escribir SQL.
 * Al extender JpaRepository ya tenemos findAll(), save(), deleteById(), count()...
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
