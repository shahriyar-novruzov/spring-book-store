package com.developia.bookstore.repository;

import com.developia.bookstore.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
