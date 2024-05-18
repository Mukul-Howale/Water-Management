package com.example.geektrust;

import com.example.geektrust.exceptions.ApartmentNotEqualToTwoOrThreeBHKException;
import com.example.geektrust.exceptions.GuestsNumberZeroOrNegativeException;

public class Apartment {
    Integer apartmentType;
    Integer corporationWaterRatio;
    Integer borewellWaterRatio;
    Integer noOfGuests = 0;
    boolean waterAlreadyAllotted;
    boolean billAlreadyCalculated;
    WaterRates waterRates = new WaterRates();
    ApartmentValues apartmentValues = new ApartmentValues();

    public void setTypeAndRatio(Integer apartmentType, String ratio){
        if(apartmentType.equals(apartmentValues.getTwoBedroom()) || apartmentType.equals(apartmentValues.getThreeBedroom())) {
            this.apartmentType = apartmentType;
            String[] ratioSplit = ratio.split(":");
            corporationWaterRatio = Integer.parseInt(ratioSplit[0]);
            borewellWaterRatio = Integer.parseInt(ratioSplit[1]);
        }
        else{
            throw new ApartmentNotEqualToTwoOrThreeBHKException("Apartment should be 2 or 3 BHK");
        }
    }

    public void addGuests(Integer noOfGuests){
        if(noOfGuests > apartmentValues.getZero()) {
            this.noOfGuests += noOfGuests;
        }
        else{
            throw new GuestsNumberZeroOrNegativeException("Number of guests cannot be less than one");
        }
    }

    public void calculateBill() {
        System.out.print(calculateWaterConsumed(noOfGuests) + calculateWaterConsumed(noOfOwners()) + " ");
        System.out.print(calculateBillForGuests() + calculateBillForOwners());
    }

    private Integer calculateBillForOwners() {
        int amountForCorporationWater = calculateWater(corporationWaterRatio) * waterRates.getCorporationWaterRate();
        float amountForBoreWellWater = calculateWater(borewellWaterRatio) * waterRates.getBorewellWaterRate();
        return amountForCorporationWater + Math.round(amountForBoreWellWater);
    }

    private Integer calculateWater(Integer ratio){
        return Math.round((ratio * calculateWaterConsumed(noOfOwners()))/totalRatio());
    }

    private Integer calculateBillForGuests(){
        Integer waterConsumedByGuests = calculateWaterConsumed(noOfGuests);
        if(waterConsumedByGuests <= waterRates.getTankWaterSlab1()){
            return waterConsumedByGuests * waterRates.getTankWaterSlab1Rate();
        }
        else if(waterConsumedByGuests <= waterRates.getTankWaterSlab2()){
            return waterRates.calculateTankWaterRateSlab1()
                    + (waterConsumedByGuests - waterRates.getTankWaterSlab1()) * waterRates.getTankWaterSlab2Rate();
        }
        else if(waterConsumedByGuests <= waterRates.getTankWaterSlab3()){
            return waterRates.calculateTankWaterRateSlab1()
                    + waterRates.calculateTankWaterRateSlab2()
                    + (waterConsumedByGuests - waterRates.getTankWaterSlab2()) * waterRates.getTankWaterSlab3Rate();
        }
        else{
            return waterRates.calculateTankWaterRateSlab1()
                    + waterRates.calculateTankWaterRateSlab2()
                    + waterRates.calculateTankWaterRateSlab3()
                    + (waterConsumedByGuests - waterRates.getTankWaterSlab3()) * waterRates.getTankWaterSlab4Rate();
        }
    }

    public boolean isWaterAlreadyAllotted(){
        return waterAlreadyAllotted;
    }

    public void setWaterAlreadyAllotted(){
        waterAlreadyAllotted = true;
    }

    public boolean isBillCalculated(){return billAlreadyCalculated;}

    public void setBillAlreadyCalculated(){
        billAlreadyCalculated = true;
    }

    private Integer calculateWaterConsumed(Integer noOfPeople){
        return noOfPeople * apartmentValues.getTotalWaterPerDay() * apartmentValues.getDaysInMonth();
    }

    private Integer noOfOwners(){
        if(apartmentType.equals(apartmentValues.getTwoBedroom())){
            return apartmentValues.getTwoBedroomOwner();
        }
        else{
            return apartmentValues.getThreeBedroomOwner();
        }
    }

    private Float totalRatio(){
        return corporationWaterRatio.floatValue() + borewellWaterRatio.floatValue();
    }
}
