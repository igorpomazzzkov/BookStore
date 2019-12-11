package Book.service;

import Book.entity.Book;
import Book.entity.Role;
import Book.entity.User;
import Book.repository.BookRepository;
import Book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User find(String username){
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFormDB = userRepository.findByUsername(user.getUsername());

        if(userFormDB != null){
            return false;
        }

        user.setActive(true);
        user.setRoleSet(Collections.singleton(Role.USER));
        user.setActiveCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
        if(!user.getEmail().isEmpty()){
            String message = String.format("Hello, %s \n" +
                    "Welcome to BookStore. Please visit next link: http://127.0.0.1:8080/activate/%s",
                    user.getFirstName()+" "+user.getLastName(), user.getActiveCode());
            mailSender.send(user.getEmail(), "Active Code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActiveCode(code);
        if(user == null){
            return false;
        }

        user.setActiveCode(null);
        userRepository.save(user);

        return true;
    }

    public List<User> findAll(  ) {
        return userRepository.findAll();
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user, User userForm, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoleSet().clear();
        for(String key: form.keySet()){
            if(roles.contains(key)){
                user.getRoleSet().add(Role.valueOf(key));
            }
        }
        user.setUsername(userForm.getUsername());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email, String firstName, String lastName){
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if(isEmailChanged){
            user.setEmail(email);
            if(!email.isEmpty()){
                user.setActiveCode(UUID.randomUUID().toString());
            }
        }

        if(!password.isEmpty()){
            user.setPassword(passwordEncoder.encode(password));
        }

        if(!firstName.isEmpty()){
            user.setFirstName(firstName);
        }

        if(!lastName.isEmpty()){
            user.setLastName(lastName);
        }

        userRepository.save(user);
        if(isEmailChanged){
            sendMessage(user);
        }
    }

    public void addToCart(User user, Book book){
        user.getBooks().add(book);
        userRepository.save(user);
    }

    public void removeFromCart(User user, Book book){
        user.getBooks().remove(book);
        userRepository.save(user);
    }


}
