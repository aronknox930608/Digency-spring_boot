package ma.digency.gov.amc.dto.library;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class AuthorResponse {

    private String refAuthor;

    @NotEmptyString
    private String fullName;

    private String gender;


    private LocalDate birthDate;


    private LocalDate dateOfDeath;

    @NotEmptyString
    private String address;

    @NotEmptyString
    private String countryOfResidence;

    @NotEmptyString
    private String city;

    @NotEmptyString
    private String phoneNumber;

    private String fax;

    @Email
    private String email;

    private String webPage;

    private String biography;

    private String writingLanguage;

    private String areasOfWriting;

    private String publishedBooks;

    private String publishedArticles;

    private byte[] picture;
}
