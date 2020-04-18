package com.developia.bookstore.repository;

import com.developia.bookstore.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
