package com.impact.numberrangesummarizer;

import org.junit.jupiter.api.*;

class NumberCollectionTest {

    private static NumberCollection numberCollection;

    @BeforeAll
    public static void setup() {
        System.out.println("Instantiating Collection before the Test Execution");
        numberCollection = new NumberCollection();
    }

    @Test
    @DisplayName("Input string should not be empty.")
    void shouldThrowExceptionWhenNumberStringIsEmpty() {
        numberCollection.setNumberString("");
        Assertions.assertThrows(IsEmptyStringException.class, () -> numberCollection.isEmptyString());
    }

    @Test
    @DisplayName("Input string should not be blank.")
    void shouldThrowExceptionWhenNumberStringIsBlank(){
        numberCollection.setNumberString(" ");
        Assertions.assertThrows(IsBlankStringException.class, () -> numberCollection.isBlankString());
    }

    @Test
    @DisplayName("Input string should only contain numbers and commas.")
    void shouldThrowExceptionWhenNumberStringIsInvalid(){
        numberCollection.setNumberString("1,&,2");
        Assertions.assertThrows(IsInvalidCharactersException.class, () -> numberCollection.isInvalidCharacters());
    }

}