package com.impact.numberrangesummarizer;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class NumberCollectionManagerTest {
    private static NumberCollectionManager numberCollectionManager;
    private static NumberCollection numberCollectionActual;

    @BeforeEach
    void setUp() {
        numberCollectionManager = new NumberCollectionManager();
        numberCollectionActual = new NumberCollection();
        NumberCollection numberCollectionExpected = new NumberCollection();
        numberCollectionActual.setNumberString("1,2,3,5,6,7,13");
        numberCollectionExpected.setNumberString("1,2,3,5,6,7,13");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @Test
    void collect() {
        String numberStringActual = numberCollectionActual.getNumberString();
        String numberStringExpected = numberCollectionActual.getNumberString();
        Collection<Integer> expected= numberCollectionManager.collect(numberStringExpected);
        Collection<Integer> actual = numberCollectionManager.collect(numberStringActual);

        assertNotNull(actual);
        assertEquals(expected,actual);

        numberCollectionActual.setNumberString("13,7,6,5,3,2,1");
        numberStringActual = numberCollectionActual.getNumberString();
        actual = numberCollectionManager.collect(numberStringActual);
        assertNotEquals(expected,actual);
    }

    @Test
    void testIfIsIntArraySorted() {
        assertTrue(numberCollectionManager.isIntArraySorted(new int[]{1,1}));
        assertTrue(numberCollectionManager.isIntArraySorted(new int[]{1,2}));
        assertFalse(numberCollectionManager.isIntArraySorted(new int[]{2,1}));
        assertFalse(numberCollectionManager.isIntArraySorted(new int[]{2,1,0}));
        assertFalse(numberCollectionManager.isIntArraySorted(new int[]{1,2,0}));
        assertTrue(numberCollectionManager.isIntArraySorted(new int[]{0,1,2}));
    }

    @Test
    void testCollectionSize() {
        assertEquals(14, numberCollectionManager.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31").size());
    }

    @Test
    void testIfNumberStringIsSplit() {
        Collection<Integer> digits = numberCollectionManager.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        assertFalse(digits.isEmpty());
        assertTrue(digits.iterator().hasNext());
    }

    @Test
    void testThatNumberCollectionIsSummarized() {
        Collection<Integer> actual = numberCollectionManager.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String result = numberCollectionManager.summarizeCollection(actual);

        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected,result);

        expected = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        assertNotEquals(expected,result);
    }
}