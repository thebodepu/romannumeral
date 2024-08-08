package com.erb.assess.romannumeral.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Data object to return array of roman numeral conversions
 * @author ebodepu
 */
public class MultipleRomanNumerals implements Serializable {
    private List<RomanNumeral> conversions;

    public MultipleRomanNumerals(List<RomanNumeral> conversions) {
        this.conversions = conversions;
    }

    public List<RomanNumeral> getConversions() {
        return conversions;
    }

    public void setConversions(List<RomanNumeral> conversions) {
        this.conversions = conversions;
    }

}
