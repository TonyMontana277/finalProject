package com.homework.finalProject.rest;

import com.homework.finalProject.Service.VisitorService;
import com.homework.finalProject.domain.Visitor;
import com.homework.finalProject.dto.VisitorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VisitorControllerTest {

    @Mock
    private VisitorService visitorService;

    @InjectMocks
    private VisitorController visitorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<VisitorDto> visitors = new ArrayList<>();
        visitors.add(new VisitorDto(1L, "John Doe", 1234567890L)); // Example visitor data
        when(visitorService.findAll()).thenReturn(visitors);

        // Act
        ResponseEntity<List<VisitorDto>> response = visitorController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visitors, response.getBody());
        verify(visitorService, times(1)).findAll();
    }

    @Test
    void testFindVisitorById_ExistingId() {
        // Arrange
        Long visitorId = 1L;
        VisitorDto visitorDto = new VisitorDto(1L, "John Doe", 1234567890L);
        when(visitorService.findVisitorById(visitorId)).thenReturn(Optional.of(visitorDto));

        // Act
        ResponseEntity<VisitorDto> response = visitorController.findVisitorById(visitorId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visitorDto, response.getBody());
        verify(visitorService, times(1)).findVisitorById(visitorId);
    }

    @Test
    void testFindVisitorById_NonExistingId() {
        // Arrange
        Long visitorId = 1L;
        when(visitorService.findVisitorById(visitorId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<VisitorDto> response = visitorController.findVisitorById(visitorId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(visitorService, times(1)).findVisitorById(visitorId);
    }

    @Test
    void testFindVisitorByName_ExistingName() {
        // Arrange
        String name = "John Doe";
        Visitor visitor = new Visitor();
        when(visitorService.findVisitorByName(name)).thenReturn(Optional.of(visitor));

        // Act
        ResponseEntity<Visitor> response = visitorController.findVisitorByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visitor, response.getBody());
        verify(visitorService, times(1)).findVisitorByName(name);
    }

    @Test
    void testFindVisitorByName_NonExistingName() {
        // Arrange
        String name = "John Doe";
        when(visitorService.findVisitorByName(name)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Visitor> response = visitorController.findVisitorByName(name);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(visitorService, times(1)).findVisitorByName(name);
    }

    @Test
    void testFindVisitorByPassportId_ExistingPassportId() {
        // Arrange
        Long passportId = 1234567890L;
        VisitorDto visitorDto = new VisitorDto(1L, "John Doe", 1234567890L);
        when(visitorService.findVisitorByPassportId(passportId)).thenReturn(Optional.of(visitorDto));

        // Act
        ResponseEntity<VisitorDto> response = visitorController.findVisitorByPassportId(passportId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visitorDto, response.getBody());
        verify(visitorService, times(1)).findVisitorByPassportId(passportId);
    }

    @Test
    void testFindVisitorByPassportId_NonExistingPassportId() {
        // Arrange
        Long passportId = 1234567890L;
        when(visitorService.findVisitorByPassportId(passportId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<VisitorDto> response = visitorController.findVisitorByPassportId(passportId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(visitorService, times(1)).findVisitorByPassportId(passportId);
    }

    @Test
    void testAddVisitor() {
        // Arrange
        Visitor visitor = new Visitor();
        when(visitorService.addVisitor(visitor)).thenReturn(visitor); // Mock the behavior of addVisitor

        // Act
        Visitor result = visitorController.addVisitor(visitor);

        // Assert
        verify(visitorService, times(1)).addVisitor(visitor);
        assertEquals(visitor, result);
    }


    @Test
    void testUpdateVisitorInformation() {
        // Arrange
        Long visitorId = 1L;
        Visitor visitor = new Visitor();

        // Act
        ResponseEntity<VisitorDto> response = visitorController.updateVisitorInformation(visitorId, visitor);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(visitorService, times(1)).updateVisitorInformation(visitorId, visitor);
    }

    // Rest of the test methods...

}
