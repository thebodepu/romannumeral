package com.erb.assess.romannumeral;

import com.erb.assess.romannumeral.exception.InvalidInputException;
import com.erb.assess.romannumeral.service.RomanNumeralServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RomanNumeralServiceImplTest {

    @InjectMocks
    private RomanNumeralServiceImpl romanNumeralService;

    @BeforeEach
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Enable pretty printing
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        romanNumeralService = new RomanNumeralServiceImpl(objectMapper);
    }

    @Test
    void given_a_valid_number_generate_correct_roman_numeral() throws Exception {

        // Given
        Integer query = 1;
        String expectedOutput = readJsonFromFile ("queryOne.json");

        // When
        String result = romanNumeralService.generateRomanNumeralForNumber(query);

        // Then
        assertEquals(expectedOutput, result, "The generated value should match the expected output");

    }

    @Test
    void given_a_valid_min_and_max_generate_correct_roman_numerals() throws Exception {

        // Given
        Integer min = 1;
        Integer max = 3;
        String expectedOutput = readJsonFromFile ("minmaxOneToThree.json");

        // When
        String result = romanNumeralService.generateRomanNumeralForNumber(min, max);

        // Then
        assertEquals(expectedOutput, result, "The generated value should match the expected output");
    }

    @Test
    void given_an_out_of_range_number_to_query_throw_invalid_input_exception() throws Exception {

        // Given
        Integer query = -1;

        // When & Then
        assertThrows(InvalidInputException.class, () -> romanNumeralService.generateRomanNumeralForNumber(query), "Input cannot be out of range");

    }

    @Test
    void given_a_number_larger_than_3999_to_query_throw_invalid_input_exception() throws Exception {

        // Given
        Integer query = 4000;

        // When & Then
        assertThrows(InvalidInputException.class, () -> romanNumeralService.generateRomanNumeralForNumber(query), "Input cannot be out of range");

    }

    @Test
    void given_min_is_larger_than_max_throw_invalid_input_exception() throws Exception {

        // Given
        Integer min = 200;
        Integer max = 100;

        // When & Then
        assertThrows(InvalidInputException.class, () -> romanNumeralService.generateRomanNumeralForNumber(min,max), "Input cannot be out of range");

    }

    @Test
    void given_a_valid_min_and_null_max_value_throw_invalid_input_exception() throws Exception {

        // Given
        Integer min = 1;
        Integer max = null;
        String expectedOutput = readJsonFromFile ("minmaxOneToThree.json");

        // When & Then
        assertThrows(InvalidInputException.class, () -> romanNumeralService.generateRomanNumeralForNumber(min,max), "Input cannot be out of range");

    }
    @Test
    void given_a_null_min_and_valid_max_value_throw_invalid_input_exception() throws Exception {

        // Given
        Integer min = null;
        Integer max = 2999;

        // When & Then
        assertThrows(InvalidInputException.class, () -> romanNumeralService.generateRomanNumeralForNumber(min,max), "Input cannot be out of range");

    }

    @Test
    void given_a_invalid_min_and_null_max_value_throw_invalid_input_exception() throws Exception {

        // Given
        Integer min = 0;
        Integer max = null;

        // When & Then
        assertThrows(InvalidInputException.class, () -> romanNumeralService.generateRomanNumeralForNumber(min,max), "Input cannot be out of range");

    }

    /**
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private String readJsonFromFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        return new String(Files.readAllBytes(Paths.get(resource.getURI())));
    }
}
