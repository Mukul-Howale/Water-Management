package com.example.geektrust;

import com.example.geektrust.exceptions.ApartmentNotEqualToTwoOrThreeBHKException;
import com.example.geektrust.exceptions.GuestsNumberZeroOrNegativeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApartmentTest {

    Apartment apartment = new Apartment();

    @Test
    void ifApartmentNotEqualTwoOrThreeBHK(){
        assertThrows(ApartmentNotEqualToTwoOrThreeBHKException.class,
                () -> apartment.setTypeAndRatio(1,"2:4"));
    }

    @Test
    void ifNumberOfGuestsZeroOrNegative(){
        assertThrows(GuestsNumberZeroOrNegativeException.class,
                () -> apartment.addGuests(-1));
    }
}
