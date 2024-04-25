package testproject.demo.service.impl;


import org.springframework.stereotype.Service;
import testproject.demo.entity.User;
import testproject.demo.repository.GenreRepository;
import testproject.demo.repository.UserRepository;
import testproject.demo.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        return  userRepository.findById((long) id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<User> searchUsers(String searchText) {
        return userRepository.findBooksBySearchText(searchText);
    }
}
