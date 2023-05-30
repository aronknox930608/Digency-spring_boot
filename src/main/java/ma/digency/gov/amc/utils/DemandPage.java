package ma.digency.gov.amc.utils;

import org.springframework.data.domain.Sort;

public class DemandPage {
    private int pageNumber=0;
    private int pageSize=10;
    private Sort.Direction sortDirection =Sort.Direction.ASC;
    private String sortBy= "refDemand";


    public int getPageNumber() {
        return pageNumber;
    }


    public int getPageSize() {
        return pageSize;
    }


    public Sort.Direction getSortDirection() {
        return sortDirection;
    }


    public String getSortBy() {
        return sortBy;
    }


    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }


    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

}
