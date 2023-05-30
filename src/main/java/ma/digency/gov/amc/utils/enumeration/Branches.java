package ma.digency.gov.amc.utils.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Branches {


    Editeur("1","Editeur","محرر"),
    Imprimeur("2","Imprimeur","طابع"),
    Distributeur("3","Distributeur","موزع"),
    Librairie("4","Librairie","مكتبة"),
    Gouvernement("5","Gouvernement","حكومة"),
    Association("6","Association","منظمة");

    private String id;
    private String name;
    private String nameAr;

    public static String arabLabel(String code,String languge){
        for(Branches b : Branches.values()){
            if(b.getId().equals(code)){
                return languge.equalsIgnoreCase("ar") ? b.getNameAr():b.getName() ;
            }
        }
        return "";
    }


}
