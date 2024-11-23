package com.example.secondhandclothes.service;

import com.example.secondhandclothes.repository.GarmentRepository;
import com.example.secondhandclothes.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    @Mock
    private GarmentRepository garmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GarmentServiceImpl garmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void create_ShouldSaveGarmentAndReturnResponse() {
//        Long publisherId = 1L;
//        GarmentRequestModel requestModel = new GarmentRequestModel();
//        GarmentDAO garmentDAO = new GarmentDAO();
//        GarmentResponseModel responseModel = new GarmentResponseModel();
//        User publisher = new User();
//
//        when(userRepository.findById(publisherId)).thenReturn(Optional.of(publisher));
//        when(Converter.toDaoFromRequestModel(requestModel)).thenReturn(garmentDAO);
//        when(garmentRepository.save(garmentDAO)).thenReturn(garmentDAO);
//        when(Converter.toResponseModelFromDao(garmentDAO)).thenReturn(responseModel);
//
//        GarmentResponseModel result = garmentService.create(requestModel, publisherId);
//
//        verify(garmentRepository).save(garmentDAO);
//        assertEquals(responseModel, result);
//    }
//
//    @Test
//    void getById_ShouldReturnGarmentResponseModelIfFound() {
//        Long id = 1L;
//        GarmentDAO garmentDAO = new GarmentDAO();
//        GarmentResponseModel responseModel = new GarmentResponseModel();
//
//        when(garmentRepository.findById(id)).thenReturn(Optional.of(garmentDAO));
//        when(Converter.toResponseModelFromDao(garmentDAO)).thenReturn(responseModel);
//
//        GarmentResponseModel result = garmentService.getById(id);
//
//        assertEquals(responseModel, result);
//    }
//
//    @Test
//    void getById_ShouldReturnNullIfNotFound() {
//        Long id = 1L;
//
//        when(garmentRepository.findById(id)).thenReturn(Optional.empty());
//
//        GarmentResponseModel result = garmentService.getById(id);
//
//        assertNull(result);
//    }
//
//    @Test
//    void search_ShouldReturnGarmentResponseModels() {
//        List<GarmentDAO> garmentList = Collections.singletonList(new GarmentDAO());
//        List<GarmentResponseModel> responseList = Collections.singletonList(new GarmentResponseModel());
//
//        when(garmentRepository.search(any(), any(), any(), any(), any())).thenReturn(garmentList);
//        when(Converter.toResponseModelFromDao(any())).thenReturn(responseList.get(0));
//
//        List<GarmentResponseModel> result = garmentService.search("type", "desc", "M", 10.0, 1L);
//
//        assertEquals(responseList, result);
//    }
//
//    @Test
//    void get_ShouldReturnAllGarments() {
//        List<GarmentDAO> garmentList = Collections.singletonList(new GarmentDAO());
//        List<GarmentResponseModel> responseList = Collections.singletonList(new GarmentResponseModel());
//
//        when(garmentRepository.findAll()).thenReturn(garmentList);
//        when(Converter.toResponseModelFromDao(any())).thenReturn(responseList.get(0));
//
//        List<GarmentResponseModel> result = garmentService.get();
//
//        assertEquals(responseList, result);
//    }
//
//    @Test
//    void update_ShouldUpdateGarment() throws AppException {
//        Long id = 1L;
//        Long publisherId = 1L;
//        GarmentRequestModel requestModel = new GarmentRequestModel();
//        GarmentDAO existingGarment = new GarmentDAO();
//        GarmentDAO updatedGarment = new GarmentDAO();
//        User publisher = new User();
//        publisher.setId(publisherId);
//        existingGarment.setPublisher(publisher);
//
//        when(garmentRepository.findById(id)).thenReturn(Optional.of(existingGarment));
//        when(Converter.toDaoFromRequestModel(requestModel)).thenReturn(updatedGarment);
//        when(garmentRepository.save(any())).thenReturn(existingGarment);
//
//        GarmentResponseModel result = garmentService.update(requestModel, id, publisherId);
//
//        verify(garmentRepository).save(existingGarment);
//    }
//
//    @Test
//    void delete_ShouldDeleteIfAuthorized() throws AppException {
//        Long garmentId = 1L;
//        Long publisherId = 1L;
//        GarmentDAO garment = new GarmentDAO();
//        User publisher = new User();
//        publisher.setId(publisherId);
//        garment.setPublisher(publisher);
//
//        when(garmentRepository.findById(garmentId)).thenReturn(Optional.of(garment));
//
//        boolean result = garmentService.delete(garmentId, publisherId);
//
//        verify(garmentRepository).deleteById(garmentId);
//        assertTrue(result);
//    }
//
//    @Test
//    void delete_ShouldThrowExceptionIfNotAuthorized() {
//        Long garmentId = 1L;
//        Long publisherId = 2L;
//        GarmentDAO garment = new GarmentDAO();
//        User publisher = new User();
//        publisher.setId(1L);
//        garment.setPublisher(publisher);
//
//        when(garmentRepository.findById(garmentId)).thenReturn(Optional.of(garment));
//
//        assertThrows(AppException.class, () -> garmentService.delete(garmentId, publisherId));
//    }
}
