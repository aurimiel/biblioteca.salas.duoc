package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.TipoSalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Para que los datos se borren al finalizar cada test
public class TipoSalaServiceIntegrationTest {

    @Autowired
    private TipoSalaService tipoSalaService;

    @Autowired
    private TipoSalaRepository tipoSalaRepository;

    @BeforeEach
    public void setUp() {
        tipoSalaRepository.deleteAll(); // Limpieza por si hay residuos
        tipoSalaRepository.save(new TipoSala(null, "Sala Estudio"));
    }

    @Test
    public void testFindAll() {
        List<TipoSala> tipos = tipoSalaService.findAll();
        assertNotNull(tipos);
        assertEquals(1, tipos.size());
    }

    @Test
    public void testFindById() {
        TipoSala tipoGuardado = tipoSalaRepository.save(new TipoSala(null, "Laboratorio"));
        TipoSala encontrado = tipoSalaService.findById(tipoGuardado.getIdTipo());

        assertNotNull(encontrado);
        assertEquals("Laboratorio", encontrado.getNombre());
    }

    @Test
    public void testSave() {
        TipoSala tipoSala = new TipoSala(null, "Sala Computación");
        TipoSala saved = tipoSalaService.save(tipoSala);

        assertNotNull(saved.getIdTipo());
        assertEquals("Sala Computación", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        TipoSala tipoSala = tipoSalaRepository.save(new TipoSala(null, "Temporal"));
        Integer id = tipoSala.getIdTipo();

        tipoSalaService.deleteById(id);

        assertFalse(tipoSalaRepository.findById(id).isPresent());
    }
}
