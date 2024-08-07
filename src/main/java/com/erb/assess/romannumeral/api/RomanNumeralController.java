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
     *
     * @return
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Roman Numeral Service is up and running fine !";
    }

    /**
     *
     * @param inputParams
     * @return
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
     *
     * @param inputParams
     * @return
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
