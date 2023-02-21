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
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/user")
public class UserResource {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService service;
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDto){
        service.create(mapper.map(userDto, User.class));
        return new ResponseEntity<>(userDto, CREATED);
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> listaUserDTO = service.findAll().stream()
                .map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaUserDTO);
    }

    @GetMapping(ID)
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable Integer id){
        Optional<UserDTO> userDTO = service.findById(id).map(x -> mapper.map(x, UserDTO.class));
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping(ID)
    public ResponseEntity<Optional<UserDTO>> uptade(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        service.update(id,mapper.map(userDTO, User.class));
        return ResponseEntity.ok().body(Optional.of(userDTO));
    }

    @DeleteMapping(ID)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
