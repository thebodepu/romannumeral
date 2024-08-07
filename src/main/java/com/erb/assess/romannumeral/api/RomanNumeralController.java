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
    public String sayHello() {
        return "Roman Numeral Service is up and running fine !";
    }

//    @GetMapping("/")
//    public ResponseEntity<RomanNumeral> romanNumeral(
//            @RequestParam(name = "query") @Valid @NotNull @DynamicRange Integer input
//    ) throws Exception {
//        logger.debug("Query Received for Roman Numeral : {}", input);
//
////        if (number < RomanNumeralService.MIN_ALLOWED_INPUT || number > RomanNumeralService.MAX_ALLOWED_INPUT) {
////            throw new InvalidInputException(
////                    String.format("Invalid Input Data Range : provided 'query' '%d' number is out of range [%d, %d].",
////                            number,
////                            RomanNumeralService.MIN_ALLOWED_INPUT,
////                            RomanNumeralService.MAX_ALLOWED_INPUT)
////            );
////        }
//
//        RomanNumeral romanNumeral = null;
////        try {
//            romanNumeral = romanNumeralService.generateRomanNumeralForNumber(new RomanNumeral(input));
////        } catch (InvalidInputException e) {
////            return ResponseEntity.badRequest().build();
////        }
//
//
//        logger.debug("Response - Roman Numeral Result: {}", romanNumeral);
//
//        return ResponseEntity
//                .ok(romanNumeral);
//    }

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
