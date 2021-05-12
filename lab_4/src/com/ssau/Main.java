package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException, IOException, ClassNotFoundException {


       // task 1
        Motorcycle yamaha = new Motorcycle("Yamaha");
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

        // task 2
        Motorcycle yamaha2 = new Motorcycle("Yamaha");
        //yamaha.addModel();
        yamaha2.addModel("YZF-R1", 1705000);
        yamaha2.addModel("YZF-R6", 1153000);
        yamaha2.addModel("YZF-R3", 499000);
        yamaha2.addModel("MT-10 SP", 1523000);
        yamaha2.addModel("MT-10", 1338000);
        yamaha2.addModel("MT-09 SP", 989000);
        yamaha2.addModel("MT-09", 891000);
        yamaha2.addModel("MT-07", 757000);
        yamaha2.addModel("MT-03", 473000);
        yamaha2.addModel("XSR900", 888000);
        yamaha2.addModel("WR450F", 818000);

        System.out.println(yamaha);

        //task 4
        try {
            Motorcycle yamaha3 = (Motorcycle) yamaha.clone();
            yamaha3.setManufacturer("Yamaha3");
            yamaha3.modifyName("MT-09 SP", "Changes to test");
            System.out.println(yamaha3); // result task 4
            System.out.println(yamaha); // result task 1
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(yamaha.hashCode()); // result task 3
        System.out.println(yamaha.equals(yamaha2)); // result task 2

        Car lada = new Car("Lada", 7);
        lada.addModel("Granta седан", 504900);
        lada.addModel("Granta лифтбек", 526900);
        lada.addModel("Granta хэтчбек", 550500);
        lada.addModel("Granta универсал", 533900);
        lada.addModel("Granta Cross", 625900);
        lada.addModel("Granta учебная", 547900);
        lada.addModel("Granta Drive Active", 694900);

        System.out.println(lada);
    }
}
