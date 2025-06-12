package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.TipoSalaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TipoSalaServiceTest {

    @Autowired
    private TipoSalaService tipoSalaService;

    @MockitoBean
    private TipoSalaRepository tipoSalaRepository;

    @Test
    public void testFindAll() {
        when(tipoSalaRepository.findAll()).thenReturn(List.of(new TipoSala(1, "Sala Estudio")));

        List<TipoSala> tipos = tipoSalaService.findAll();

        assertNotNull(tipos);
        assertEquals(1, tipos.size());
        assertEquals("Sala Estudio", tipos.get(0).getNombre());
    }

    @Test
    public void testFindById() {
        TipoSala tipoSala = new TipoSala(1, "Laboratorio");
        when(tipoSalaRepository.findById(1)).thenReturn(Optional.of(tipoSala));

        TipoSala encontrado = tipoSalaService.findById(1);

        assertNotNull(encontrado);
        assertEquals(1, encontrado.getIdTipo());
        assertEquals("Laboratorio", encontrado.getNombre());
    }

    @Test
    public void testSave() {
        TipoSala tipoSala = new TipoSala(null, "Sala Computación");
        TipoSala guardado = new TipoSala(1, "Sala Computación");

        when(tipoSalaRepository.save(tipoSala)).thenReturn(guardado);

        TipoSala resultado = tipoSalaService.save(tipoSala);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdTipo());
        assertEquals("Sala Computación", resultado.getNombre());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(tipoSalaRepository).deleteById(1);

        tipoSalaService.deleteById(1);

        verify(tipoSalaRepository, times(1)).deleteById(1);
    }
}
