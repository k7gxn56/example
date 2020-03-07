package hello;

import lombok.Getter;
import lombok.Setter;

public enum CountryEnum {

    ONE(1,"齐"),
    TWO(2,"韩"),
    THREE(3,"楚"),
    FOUR(4,"燕"),
    FIVE(5,"魏"),
    SIX(6,"赵");

    CountryEnum(Integer code, String county) {
        this.code = code;
        this.county = county;
    }

    @Getter
    private Integer code;

    @Getter
    private String county;

    public static CountryEnum getCountry(Integer i){

        for (CountryEnum element : CountryEnum.values()){
            if (i.equals(element.code)){
                return element;
            }
        }
        return null;
    }

    public static String getCountryName(Integer i){
        return getCountry(i).county;
    }



}
