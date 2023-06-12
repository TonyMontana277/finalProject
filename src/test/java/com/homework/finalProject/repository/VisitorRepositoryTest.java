package com.homework.finalProject.repository;

import com.homework.finalProject.domain.Visitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class VisitorRepositoryTest {
    private VisitorRepository visitorRepository;

    @Test
    void testFindVisitorByPassportId() {
        // Arrange
        Visitor visitor = new Visitor();
        visitor.setId(1L);
        visitor.setName("John Doe");
        visitor.setPassportId(123456789L);

        visitorRepository = Mockito.mock(VisitorRepository.class);
        when(visitorRepository.findVisitorByPassportId(123456789L)).thenReturn(Optional.of(visitor));

        // Act
        Optional<Visitor> result = visitorRepository.findVisitorByPassportId(123456789L);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(visitor, result.get());

        verify(visitorRepository, times(1)).findVisitorByPassportId(123456789L);
    }
    @Test
    void testFindVisitorByName() {
        // Arrange
        Visitor visitor = new Visitor();
        visitor.setId(1L);
        visitor.setName("John Doe");
        visitor.setPassportId(123456789L);

        visitorRepository = Mockito.mock(VisitorRepository.class);
        when(visitorRepository.findVisitorByName("John Doe")).thenReturn(Optional.of(visitor));

        // Act
        Optional<Visitor> result = visitorRepository.findVisitorByName("John Doe");

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(visitor, result.get());

        verify(visitorRepository, times(1)).findVisitorByName("John Doe");
    }
}
