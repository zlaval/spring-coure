package hu.zlaval.springcourse.book.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import hu.zlaval.springcourse.book.BookEntity;
import hu.zlaval.springcourse.book.BookService;
import hu.zlaval.springcourse.book.Genre;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

public class BookForm {

    private final Event closeEvent;
    private final BookEntity book;
    private final BookService bookService;

    private final Dialog dialog;
    private final TextField titleTxtInput;
    private final TextField authorTxtInput;
    private final TextField publisherTxtInput;
    private final IntegerField pagesIntInput;
    private final ComboBox<Genre> genreComboBox;
    private final Checkbox availableCheckBox;
    private final DatePicker publishedDatePicker;

    public interface Event {
        void onClose();
    }

    public BookForm(
            BookService bookService,
            BookEntity book,
            Event closeEvent
    ) {
        this.book = Objects.requireNonNullElseGet(book, BookEntity::new);
        this.bookService = bookService;
        this.closeEvent = closeEvent;

        this.dialog = new Dialog();
        this.dialog.setWidth("400px");

        this.titleTxtInput = new TextField("Cím");
        this.authorTxtInput = new TextField("Szerző");
        this.publisherTxtInput = new TextField("Kiadó");
        this.pagesIntInput = new IntegerField("Oldalszám");
        this.genreComboBox = new ComboBox<>("Műfaj");
        genreComboBox.setItems(Genre.values());
        this.availableCheckBox = new Checkbox("Kapható");
        this.publishedDatePicker = new DatePicker("Kiadás dátuma");


        var saveBtn = new Button("Mentés", _ -> saveBookListener());
        var cancelBtn = new Button("Mégsem", _ -> dialog.close());
        var deleteBtn = new Button("Törlés", _ -> deleteBookListener());


        var form = new FormLayout(titleTxtInput, authorTxtInput, publisherTxtInput,
                pagesIntInput, genreComboBox, availableCheckBox, publishedDatePicker);
        var actions = new HorizontalLayout(saveBtn, cancelBtn, deleteBtn);
        dialog.add(form, actions);

        if (!this.book.isNew()) {
            setBook(this.book);
        } else {
            deleteBtn.setEnabled(false);
        }

        dialog.open();
    }


    private void deleteBookListener() {
        bookService.deleteById(book.getId());
        closeEvent.onClose();
        dialog.close();
    }

    private void saveBookListener() {
        book.setTitle(titleTxtInput.getValue());
        book.setAuthor(authorTxtInput.getValue());
        book.setPublisher(publisherTxtInput.getValue());
        book.setPages(Optional.ofNullable(pagesIntInput.getValue()).orElse(0));
        book.setGenre(genreComboBox.getValue());
        book.setAvailable(availableCheckBox.getValue());
        book.setPublishedAt(
                Optional.ofNullable(publishedDatePicker.getValue())
                        .map(LocalDate::atStartOfDay)
                        .map(date -> date.atZone(ZoneId.systemDefault()).toInstant())
                        .orElse(null)

        );

        bookService.save(book);
        dialog.close();
        closeEvent.onClose();
    }

    private void setBook(BookEntity book) {
        titleTxtInput.setValue(book.getTitle());
        authorTxtInput.setValue(book.getAuthor());
        publisherTxtInput.setValue(book.getPublisher());
        pagesIntInput.setValue(Optional.ofNullable(book.getPages()).orElse(0));
        genreComboBox.setValue(book.getGenre());
        availableCheckBox.setValue(book.getAvailable());
        publishedDatePicker.setValue(Optional.ofNullable(book.getPublishedAt())
                .map(i -> i.atZone(ZoneId.systemDefault()).toLocalDate())
                .orElse(null)
        );
    }

}
