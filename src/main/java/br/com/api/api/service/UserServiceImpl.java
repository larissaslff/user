package br.com.api.api.service;

import br.com.api.api.domain.User;
import br.com.api.api.repositories.UserRepository;
import br.com.api.api.resource.exception.EmailJaCadastradoException;
import br.com.api.api.resource.exception.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
    @Override
    public User create(User user) {
       Optional<User> emailCadastrado = findByEmail(user.getEmail());
       if(emailCadastrado.isPresent()) {
           throw new EmailJaCadastradoException("Email já cadastrado");
       }
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        if(repository.findById(id).isPresent()) {
            return repository.findById(id);
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado");
    }

    @Override
    public Optional<User> update(Integer id, User user) {
        findById(id);
        user.setId(id);
        return Optional.of(repository.save(user));
    }

    private Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
