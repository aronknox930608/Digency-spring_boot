package ma.digency.gov.amc.repository.entity.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.siel.Publication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author extends AcmAbstractAuditEntity {
    protected static final String AUTHOR_REF_AUTHOR= "ref_author";
    @Column(name = "ref_author")
    private String refAuthor;

    private String fullName;

    private String gender;

    private LocalDate birthDate;

    private LocalDate dateOfDeath;

    private String address;

    private String countryOfResidence;

    private String city;

    private String phoneNumber;

    private String fax;

    private String email;

    private String webPage;

    private String biography;

    private String writingLanguage;

    private String areasOfWriting;

    private String publishedBooks;

    private String publishedArticles;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    @JoinColumn(name = AUTHOR_REF_AUTHOR, referencedColumnName = AUTHOR_REF_AUTHOR, updatable = false)
    private List<Book> books;

    @Lob
    private byte[] picture;

}
