package ma.digency.gov.amc.utils.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.CellType;

@AllArgsConstructor
@Getter
public enum DocumentColumnEnum {

    AUTHOR(0, CellType.STRING),
    TITLE(1, CellType.STRING),
    PUBLISHING_DATE(3, CellType.STRING),
    PUBLISHER(2, CellType.STRING),
    COPIES_NB(4, CellType.NUMERIC),
    AMOUT(5, CellType.NUMERIC),
    SPECIALITY(6, CellType.STRING),
    ISBN(8, CellType.STRING),
    LEGAL_DEPOSIT(7, CellType.STRING),
    COLIS(9, CellType.NUMERIC);

    private int cellIndex;

    private CellType cellType;

    public static DocumentColumnEnum cellTypeFrom(int i) {
        for (DocumentColumnEnum type : DocumentColumnEnum.values()) {
            if (type.getCellIndex() == i) {
                return type;
            }
        }
        return null;
    }

}
