package ma.digency.gov.amc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Getter
@Setter
public class PageDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "le nombre d'éléments retournés")
    private int numberOfElements;

    @Schema(description = "la taille de la page")
    private int size;

    @Schema(description = "Le nombre total d'éléments au niveau de la BDD")
    private long totalElements;

    @Schema(description = "le nombre total de pages")
    private int totalPages;

    @Schema(description = "le numéro de la page courante")
    private int number;

    /**
     * Public constructor.
     * <p>
     * To construct a {@link PageDetails} with :
     * </p>
     */
    public PageDetails() {
        super();
    }

    /**
     * Public constructor.
     * <p>
     * To construct a {@link PageDetails} with :
     * </p>
     *
     * @param numberOfElements
     * @param size
     * @param totalElements
     * @param totalPages
     * @param number
     */
    public PageDetails(int numberOfElements, int size, long totalElements, int totalPages,
                       int number) {
        super();
        this.numberOfElements = numberOfElements;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.number = number;
    }

    /**
     * To get a {@link PageDetails} from {@link Page}.
     *
     * @param <T>
     * @param page
     * @return
     */
    public static <T> PageDetails fromPage(Page<T> page) {
        return new PageDetails(page.getNumberOfElements(), page.getSize(), page.getTotalElements(),
                page.getTotalPages(), page.getNumber());

    }


}
