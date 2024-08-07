package com.erb.assess.romannumeral.dto;

import java.io.Serializable;

/**
 * Holds data required for a single number to roman record
 */
public class RomanNumeral implements Serializable {

    private Integer input;
    private String output;

    public RomanNumeral(Integer input) {
        this.input = input;
    }

    public RomanNumeral(Integer input, String output) {
        this.input = input;
        this.output = output;
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
