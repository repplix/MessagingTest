package com.github.repplix.domainservice;

import com.github.repplix.domain.book.Book;
import io.jexxa.addend.applicationcore.DomainService;
import com.github.repplix.domain.book.BookRepository;
import com.github.repplix.domain.book.ISBN13;

import java.util.Objects;
import java.util.stream.Stream;

import static com.github.repplix.domain.book.ISBN13.createISBN;

@SuppressWarnings("unused")
@DomainService
public class ReferenceLibrary
{
    private final BookRepository bookRepository;

    @SuppressWarnings("unused")
    public ReferenceLibrary(BookRepository bookRepository)
    {
        this.bookRepository = Objects.requireNonNull(bookRepository);
        addLatestBooks();
    }

    public void addLatestBooks()
    {
        getLatestBooks()
                .filter(book -> ! bookRepository.isRegistered(book)) // Filter already maintained books.
                .forEach(isbn13 -> bookRepository.add(Book.newBook(isbn13)));
    }

    /** Some Random books found in internet */
    private Stream<ISBN13> getLatestBooks()
    {
        return Stream.of(
                createISBN("978-1-60309-025-4" ),
                createISBN("978-1-60309-025-4" ),
                createISBN("978-1-60309-047-6" ),
                createISBN("978-1-60309-322-4" ),
                createISBN("978-1-891830-85-3" ),
                createISBN("978-1-60309-016-2" ),
                createISBN("978-1-60309-265-4" )
        );
    }
}
