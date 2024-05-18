package com.example.geektrust;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        try(Stream<String> lines = Files.lines(new File(args[0]).toPath())){
            List<String> getAllLines = lines.map(String::trim)
                    .filter(string -> !string.matches(" "))
                    .collect(Collectors.toList());
            main.readCommands(getAllLines);
        }
        catch (IOException ioException){
            System.out.println("IOException handled");
        }
    }

    public void readCommands(List<String> getAllLines){
        Apartment apartment = new Apartment();
        boolean checkIfBillCommand = false;
        for (String eachLines : getAllLines) {
            String[] commands = eachLines.trim().split(" ");
            if(commands[0].equals(Commands.BILL.toString())){
                checkIfBillCommand = true;
            }
            executeCommands(commands,apartment);
        }
        if(!checkIfBillCommand){
            throw new NoBillCommandException("Bill command not entered");
        }
    }

    private void executeCommands(String[] commands, Apartment apartment){
        if(commands[0].equals(Commands.ALLOT_WATER.toString())){
            if(!apartment.isWaterAlreadyAllotted()){
                apartment.setWaterAlreadyAllotted();
                apartment.setTypeAndRatio(Integer.parseInt(commands[1]),commands[2]);
            }
            else{
                throw new WaterAlreadyAllotedException("Water is already allotted");
            }
        }
        else if(commands[0].equals(Commands.ADD_GUESTS.toString())){
            if(apartment.isWaterAlreadyAllotted()){
                if(!apartment.isBillCalculated()){
                    apartment.addGuests(Integer.parseInt(commands[1]));
                }
                else{
                    throw new BillAlreadyCalculatedException("Bill is already calculated");
                }
            }
            else {
                throw new WaterNotAllottedException("Water is not allotted");
            }
        }
        else if(commands[0].equals(Commands.BILL.toString())){
            if(apartment.isWaterAlreadyAllotted()){
                if(!apartment.isBillCalculated()){
                    apartment.setBillAlreadyCalculated();
                    apartment.calculateBill();
                }
                else {
                    throw new BillAlreadyCalculatedException("Bill is already calculated");
                }
            }
            else{
                throw new WaterNotAllottedException("Water is not allotted");
            }
        }
        else{
            throw new WrongCommandEnteredException("Wrong command entered");
        }
    }
}
