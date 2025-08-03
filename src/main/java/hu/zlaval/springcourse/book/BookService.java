package hu.zlaval.springcourse.book;

import hu.zlaval.springcourse.book.calculators.BookPriceProvider;
import hu.zlaval.springcourse.book.calculators.RandomBookPriceCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private  BookRepository bookRepository;
   // private RandomBookPriceCalculator priceCalculator;
    private BookPriceProvider priceProvider;

    public BookService(
            BookRepository bookRepository,
            @Qualifier("discount") BookPriceProvider priceProvider
            //RandomBookPriceCalculator priceCalculator
    ) {
        this.bookRepository = bookRepository;
       // this.priceCalculator = priceCalculator;
        this.priceProvider = priceProvider;
    }

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    public BookEntity save(BookEntity book) {
        book.setPrice(priceProvider.providePrice());
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
