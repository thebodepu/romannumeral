package com.erb.assess.romannumeral.api;

import com.erb.assess.romannumeral.dto.InputParams;
import com.erb.assess.romannumeral.service.RomanNumeralService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * REST API to convert integers into Roman numerals
 * @author ebodepu
 *
 */
@RestController
@Validated
public class RomanNumeralController {

    private final RomanNumeralService romanNumeralService;
    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralController.class);

    @Autowired
    public RomanNumeralController(RomanNumeralService romanNumeralService) {
        this.romanNumeralService = romanNumeralService;
    }

    /**
     * Checks the health of the REST API
     *
     * @return
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Roman Numeral Service is up and running fine !";
    }

    /**
     * Converts given integer into Roman numeral
     *
     * @param inputParams
     * @return When input parameter 'query' is present, the equivalent Roman numeral would be returned in JSON format
     *          If the input parameters 'min' and 'max' are present, an array of Roman numerals starting from 'min' and upto '
     *          max' would be return in JSON format
     * @throws Exception
     */
    @GetMapping("/")
    public ResponseEntity<String> romanNumeralMinMax(@Valid InputParams inputParams
    ) throws Exception {
        logger.debug("Query Received for Roman Numeral : query : {}", inputParams.getQuery());
        logger.debug("Query Received for Roman Numeral : min : {}", inputParams.getMin());
        logger.debug("Query Received for Roman Numeral : max : {}", inputParams.getMax());

        String romanNumeral = convertToRoman(inputParams);

        logger.debug("Response - Roman Numeral Result: {}", romanNumeral);

        return ResponseEntity
                .ok(romanNumeral);
    }

    /**
     * Calls respective service method depending on the input parameter
     *
     * @param inputParams
     * @return JSON response of input and output
     * @throws Exception
     */
    private String convertToRoman(InputParams inputParams) throws Exception {
        String romanNumeral;
        if (inputParams.getQuery() != null) {
            romanNumeral = romanNumeralService.generateRomanNumeralForNumber(inputParams.getQuery());
        } else {
            romanNumeral = romanNumeralService.generateRomanNumeralForNumber(inputParams.getMin(), inputParams.getMax());
        }
        return romanNumeral;
    }

}
