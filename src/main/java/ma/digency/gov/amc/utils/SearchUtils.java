package ma.digency.gov.amc.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SearchUtils {

    private static final int DEFAULT_PAGINATION_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGINATION_SIZE = 10;

    /**
     * private constructor.
     */
    private SearchUtils() {

    }


    /**
     * Create {@link Pageable} using page number, page size and sort fields.
     *
     * @param page page number
     * @param size page size
     * @return Pageable
     */
    public static Pageable createPageable(final Integer page, final Integer size) {
        if (page == null && size != null) {
            return PageRequest.of(DEFAULT_PAGINATION_PAGE_NUMBER, size == 0?DEFAULT_PAGINATION_SIZE:size);
        } else if (page != null && size == null) {
            return PageRequest.of(page, DEFAULT_PAGINATION_SIZE);
        } else if (page == null && size == null) {
            return PageRequest.of(DEFAULT_PAGINATION_PAGE_NUMBER, DEFAULT_PAGINATION_SIZE);
        } else {
            return PageRequest.of(page, size == 0?DEFAULT_PAGINATION_SIZE:size);
        }
    }

    public static Pageable createPageableWithSort(final Integer page, final Integer size, Sort sort) {
        if (page == null && size != null) {
            return PageRequest.of(DEFAULT_PAGINATION_PAGE_NUMBER, size == 0?DEFAULT_PAGINATION_SIZE:size,sort);
        } else if (page != null && size == null) {
            return PageRequest.of(page, DEFAULT_PAGINATION_SIZE,sort);
        } else if (page == null && size == null) {
            return PageRequest.of(DEFAULT_PAGINATION_PAGE_NUMBER, DEFAULT_PAGINATION_SIZE,sort);
        } else {
            return PageRequest.of(page, size == 0?DEFAULT_PAGINATION_SIZE:size,sort);
        }
    }

    /*
        As all entites extend from audit entity, it is going to be helpful if we set a sorting for modification date
        since all entites have this field, and for better UX, we need to display most recent updated data
     */
    public static  Pageable createPageableSortModificationDate(final Integer page, final Integer size)
    {
        return createPageableWithSort(page,size,Sort.by("dateModification").descending());
    }

    public static  Pageable createPageableSortCreationDate(final Integer page, final Integer size)
    {
        return createPageableWithSort(page,size,Sort.by("dateCreation").descending());
    }
}

