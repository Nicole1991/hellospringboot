package com.example.readinglist.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book, Long>{

    List<Book> findAllByReader(Reader reader);
}
