package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException {
        // write your code here
        Motorcycle yamaha = new Motorcycle("Yamaha", 1);
        //yamaha.addModel();
        yamaha.addModel("YZF-R1", 1705000);
        yamaha.addModel("YZF-R6", 1153000);
        yamaha.addModel("YZF-R3", 499000);
        yamaha.addModel("MT-10 SP", 1523000);
        yamaha.addModel("MT-10", 1338000);
        yamaha.addModel("MT-09 SP", 989000);
        yamaha.addModel("MT-09", 891000);
        yamaha.addModel("MT-07", 757000);
        yamaha.addModel("MT-03", 473000);
        yamaha.addModel("XSR900", 888000);
        yamaha.addModel("WR450F", 818000);
        //проверка на выбрасывание исключений
        //yamaha.modifyName("MT-09", "XSR900");
        //yamaha.modifyName("YZ450F", "WR250F");
        //yamaha.addModel("jdfkj", -34753874);

        yamaha.setName("YZF-R3", "YZF-R4");
        StaticMethods.printModelNames(yamaha);
        yamaha.removeModel("YZF-R6");
        StaticMethods.printVehicle(yamaha);
        System.out.println("Price MT-10: " + yamaha.getPriceByName("MT-10"));
        yamaha.setPriceByName("MT-10", 1_400_000);
        System.out.println("Modified price MT-10: " + yamaha.getPriceByName("MT-10"));
        StaticMethods.printVehicle(yamaha);

        Car lada = new Car("Lada", 7);
        lada.addModel("Granta седан", 504900);
        lada.addModel("Granta лифтбек", 526900);
        lada.addModel("Granta хэтчбек", 550500);
        lada.addModel("Granta универсал", 533900);
        lada.addModel("Granta Cross", 625900);
        lada.addModel("Granta учебная", 547900);
        lada.addModel("Granta Drive Active", 694900);
        System.out.println(lada.getManufacturer());
        StaticMethods.printVehicle(lada);
        System.out.println("Price Granta лифтбек: " + lada.getPriceByName("Granta лифтбек"));
        lada.setName("Granta лифтбек", "Granta liftback");
        StaticMethods.printModelNames(lada);
        lada.setPriceByName("Granta универсал", 534000);
        StaticMethods.printVehicle(lada);
        lada.removeModel("Granta учебная");
        StaticMethods.printVehicle(lada);
        //lada.addModel("Granta седан", 3847389);
        //lada.modifyName("Granta", "dkljfls");
        //lada.addModel("Granta сед",-37472);
    }
}
