package com.enp.bibliotheque.benp.persons.dto;

import com.enp.bibliotheque.benp.persons.enums.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String name;
    private String author;
    private Long stock;
    private BookFormat format;

}
