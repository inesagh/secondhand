package com.example.secondhandclothes.controller.garment;

import com.example.secondhandclothes.controller.garment.models.GarmentRequestModel;
import com.example.secondhandclothes.controller.garment.models.GarmentResponseModel;
import com.example.secondhandclothes.exception.AppException;
import com.example.secondhandclothes.exception.GeneralResponse;
import com.example.secondhandclothes.service.GarmentService;
import com.example.secondhandclothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GarmentController {
    private final GarmentService garmentService;
    private final UserService userService;

    @Autowired
    public GarmentController(GarmentService garmentService, UserService userService) {
        this.garmentService = garmentService;
        this.userService = userService;
    }

    @GetMapping("/clothes")
    public ResponseEntity<List<GarmentResponseModel>> getAllGarments(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Long publisherId
            ) {
        return ResponseEntity.ok(garmentService.search(type, description, size, price, publisherId));
    }

    @GetMapping("/clothes/{id}")
    public ResponseEntity<GarmentResponseModel> getAllGarments(@PathVariable Long id) {
        return ResponseEntity.ok(garmentService.getById(id));
    }

    @PostMapping("/garment")
    public ResponseEntity<Object> publish(@RequestBody GarmentRequestModel garmentRequestModel) {
        try {
            Long authenticatedUserId = userService.getAuthenticatedUser().getId();
            return ResponseEntity.ok(garmentService.create(garmentRequestModel, authenticatedUserId));
        }  catch (AppException ex) {
            return ResponseEntity.ok(new GeneralResponse("Not authenticated user. Please authenticate first and try again.", HttpStatus.FORBIDDEN.value()));
        }
    }

    @PutMapping("/garment/{id}")
    public ResponseEntity<Object> update(@RequestBody GarmentRequestModel garmentRequestModel,
            @PathVariable Long id) {
        try {
            Long authenticatedUserId = userService.getAuthenticatedUser().getId();
            return ResponseEntity.ok(garmentService.update(garmentRequestModel, id, authenticatedUserId));
        } catch (AppException ex) {
            return ResponseEntity.ok(new GeneralResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/garment/{id}")
    public ResponseEntity<GeneralResponse> unpublish(@PathVariable Long id) {
        try {
            Long authenticatedUserId = userService.getAuthenticatedUser().getId();
            garmentService.delete(id, authenticatedUserId);
            return ResponseEntity.ok(new GeneralResponse("Garment unpublished successfully.", HttpStatus.OK.value()));
        } catch (AppException ex) {
            return ResponseEntity.ok(new GeneralResponse(ex.getMessage()));
        }
    }
}
