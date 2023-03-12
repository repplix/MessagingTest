package com.github.repplix.domain.book;

import com.github.repplix.domain.DomainEventPublisher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    private static final ISBN13 ANY_BOOK = ISBN13.createISBN("978-3-86490-387-8" );

    @Test
    void addToStock() {
        // Arrange
        var objectUnderTest = Book.newBook(ANY_BOOK);
        var amountInStock = 5;

        // Act
        objectUnderTest.addToStock(amountInStock);

        // Assert
        assertEquals(amountInStock, objectUnderTest.amountInStock());
        assertTrue(objectUnderTest.inStock());
    }

    @Test
    void sell() throws BookNotInStockException {
        // Arrange
        var objectUnderTest = Book.newBook(ANY_BOOK);
        var amountInStock = 5;
        objectUnderTest.addToStock(amountInStock);

        // Act
        objectUnderTest.sell();

        // Assert
        assertEquals(amountInStock - 1, objectUnderTest.amountInStock());
        assertTrue(objectUnderTest.inStock());
    }

    @Test
    void sellOutOfStock() {
        // Arrange
        var objectUnderTest = Book.newBook(ANY_BOOK);

        // Act / Assert
        assertThrows( BookNotInStockException.class, objectUnderTest::sell);
    }

    @Test
    void sellLastBook() throws BookNotInStockException {
        // Arrange
        var amountInStock = 1;
        var domainEventRecorder = new DomainEventRecorder();
        var objectUnderTest = Book.newBook(ANY_BOOK);

        DomainEventPublisher.subscribe(BookSoldOut.class, domainEventRecorder::receive);
        objectUnderTest.addToStock(amountInStock);

        // Act
        objectUnderTest.sell();

        // Assert
        assertEquals(0, objectUnderTest.amountInStock() );
        assertEquals(1, domainEventRecorder.getDomainEvents().size() );
    }

    private static class DomainEventRecorder {
        private final List<BookSoldOut> domainEvents = new ArrayList<>();

        public void receive(BookSoldOut bookSoldOut)
        {
            domainEvents.add(bookSoldOut);
        }

        List<BookSoldOut> getDomainEvents()
        {
            return domainEvents;
        }
    }

}