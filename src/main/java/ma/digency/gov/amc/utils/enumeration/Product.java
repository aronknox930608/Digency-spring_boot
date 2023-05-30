package ma.digency.gov.amc.utils.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Product {


    Imprimer("1","livre imprimé","كتاب مطبوع"),
    Numerique("2","Livre numérique","كتاب رقمي"),
    Audio("3","Livre audio","كتاب صوتي");

    private String id;
    private String name;
    private String nameAr;

    public static String arabLabel(String code,String languge){
        for(Product b : Product.values()){
            if(b.getId().equals(code)){
                return languge.equalsIgnoreCase("ar") ? b.getNameAr():b.getName() ;
            }
        }
        return "";
    }
}
