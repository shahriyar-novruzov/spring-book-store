package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Session;
import com.developia.bookstore.repository.SessionRepository;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository repository;

    SessionServiceImpl(SessionRepository sessionRepository) {
        this.repository = sessionRepository;
    }

    @Override
    public Session find() {
        Iterable<Session> sessions = repository.findAll();

        if (sessions.iterator().hasNext())
            return sessions.iterator().next();

        return null;
    }

    @Override
    public void delete() {
        repository.deleteAll();
    }
}
