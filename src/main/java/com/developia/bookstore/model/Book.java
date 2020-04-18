package com.developia.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String isbn;
    private String author;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer pageSize;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
    private BigDecimal rating;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Review> reviews;

    public BigDecimal getRating() {

        int count = 0;
        BigDecimal total = BigDecimal.ZERO;

        for (Review review : reviews) {
            if (review.getRate() != null) {
                count++;
                total = total.add(review.getRate());
            }
        }

        return count > 0 ? total.divide(new BigDecimal(count), RoundingMode.CEILING) : null;
    }
}
