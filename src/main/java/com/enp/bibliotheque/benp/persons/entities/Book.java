package com.enp.bibliotheque.benp.persons.entities;


import com.enp.bibliotheque.benp.persons.enums.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name =  "BOOK")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable =false,  length = 100)
    @Size(max = 100)
    private String name;

    @Column(nullable =false,  length = 100)
    @Size(max = 100)
    private String author;

    @Column(nullable =false,  length = 100)
    private Integer stock;
    
    @Column(nullable =false, length = 100)
    private BookFormat format;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("author", author)
                .append("stock", stock)
                .append("format", format)
                .toString();
    }
}

