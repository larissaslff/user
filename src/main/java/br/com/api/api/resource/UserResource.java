package br.com.api.api.resource;

import br.com.api.api.domain.DTO.UserDTO;
import br.com.api.api.domain.User;
import org.modelmapper.ModelMapper;
import br.com.api.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService service;
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(service.create(user), CREATED);
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable Integer id){
        Optional<UserDTO> userDTO = service.findById(id).map(x -> mapper.map(x, UserDTO.class));
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> uptade(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok().body(service.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
