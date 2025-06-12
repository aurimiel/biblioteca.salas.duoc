package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.CarreraService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarreraServiceTest {

    @Autowired
    private CarreraService carreraService;
    @MockitoBean
    private CarreraRepository carreraRepository;

    @Test
    public void testFindAll() {
        Carrera carrera = new Carrera("1", "Ingeniería");
        when(carreraRepository.findAll()).thenReturn(List.of(carrera));

        List<Carrera> carreras = carreraService.findAll();

        assertNotNull(carreras);
        assertEquals(1, carreras.size());
        assertEquals("Ingeniería", carreras.get(0).getNombre());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Carrera carrera = new Carrera(codigo, "Ingeniería");

        when(carreraRepository.findById(codigo)).thenReturn(Optional.of(carrera));

        Carrera found = carreraService.findByCodigo(codigo);

        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        Carrera carrera = new Carrera("1", "Ingeniería");
        when(carreraRepository.save(carrera)).thenReturn(carrera);

        Carrera saved = carreraService.save(carrera);

        assertNotNull(saved);
        assertEquals("Ingeniería", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";
        doNothing().when(carreraRepository).deleteById(codigo);

        carreraService.deleteByCodigo(codigo);

        verify(carreraRepository, times(1)).deleteById(codigo);
    }
}
