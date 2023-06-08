package ecommerce.v1.controllers;

import ecommerce.v1.configurations.components.jwt.JwtUtil;
import ecommerce.v1.configurations.components.others.Response;
import ecommerce.v1.models.dto.user.create.UsersDto;
import ecommerce.v1.models.entities.security.Users;
import ecommerce.v1.services.UsersService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    private UsersService usersService;
    private Response<Users, UsersDto> responseCreate;
    private ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
    @Autowired
    public void setResponseCreate(Response<Users, UsersDto> responseCreate) {
        this.responseCreate = responseCreate;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<Users> findAll() {
        return this.usersService.findAll();
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid UsersDto usersDto, Errors errors) {
        return this.responseCreate.create(
                (entity) -> this.usersService.save(entity),
                this.modelMapper,
                Users.class,
                usersDto,
                errors);
    }

    @RequestMapping(value = "/users/authentication", method = RequestMethod.POST)
    public String auth(@RequestBody ecommerce.v1.models.dto.user.authentication.UsersDto usersDto) {
        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usersDto.getEmail(), usersDto.getPassword()));
        if (authenticate.isAuthenticated()) {
            return this.jwtUtil.generateToken(usersDto.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user... !");
        }
    }

}
