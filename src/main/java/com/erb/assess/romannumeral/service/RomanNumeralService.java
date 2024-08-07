package com.erb.assess.romannumeral.service;

import com.erb.assess.romannumeral.exception.InvalidInputException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RomanNumeralService {
    String generateRomanNumeralForNumber(Integer query)  throws Exception;
    String generateRomanNumeralForNumber(Integer min, Integer max) throws Exception;
}
