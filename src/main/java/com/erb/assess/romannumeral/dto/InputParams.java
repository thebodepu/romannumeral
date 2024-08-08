package com.erb.assess.romannumeral.dto;


import com.erb.assess.romannumeral.validation.DynamicRange;

import com.erb.assess.romannumeral.validation.InputValidation;

import java.io.Serializable;

/**
 * Data object to hold input paramaters required for the API
 * @author ebodepu 
 */
@InputValidation
public class InputParams implements Serializable {

    @DynamicRange(message = "Number must be between ${validation.roman.number.min} and ${validation.roman.number.max}")
    private Integer query;

    @DynamicRange(message = "Number must be between ${validation.roman.number.min} and ${validation.roman.number.max}")
    private Integer min;

    @DynamicRange(message = "Number must be between ${validation.roman.number.min} and ${validation.roman.number.max}")
    private Integer max;

    public Integer getQuery() {
        return query;
    }

    public void setQuery(Integer query) {
        this.query = query;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
