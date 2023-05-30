package ma.digency.gov.amc.utils;

import org.springframework.data.domain.Sort;

public class CardPage {

    private int numPage=0;
    private int sizePage=10;
    private Sort.Direction sortDirection=Sort.Direction.ASC;
    private String sortedBy="refCard";

    public int getNumPage() {
        return numPage;
    }


    public int getSizePage() {
        return sizePage;
    }


    public Sort.Direction getSortDirection() {
        return sortDirection;
    }


    public String getSortedBy() {
        return sortedBy;
    }

}
