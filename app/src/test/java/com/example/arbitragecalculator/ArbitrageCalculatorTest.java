package com.example.arbitragecalculator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.text.Spanned;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class ArbitrageCalculatorTest {

    @Mock
    ArbitrageCalculator calculator = mock(ArbitrageCalculator.class);

    @Test
    public void calculateStuff() {
        //WHY IS IT FALSE, ITS TRUE, EVEN THE APP SAYS ITS RIGHT BUT WHY NOT YOU
        when(calculator.calculateStuff(1.9,1.9,1.4,2.5)).thenReturn(true);
        boolean test = verify(calculator).test;
        //The junit asserts works as intended but its the mockito failing to get the boolean right
        assertEquals(true, test);
        /*
        ArbitrageCalculator calculator = mock(ArbitrageCalculator.class);
        when(calculator.calculateStuff(1.9,1.9,1.4,2.5)).thenReturn(true);
        assertEquals(true, calculator.debug_calculationResult);
        //assertTrue(calculator.debug_calculationResult);
        */
    }

    @Test
    public void calculateStuff2() {
        ArbitrageCalculator calculator = mock(ArbitrageCalculator.class);
        //oolean check = calculator.calculateStuff(1.9,1.9,1.4,2.5);
        when(calculator.calculateStuff(1.9,1.9,1.4,1.4));
        //assertEquals(true);
        //assertTrue(calculator.debug_calculationResult);
    }
}