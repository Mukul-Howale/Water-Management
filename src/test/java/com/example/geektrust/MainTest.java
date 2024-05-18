package com.example.geektrust;

import com.example.geektrust.exceptions.*;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    Main main = new Main();
    @Test
    void ifBillCommandNotEntered(){
        String[] temp = new String[]{
                "ALLOT_WATER 2 3:7",
                "ADD_GUESTS 2",
                "ADD_GUESTS 3"
        };
        assertThrows(NoBillCommandException.class,
                () -> main.readCommands(new ArrayList<String>(Arrays.asList(temp))));
    }

    @Test
    void ifWaterAlreadyAllotted(){
        String[] temp = new String[]{
                "ALLOT_WATER 2 3:7",
                "ADD_GUESTS 2",
                "ALLOT_WATER 2 3:7",
                "ADD_GUESTS 3"
        };
        assertThrows(WaterAlreadyAllotedException.class,
                () -> main.readCommands(new ArrayList<String>(Arrays.asList(temp))));
    }

    @Test
    void ifWaterNotAllotted(){
        String[] temp = new String[]{
                "ADD_GUESTS 2",
                "ADD_GUESTS 3",
                "BILL"
        };
        assertThrows(WaterNotAllottedException.class,
                () -> main.readCommands(new ArrayList<String>(Arrays.asList(temp))));
    }

    @Test
    void ifBillAlreadyCalculated(){
        String[] temp = new String[]{
                "ALLOT_WATER 2 3:7",
                "ADD_GUESTS 2",
                "BILL",
                "ADD_GUESTS 3"
        };
        assertThrows(BillAlreadyCalculatedException.class,
                () -> main.readCommands(new ArrayList<String>(Arrays.asList(temp))));
    }

    @Test
    void ifWrongCommandEntered(){
        String[] temp = new String[]{
                "ALLOT_WATER 2 3:7",
                "SOMETHING",
                "BILL",
                "ADD_GUESTS 3"
        };
        assertThrows(WrongCommandEnteredException.class,
                () -> main.readCommands(new ArrayList<String>(Arrays.asList(temp))));
    }
}