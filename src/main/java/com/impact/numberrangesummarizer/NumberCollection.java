package com.impact.numberrangesummarizer;


//POJO class
//Helps with readability and usability
public class NumberCollection
{
//variable
    private String numberString;

//methods
    //getter
    public String getNumberString(){
        return numberString;
    }

    //setter
    public void setNumberString(String numberString){
        this.numberString = numberString;
    }

    public void isEmptyString() throws IsEmptyStringException {
        if(numberString == null || numberString.isEmpty()) {
//          throw an object of user defined exception
            throw new IsEmptyStringException("Input string should not be empty.");
        }

    }

    public void isBlankString() throws IsBlankStringException {
        if(numberString.isBlank()){
//          throw an object of user defined exception
            throw new IsBlankStringException("Input string should not be blank.");
        }
    }

    public void isInvalidCharacters() throws IsInvalidCharactersException {
//      input validation to check whether stringArray contains any characters other than numbers and commas
        String regex = "[0-9,]+";

        if(!numberString.matches(regex)){
//          throw an object of user defined exception
            throw new IsInvalidCharactersException("Input string should only contain numbers and commas.");
        }
    }


}
