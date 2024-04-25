package testproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testproject.demo.entity.Book;
import testproject.demo.entity.BookAndGenre;
import testproject.demo.entity.Genre;
import testproject.demo.entity.User;
import testproject.demo.repository.GenreRepository;
import testproject.demo.repository.UserRepository;
import testproject.demo.service.BookService;
import testproject.demo.service.GenreService;
import testproject.demo.service.UserService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping(value="/users")
    public String listUsers(Model model){ //used to transfer data between the view and controller
        List<User> genresName= new ArrayList<User>();
        String genreName="";
        model.addAttribute("usersList",userService.getAllUsers());
        return "users";
    }

    @GetMapping(value="/users/add")
    public String creatUserForm(Model model){
        Book book = new Book();
        User user=new User();
        model.addAttribute("user",user);
        return "add_users";
    }



    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user, Model model) throws Exception {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);


        if(user.getFname()=="") {
            JOptionPane.showMessageDialog(jf, "Name can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "add_users";

        }
        if(user.getLname()=="") {
            JOptionPane.showMessageDialog(jf, "Name can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "add_users";
        }

        if(user.getPhone()==""){
            JOptionPane.showMessageDialog(jf, "Phone can not be empty!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            return "add_users";
        }
        // book.setGenre(Integer.parseInt(genreRepository.findGenreByName(book.getGenre())));

        JOptionPane.showMessageDialog(jf, "Successfull!",
                "Added new user!", JOptionPane.INFORMATION_MESSAGE);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String updateUserForm(@PathVariable int id, Model model){

        model.addAttribute("user",userService.getUserById(id));
        return "edit_users"; //html file
    }

    @PostMapping(value="/users/{id}")
    public String updateUsers(@PathVariable int id, @ModelAttribute("user") User user, Model mode)  {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        if(user.getFname()=="") {
            JOptionPane.showMessageDialog(jf, "Name can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            // return "edit_books";
        }
        if(user.getLname()=="") {
            JOptionPane.showMessageDialog(jf, "Name can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            //    return "edit_books";
        }
        if(user.getPhone()=="") {
            JOptionPane.showMessageDialog(jf, "Phone can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            //  return "edit_books";
        }


        if(user.getFname()!="" && user.getLname()!="" && user.getPhone()!=""){
            User existingUser=userService.getUserById(id);
            existingUser.setFname(user.getFname());
            existingUser.setLname(user.getLname());
            existingUser.setPhone(user.getPhone());


            JOptionPane.showMessageDialog(jf, "Successfull!",
                    "Updated existing user!", JOptionPane.INFORMATION_MESSAGE);

            userService.updateUser(existingUser);

            return "redirect:/users";
        }
        return "edit_users";
    }


    @PostMapping(value="/users/search")
    public String forwardSearchUsers(@RequestParam String searchText) {
        return "redirect:/users/search" + searchText;
    }
    @GetMapping("/users/search/{searchText}")
    public String searchArticlesUser(Model model,@PathVariable String searchText) {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        List<User> foundArticles=userService.searchUsers(searchText);

        if (!foundArticles.isEmpty()) {
            model.addAttribute("usersList",foundArticles);
            return "users";


        } else {
            JOptionPane.showMessageDialog(jf, "Nothing found!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
        }
        return "users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (jf, "Are you sure you want to delete that user?","Warning",dialogButton);
        if(dialogResult == 0){
           userService.deleteUserById(id);
            return "redirect:/users";
        }
        return "redirect:/users";
    }

}
