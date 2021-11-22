package ro.deloittedigital.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.deloittedigital.demo.exception.EmailAlreadyUsedException;
import ro.deloittedigital.demo.exception.EmailNotValidException;
import ro.deloittedigital.demo.model.domain.User;
import ro.deloittedigital.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User add(User user) {
        if(!patternMatches(user.getEmail())){
            throw new EmailNotValidException();
        }
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(user.getEmail());
        if (existingUser.isPresent()){
            throw new EmailAlreadyUsedException();
        }
        return userRepository.save(user);
    }

    public List<User> getAvailableUsers() {
        return userRepository.findAll();
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> get(String email) {
        return userRepository.findOneByEmailIgnoreCase(email);
    }

    public User update(User user) {
        return userRepository.save(user);
    }
    public static boolean patternMatches(String emailAddress) {
        String regexPattern="^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}

