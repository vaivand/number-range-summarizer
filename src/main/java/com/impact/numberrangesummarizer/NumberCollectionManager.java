package com.impact.numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

// check if string is sorted
public class NumberCollectionManager implements NumberRangeSummarizer {
    private static final String EXCEPTION_OCCURRED = "Exception occurred: ";
    static Logger logger = Logger.getLogger(NumberCollectionManager.class.getName());

    public static void main(String[] args) {
        NumberCollectionManager numberCollectionManager = new NumberCollectionManager();
        NumberCollection numberCollection = new NumberCollection();

//      Set the value of the numberString variable
        numberCollection.setNumberString("1,3,6,7,8,12,13,14,15,21,22,23,24,31");

//      Check if numberString is empty
        try{
            numberCollection.isEmptyString();
        }catch(IsEmptyStringException ex){
//          printing the message from InvalidAgeException object
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED,ex);
            System.exit(0);
        }

//      Check if the numberString is blank
        try{
            numberCollection.isBlankString();
        }catch(IsBlankStringException ex){
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED,ex);
            System.exit(0);
        }

//      Check if the numberString has invalid characters
        try{
            numberCollection.isInvalidCharacters();
        }catch(IsInvalidCharactersException ex){
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED,ex);
            System.exit(0);
        }

//      Read the value of the String variable
        String numberString = numberCollection.getNumberString();

//      Collect the input
        Collection<Integer> collectionOfNumbers = numberCollectionManager.collect(numberString);

//      Summarize the numbers
        String result =  numberCollectionManager.summarizeCollection(collectionOfNumbers);
        logger.log(Level.INFO,result);
    }


//  Implement the collect method
    @Override
    public Collection<Integer> collect(String input) {
        //Split string of numbers delimited by comma and store them in stringArray
        String[] stringArray = input.split(",");

        Collection<Integer> intCollection = new ArrayList<>();

        //Convert the numbers stored in the string array into integers and store in array list
        for (String s : stringArray) {
            try {
                intCollection.add(Integer.parseInt(s));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return intCollection;
    }

//  check if array is sorted
    public boolean isIntArraySorted(int[] intArray){
        for (int x = 0; x < intArray.length - 1; x++) {
            if (intArray[x] > intArray[x + 1])
                return false;
        }
        return true;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        StringBuilder result = new StringBuilder();

        int collectionSize = input.size();
        int[] numberArray = new int[collectionSize];

        int index = 0;
        for (Integer digit : input) {
            numberArray[index] = digit;
            index++;
        }

//      Check if the int array is sorted
        boolean isSorted = isIntArraySorted(numberArray);
        if(!isSorted){
//          Sort the int array
            Arrays.parallelSort(numberArray);
        }

//      track begin and end
        int begin;
        int end;
        end = begin = numberArray[0];

        for (int i = 1; i < numberArray.length; i++)
        {
            // as long as entries are consecutive, move end forward
            if (numberArray[i] == (numberArray[i - 1] + 1))
                end = numberArray[i];
            else
            {
                // when no longer consecutive, add group to result
                // depending on whether begin=end (single item) or not
                if (begin == end)
                    result.append(begin).append(", ");
                else if (end == (begin + 1))
                    result.append(begin).append(", ").append(end).append(", ");
                else
                    result.append(begin).append("-").append(end).append(", ");

                begin = end = numberArray[i];
            }
        }

//      handle the last segment
        if (begin == end)
            result.append(begin);
        else
            result.append(begin).append("-").append(end);

        return result.toString();
    }
}
