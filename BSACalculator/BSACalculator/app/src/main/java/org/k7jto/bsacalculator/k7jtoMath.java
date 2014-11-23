package org.k7jto.bsacalculator;

/**
 * Created by John Overbaugh on 11/22/2014.
 * This class contains all of the math for BSACalculator. Implement math here,
 * and refactor to double here
 */
public class k7jtoMath {
    // ************************************************************************
    //             START REFACTORING HERE TO CHANGE TO DOUBLE
    // ************************************************************************
    public int getNumber(String numIn) {
        try {
            return Integer.parseInt(numIn);
        }
        catch (Exception ex) {
            // TODO: this is horrible, need to bubble up the exception
            return 0;
        }
    }

    // ************************************************************************
    //               Add math functions here
    // ************************************************************************
    
}
