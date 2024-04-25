package testproject.demo.service;

import testproject.demo.entity.Book;
import testproject.demo.entity.Genre;
import testproject.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(int id);

    User updateUser(User user);

    void deleteUserById(Long id);

    List<User> searchUsers(String searchText);

}
