package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.CarreraService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    private CarreraRepository carreraRepository;

    @InjectMocks
    private CarreraService carreraService;

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
