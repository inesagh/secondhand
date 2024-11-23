package com.example.secondhandclothes.clothes.service;

import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.clothes.domain.Garment;
import com.example.secondhandclothes.user.domain.User;
import com.example.secondhandclothes.clothes.domain.GarmentRepository;
import com.example.secondhandclothes.user.domain.UserRepository;
import com.example.secondhandclothes.util.exception.AppException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@RunWith(MockitoJUnitRunner.class)
public class GarmentServiceImplTest {

    @Mock
    private GarmentRepository garmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GarmentServiceImpl garmentService;
    private Garment garment;
    private User publisher;

    @Before
    public void setUp() {
        publisher = new User();
        publisher.setId(1L);
        publisher.setUsername("test");
        publisher.setPassword("$2a$10$r7aTm6/91CjuEoh9Ye.fnOywr0M3ynKKySn6G9LIKi7Q7qUjFtH1S");
        publisher.setRoles("ROLES_USER");
        publisher.setFullName("test");
        publisher.setAddress("address");
        garment = new Garment();
        garment.setId(1L);
        garment.setType("shoes");
        garment.setDescription("black");
        garment.setSize("40");
        garment.setPrice(10.0);
        garment.setPublisher(publisher);
    }

    @Test
    public void createTest() {
        Mockito.when(userRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(publisher));
        Mockito.when(garmentRepository.save(Mockito.any())).thenReturn(garment);
        GarmentRequestDto requestDto = new GarmentRequestDto(garment.getType(), garment.getDescription(),
                garment.getSize(), garment.getPrice());

        GarmentResponseDto garmentResponseDto = garmentService.create(requestDto, 1L);
        assertEquals(garment.getType(), garmentResponseDto.getType());
        assertEquals(garment.getDescription(), garmentResponseDto.getDescription());
        assertEquals(garment.getPrice(), garmentResponseDto.getPrice());
        assertEquals(garment.getPublisher().getUsername(), garmentResponseDto.getPublisher().getUsername());
    }

    @Test
    public void getByIdTest() {
        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.of(garment));

        GarmentResponseDto garmentResponseDto = garmentService.getById(1L);
        assertEquals(garment.getType(), garmentResponseDto.getType());
        assertEquals(garment.getDescription(), garmentResponseDto.getDescription());
        assertEquals(garment.getSize(), garmentResponseDto.getSize());
    }

    @Test
    public void getNullByIdTest() {
        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.empty());

        GarmentResponseDto garmentResponseDto = garmentService.getById(1L);
        assertNull(garmentResponseDto);
    }

    @Test
    public void searchTest() {
        List<Garment> garmentList = List.of(garment);
        Mockito.when(garmentRepository.search(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble(),
                Mockito.anyLong())).thenReturn(garmentList);

        List<GarmentResponseDto> responseDtos = garmentService.search("shoes", "black", "40", 10.0, 1L);

        assertNotNull(responseDtos);
        assertEquals(1, responseDtos.size());
        GarmentResponseDto responseDto = responseDtos.get(0);
        assertEquals(garment.getType(), responseDto.getType());
        assertEquals(garment.getDescription(), responseDto.getDescription());
        assertEquals(garment.getPrice(), responseDto.getPrice());
        assertEquals(garment.getPublisher().getUsername(), responseDto.getPublisher().getUsername());
    }

    @Test
    public void getTest() {
        List<Garment> garmentList = List.of(garment);
        Mockito.when(garmentRepository.findAll()).thenReturn(garmentList);

        List<GarmentResponseDto> responseDtos = garmentService.get();

        assertNotNull(responseDtos);
        assertEquals(1, responseDtos.size());
        GarmentResponseDto responseDto = responseDtos.get(0);
        assertEquals(garment.getType(), responseDto.getType());
        assertEquals(garment.getDescription(), responseDto.getDescription());
        assertEquals(garment.getPrice(), responseDto.getPrice());
        assertEquals(garment.getPublisher().getUsername(), responseDto.getPublisher().getUsername());
    }

    @Test
    public void updateTest() throws AppException {
        Garment existingGarment = new Garment();
        existingGarment.setId(1L);
        existingGarment.setType("shoes");
        existingGarment.setDescription("black");
        existingGarment.setSize("40");
        existingGarment.setPrice(10.0);
        existingGarment.setPublisher(publisher);

        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.of(existingGarment));
        Mockito.when(garmentRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        GarmentRequestDto requestDto = new GarmentRequestDto("Jacket", "black", "L", 100.0);

        GarmentResponseDto responseDto = garmentService.update(requestDto, 1L, 1L);

        assertNotNull(responseDto);
        assertEquals("Jacket", responseDto.getType());
        assertEquals("black", responseDto.getDescription());
        assertEquals("L", responseDto.getSize());
        assertEquals(100.0, responseDto.getPrice());
        assertEquals(publisher.getUsername(), responseDto.getPublisher().getUsername());
    }

    @Test(expected = AppException.class)
    public void updateInvalidGarmentTest() throws AppException {
        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.empty());
        GarmentRequestDto requestDto = new GarmentRequestDto("Jacket", "A stylish jacket", "L", 100.0);

        garmentService.update(requestDto, 1L, 1L);
    }

    @Test(expected = AppException.class)
    public void updateWithUnAuthorizedPublisherTest() throws AppException {
        Garment existingGarment = new Garment();
        existingGarment.setId(1L);
        existingGarment.setPublisher(new User());

        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.of(existingGarment));
        GarmentRequestDto requestDto = new GarmentRequestDto("Jacket", "A stylish jacket", "L", 100.0);

        garmentService.update(requestDto, 1L, 1L);
    }

    @Test
    public void deleteTest() throws AppException {
        Garment existingGarment = new Garment();
        existingGarment.setId(1L);
        existingGarment.setPublisher(publisher);

        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.of(existingGarment));
        Mockito.doNothing().when(garmentRepository).deleteById(1L);

        boolean result = garmentService.delete(1L, 1L);

        assertTrue(result);
        Mockito.verify(garmentRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test(expected = AppException.class)
    public void deleteInvalidGarmentTest() throws AppException {
        Mockito.when(garmentRepository.findById(1L)).thenReturn(Optional.empty());
        garmentService.delete(1L, 1L);
    }
}
