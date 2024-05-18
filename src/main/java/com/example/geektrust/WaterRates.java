package com.example.geektrust;

public class WaterRates {

    final Integer corporationWaterRate = 1;
    final Float borewellWaterRate = 1.5f;
    final Integer tankWaterSlab1 = 500;
    final Integer tankWaterSlab2 = 1500;
    final Integer tankWaterSlab3 = 3000;
    final Integer tankWaterSlab1Rate = 2;
    final Integer tankWaterSlab2Rate = 3;
    final Integer tankWaterSlab3Rate = 5;
    final Integer tankWaterSlab4Rate = 8;

    public Integer getCorporationWaterRate() {
        return corporationWaterRate;
    }
    public Float getBorewellWaterRate() {
        return borewellWaterRate;
    }
    public Integer getTankWaterSlab1() {
        return tankWaterSlab1;
    }
    public Integer getTankWaterSlab2() {
        return tankWaterSlab2;
    }
    public Integer getTankWaterSlab3() {
        return tankWaterSlab3;
    }
    public Integer getTankWaterSlab1Rate() {
        return tankWaterSlab1Rate;
    }
    public Integer getTankWaterSlab3Rate() {
        return tankWaterSlab3Rate;
    }
    public Integer getTankWaterSlab2Rate() {
        return tankWaterSlab2Rate;
    }
    public Integer getTankWaterSlab4Rate() {
        return tankWaterSlab4Rate;
    }
    public Integer calculateTankWaterRateSlab1(){
        return tankWaterSlab1 * tankWaterSlab1Rate;
    }
    public Integer calculateTankWaterRateSlab2(){
        return (tankWaterSlab2 - tankWaterSlab1) * tankWaterSlab2Rate;
    }

    public Integer calculateTankWaterRateSlab3() {
        return (tankWaterSlab3 - tankWaterSlab2) * tankWaterSlab3Rate;
    }
}
