package biblioteca.salas.duoc.biblioteca.salas.duoc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.TipoSalaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TipoSalaServiceTest {

    @Autowired
    private TipoSalaService tipoSalaService;

    @Mock
    private TipoSalaRepository tipoSalaRepository;

    @Test
    public void testFindAll() {
        when(tipoSalaRepository.findAll()).thenReturn(List.of(new TipoSala(1, "Laboratorio")));

        List<TipoSala> tipos = tipoSalaService.findAll();
        assertNotNull(tipos);
        assertEquals(1, tipos.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        TipoSala tipoSala = new TipoSala(id, "Laboratorio");
        when(tipoSalaRepository.findById(id)).thenReturn(Optional.of(tipoSala));

        TipoSala found = tipoSalaService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getIdTipo());
    }

    @Test
    public void testSave() {
        TipoSala tipoSala = new TipoSala(1, "Laboratorio");
        when(tipoSalaRepository.save(tipoSala)).thenReturn(tipoSala);

        TipoSala saved = tipoSalaService.save(tipoSala);
        assertNotNull(saved);
        assertEquals("Laboratorio", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(tipoSalaRepository).deleteById(id);

        tipoSalaService.deleteById(id);
        verify(tipoSalaRepository, times(1)).deleteById(id);
    }
}
