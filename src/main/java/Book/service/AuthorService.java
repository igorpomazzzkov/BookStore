package Book.service;

import Book.entity.Author;
import Book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService{
    @Autowired
    private AuthorRepository authorRepository;

    public Author loadAuthorById(long id){
        return authorRepository.findById(id);
    }
}
