package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private String author;
    private int pages;
    private String izdat;
    private String genre;
    private String[] tag;
}
