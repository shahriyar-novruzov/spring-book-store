package com.developia.bookstore.service;

import com.developia.bookstore.model.Session;

public interface SessionService {
    Session find();
    void delete();
}
