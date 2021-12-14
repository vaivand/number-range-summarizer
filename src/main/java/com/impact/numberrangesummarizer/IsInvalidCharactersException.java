package com.impact.numberrangesummarizer;

//this class represents a custom Exception
public class IsInvalidCharactersException extends Exception {

//  calls the constructor of parent Exception
    public IsInvalidCharactersException(String errorMessage){
        super(errorMessage);
    }
}
