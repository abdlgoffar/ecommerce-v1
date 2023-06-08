package ecommerce.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ecommerce.v1.models.entities.security.Users;
import ecommerce.v1.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService() {
    }

    @Autowired
    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    //method override for load or login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.usersRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("user with email " + email + " not available")));
    }
    //method for save or register
    public Users save(Users users) {
        String passwordEncode = this.bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(passwordEncode);
        return this.usersRepository.save(users);
    }
    public List<Users> findAll() {
        List<Users> all = this.usersRepository.findAll();
        return all;
    }
}
