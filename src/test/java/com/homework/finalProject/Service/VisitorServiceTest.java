package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Visitor;
import com.homework.finalProject.dto.VisitorDto;
import com.homework.finalProject.repository.VisitorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class VisitorServiceTest {
    private VisitorService visitorService;
    private VisitorRepository visitorRepository;

    @BeforeEach
    void setUp() {
        visitorRepository = Mockito.mock(VisitorRepository.class);
        visitorService = new VisitorService(visitorRepository);
    }

    @Test
    void testAddVisitor() {
        // Arrange
        Visitor visitor = new Visitor();
        visitor.setName("John Doe");
        visitor.setPassportId(Long.valueOf(123456));

        // Act
        when(visitorRepository.save(visitor)).thenReturn(visitor);
        Visitor result = visitorService.addVisitor(visitor);

        // Assert
        assertNotNull(result);
        assertEquals(visitor, result);

        verify(visitorRepository, times(1)).save(visitor);
    }

    @Test
    void testUpdateVisitorInformation() {
        // Arrange
        Long visitorId = 1L;
        Visitor existingVisitor = new Visitor();
        existingVisitor.setId(visitorId);
        existingVisitor.setName("John Doe");
        existingVisitor.setPassportId(Long.valueOf(123456));

        Visitor updatedVisitor = new Visitor();
        updatedVisitor.setName("Jane Smith");
        updatedVisitor.setPassportId(Long.valueOf(987654));

        when(visitorRepository.findById(visitorId)).thenReturn(Optional.of(existingVisitor));
        when(visitorRepository.save(existingVisitor)).thenReturn(existingVisitor);

        // Act
        VisitorDto visitorDto = visitorService.updateVisitorInformation(visitorId, updatedVisitor);

        // Assert
        assertEquals(existingVisitor.getId(), visitorDto.getId());
        assertEquals(updatedVisitor.getName(), visitorDto.getName());
        assertEquals(updatedVisitor.getPassportId(), visitorDto.getPassportId());

        verify(visitorRepository, times(1)).findById(visitorId);
        verify(visitorRepository, times(1)).save(existingVisitor);
    }
}
