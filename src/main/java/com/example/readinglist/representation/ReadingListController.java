package com.example.readinglist.representation;

import com.example.readinglist.infrastructure.Book;
import com.example.readinglist.infrastructure.Reader;
import com.example.readinglist.infrastructure.ReadingListRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {

    private String associateId;

    private ReadingListRepository readingListRepository;

    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    public void setAssociateId (String associateId) {
        this.associateId = associateId;
    }

    @GetMapping
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList = readingListRepository.findAllByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
        }
        return "readingList";
    }

    @PostMapping
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

}
