package ma.digency.gov.amc.repository.entity.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.library.LibraryOtherProduct;
import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import org.hibernate.annotations.Type;

import javax.persistence.ManyToMany;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Library extends AcmAbstractAuditEntity {
    protected static final String LIBRARY_REF_LIBRARY= "ref_library";
    protected static final String BOOK_REF_BOOK= "ref_book";

    @Column(name = "ref_library")
    private String refLibrary;

    private String libraryName;

    private String libraryOwnerName;

    private LocalDate libraryCreationDate;

    private String businessRegisterNumber;

    private String StandardDefinitionOfBusiness;

    private String cnssNumber;

    private String librarySpace;

    private String Address;

    private String city;

    private String phoneNumber;

    private String email;

    private String adressesNumber;

    @Type(type = "list-array")
    @Column(name = "BOOKS_LANGUAGE_SOLD", columnDefinition = "varchar[]")
    private List<String> booksLanguageSold;

    private Integer permanentEmployeesNumber;

    private Integer temporaryWorkersNumber;

    @Type(type = "list-array")
    @Column(name = "SUPPLY_SOURCE", columnDefinition = "varchar[]")
    private List<String> supplySource;

    @Type(type = "list-array")
    @Column(name = "CUSTOMER_BASE", columnDefinition = "varchar[]")
    private List<String> customerBase;

    @Type(type = "list-array")
    @Column(name = "LIBRARY_OTHER_PRODUCT", columnDefinition = "varchar[]")
    private List<String> libraryOtherProduct;

    @Type(type = "list-array")
    @Column(name = "SUGGESTED_TYPES_BOOK", columnDefinition = "varchar[]")
    private List<String> suggestedTypesBook;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "library_book",
            joinColumns = {
                    @JoinColumn(name = LIBRARY_REF_LIBRARY, referencedColumnName = LIBRARY_REF_LIBRARY, updatable = false
                    )},
            inverseJoinColumns = {
                    @JoinColumn(name = BOOK_REF_BOOK , referencedColumnName = BOOK_REF_BOOK, updatable = false
                    ) })
    private List<Book> books;

}
