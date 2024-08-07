package com.erb.assess.romannumeral.service;

import com.erb.assess.romannumeral.dto.MultipleRomanNumerals;
import com.erb.assess.romannumeral.dto.RomanNumeral;
import com.erb.assess.romannumeral.exception.InvalidInputException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class RomanNumeralServiceImpl implements RomanNumeralService {

    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralServiceImpl.class);

    public static final int MIN_ALLOWED_INPUT = 1;
    public static final int MAX_ALLOWED_INPUT = 3999;

    private final ObjectMapper objectMapper;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public RomanNumeralServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String generateRomanNumeralForNumber(Integer query) throws Exception {
        if (query == null) {
            logger.error("Null input query");
            throw new InvalidInputException("9991",
                    "Invalid input number: " + query + " valid range is from " + MIN_ALLOWED_INPUT + " to " + MAX_ALLOWED_INPUT);
        }
        if (query < MIN_ALLOWED_INPUT || query > MAX_ALLOWED_INPUT) {
            logger.error("Invalid input query: " + query);
            throw new InvalidInputException("9992",
                    "Invalid input number: " + query + " valid range is from " + MIN_ALLOWED_INPUT + " to " + MAX_ALLOWED_INPUT);
        }
        logger.debug("Generating Roman numeral for number: {}", query);

        RomanNumeral romanNumeral = numberToRoman(query);

        return objectMapper.writeValueAsString(romanNumeral);
    }

    @Override
    public String generateRomanNumeralForNumber(Integer min, Integer max) throws Exception {
        if ((min == null) || (max == null)) {
            logger.error("Invalid input number: min and max are null");
            throw new InvalidInputException("9993",
                    "Invalid input for either min or max. valid range is from " + MIN_ALLOWED_INPUT + " to " + MAX_ALLOWED_INPUT);
        }

        if ((min < MIN_ALLOWED_INPUT || min > MAX_ALLOWED_INPUT) || (max < MIN_ALLOWED_INPUT || max > MAX_ALLOWED_INPUT)) {
            logger.error("Invalid input number: min and max are not in range [{}, {}]", min, max);
            throw new InvalidInputException("9994",
                    "Invalid input for either min or max. valid range is from " + MIN_ALLOWED_INPUT + " to " + MAX_ALLOWED_INPUT);
        }

        if ((min > max )) {
            logger.error("Invalid input number: min > max");
            throw new InvalidInputException("9995",
                    "Invalid input. min should be less than max");
        }

        logger.debug("Generating Roman numeral from min: {}", min);
        logger.debug("Generating Roman numeral to max: {}", max);

//        MultipleRomanNumerals multipleRomanNumerals = new MultipleRomanNumerals();
//        List<RomanNumeral> conversions = new ArrayList<RomanNumeral>();
//
//        for (int i = min; i <= max; i++) {
//            conversions.add(numberToRoman(i));
//        }
//
//        multipleRomanNumerals.setConversions(conversions);

        MultipleRomanNumerals multipleRomanNumerals = computeMultiples(min, max);

        return objectMapper.writeValueAsString(multipleRomanNumerals);
    }



    /**
     *
     * @param min
     * @param max
     * @return
     */
    public MultipleRomanNumerals computeMultiples(int min, int max) throws Exception {
        // List to store Future objects
        List<Future<RomanNumeral>> futures = new ArrayList<>();

        // Submit tasks for parallel execution
        for (int index = min; index <= max; index++) {
            final int numberToConvert = index;
            futures.add(executorService.submit(() -> numberToRoman(numberToConvert)));
        }

        // List to store results
        List<RomanNumeral> conversions = new ArrayList<RomanNumeral>();

        // Collect results from futures
        for (Future<RomanNumeral> future : futures) {
            try {
                conversions.add(future.get());
            } catch (InterruptedException | ExecutionException exp) {
                logger.error("Parallel processing error", exp);
            }
        }

        if (conversions.isEmpty()) {
            throw new Exception("Could not parallel process the conversions. Please check logs.");
        }

        // Sort the all processed results in ascending order
        conversions.sort((n1, n2) -> Integer.compare(n1.getInput(), n2.getInput()));

        return new MultipleRomanNumerals(conversions);
    }

    /**
     *
     * @param number
     * @return
     */
    private RomanNumeral numberToRoman(Integer number) {

        // predefine the roman numerals and corresponding numbers
        int[] presetNumbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] presetRomanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        int numberToConvert = number;

        StringBuffer romanBuffer = new StringBuffer();

        while (number > 0) {
            if (number >= presetNumbers[index]) {
                romanBuffer.append(presetRomanNumerals[index]);
                number -= presetNumbers[index];
            } else {
                index++;
            }
        }

        return new RomanNumeral (numberToConvert, romanBuffer.toString());
    }

}
