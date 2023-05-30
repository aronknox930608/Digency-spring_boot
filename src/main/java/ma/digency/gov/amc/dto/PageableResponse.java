package ma.digency.gov.amc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageableResponse<T> implements Serializable {

    private PageDetails pageDetails;

    private List<T> content;

    public PageableResponse(Page<T> page) {
        this.content = page.getContent();
        this.pageDetails = PageDetails.fromPage(page);

    }
}
