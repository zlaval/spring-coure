package hu.zlaval.springcourse.book;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import hu.zlaval.springcourse.book.components.BookForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Route("books")
public class BookView extends VerticalLayout {

    private final BookService bookService;
    private final Grid<BookEntity> grid;

    @Autowired
    public BookView(BookService bookService) {
        this.bookService = bookService;
        this.grid = new Grid<>(BookEntity.class);

        setSizeFull();
        setupToolBar();
        setupGrid();
        loadBooks();
    }


    private void setupToolBar() {
        Button addBtn = new Button("Új könyv", e -> openForm(null));
        add(new HorizontalLayout(addBtn));
    }

    private void setupGrid() {
        grid.removeAllColumns();
        grid.addColumn(BookEntity::getId).setHeader("Azonosító");
        grid.addColumn(BookEntity::getTitle).setHeader("Cím");
        grid.addColumn(BookEntity::getAuthor).setHeader("Szerző");
        grid.addColumn(BookEntity::getPublisher).setHeader("Kiadó");
        grid.addColumn(BookEntity::getPages).setHeader("Oldalszám");
        grid.addColumn(BookEntity::getGenre).setHeader("Műfaj");
        grid.addColumn(BookEntity::getAvailable).setHeader("Kapható");

        grid.addColumn(book -> Optional.ofNullable(book.getPublishedAt())
                .map(Instant::toString)
                .orElse("")
        ).setHeader("Kiadás dátuma");

        grid.setSizeFull();
        grid.addItemClickListener(event -> openForm(event.getItem()));
        add(grid);
    }


    private void openForm(final BookEntity book) {
        new BookForm(bookService, book, this::loadBooks);
    }


    private void loadBooks() {
        List<BookEntity> books = bookService.findAll();
        grid.setItems(books);
    }
}