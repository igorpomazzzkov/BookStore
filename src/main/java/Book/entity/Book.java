package Book.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    @OrderBy(value = "name asc")
    private String name;

    @Column(name = "date_of_publication", nullable = false)
    private Date dateOfPublication;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "count_of_page", nullable = false)
    private int countOfPage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "filename")
    private String filename;

    @ManyToOne
    @JoinColumn(name = "publishing_id")
    private Publishing publishing;

    @Column(name = "description")
    private String text;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<User>();

    @ManyToMany
    @JoinTable(name = "book_category",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories = new ArrayList<Category>();

    public Book() {
    }

    public Book(String name, Date dateOfPublication, double price, int countOfPage, Author author, Publishing publishing, String text) {
        setName(name);
        setDateOfPublication(dateOfPublication);
        setPrice(price);
        setCountOfPage(countOfPage);
        setAuthor(author);
        setPublishing(publishing);
        setText(text);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        Book book = (Book) object;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getPublishingHouse() {
        return publishing != null ? publishing.getName() : "";
    }

    public String getDateOfPublicationToString() {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM yyyy", myDateFormatSymbols);
        return dateFormat.format(dateOfPublication);
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };

    public String getAuthorName() {
        return author != null ? author.getFirstName() + " " + author.getLastName() : "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCountOfPage() {
        return countOfPage;
    }

    public void setCountOfPage(int countOfPage) {
        this.countOfPage = countOfPage;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Publishing getPublishing() {
        return publishing;
    }

    public void setPublishing(Publishing publishing) {
        this.publishing = publishing;
    }

    public String getText() {
        return !text.isBlank() ? text.toString() : "Здесь должно быть описание книги, но администратор его не добавил";
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}