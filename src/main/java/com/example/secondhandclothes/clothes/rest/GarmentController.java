package com.example.secondhandclothes.clothes.rest;

import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.util.exception.AppException;
import com.example.secondhandclothes.util.exception.GeneralResponse;
import com.example.secondhandclothes.clothes.service.GarmentService;
import com.example.secondhandclothes.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GarmentController {
    private final GarmentService garmentService;
    private final UserService userService;

    @Autowired
    public GarmentController(GarmentService garmentService, UserService userService) {
        this.garmentService = garmentService;
        this.userService = userService;
    }

    @GetMapping("/clothes")
    public ResponseEntity<List<GarmentResponseDto>> getAllGarments(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Long publisherId
            ) {
        return ResponseEntity.ok(garmentService.search(type, description, size, price, publisherId));
    }

    @GetMapping("/clothes/{id}")
    public ResponseEntity<GarmentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(garmentService.getById(id));
    }

    @PostMapping("/clothe")
    public ResponseEntity<Object> publish(@RequestBody GarmentRequestDto garmentRequestDto) {
        try {
            Long authenticatedUserId = userService.getAuthenticatedUser().getId();
            return ResponseEntity.ok(garmentService.create(garmentRequestDto, authenticatedUserId));
        }  catch (AppException ex) {
            return ResponseEntity.ok(new GeneralResponse("Not authenticated user. Please authenticate first and try again.", HttpStatus.FORBIDDEN.value()));
        }
    }

    @PutMapping("/clothe/{id}")
    public ResponseEntity<Object> update(@RequestBody GarmentRequestDto garmentRequestDto,
            @PathVariable Long id) {
        try {
            Long authenticatedUserId = userService.getAuthenticatedUser().getId();
            return ResponseEntity.ok(garmentService.update(garmentRequestDto, id, authenticatedUserId));
        } catch (AppException ex) {
            return ResponseEntity.ok(new GeneralResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/clothe/{id}")
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
