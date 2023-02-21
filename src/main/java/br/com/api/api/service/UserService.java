package br.com.api.api.service;

import br.com.api.api.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    User create(User user);

    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> update(Integer id, User user);
}
