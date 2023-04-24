package com.kisonich.learntest.magaz.services;

import com.kisonich.learntest.magaz.model.User;
import com.kisonich.learntest.magaz.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userDetails) {
        User user = getUser(userId);
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = getByLogin(login);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), u.getPassword(), true, true, true, true, new HashSet<>());
    }

//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
}

//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User getUserById(long id) {
//        try {
//            return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User updateUser(long id, User updatedUser) throws UserNotFoundException {
//        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//        user.setFio(updatedUser.getFio());
//        user.setEmail(updatedUser.getEmail());
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(long id) {
//        User user = null;
//        try {
//            user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        userRepository.delete(user);
//    }
//}