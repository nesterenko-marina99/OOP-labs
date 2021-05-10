package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException, IOException, ClassNotFoundException {


       // write your code here
        Motorcycle yamaha = new Motorcycle("Yamaha");
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

        ClassWithStaticMethods.writingVehicleInfToSymbolStream(yamaha, new FileWriter
                ("/home/nesterenkom/Yandex.Disk/university/OOP/Nesterenko_6395_OOP/lab_3/input.txt"));
        ClassWithStaticMethods.writingVehicleInfToSymbolStream(
                ClassWithStaticMethods.readingMotorcycleInfFromSymbolStream(new FileReader
                ("/home/nesterenkom/Yandex.Disk/university/OOP/Nesterenko_6395_OOP/lab_3/input.txt")),
                new FileWriter
                        ("/home/nesterenkom/Yandex.Disk/university/OOP/Nesterenko_6395_OOP/lab_3/output.txt"));
        FileOutputStream fileOut = new FileOutputStream
                ("/home/nesterenkom/Yandex.Disk/university/OOP/Nesterenko_6395_OOP/lab_3/output");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(yamaha);
        FileInputStream fileIn = new FileInputStream
                ("/home/nesterenkom/Yandex.Disk/university/OOP/Nesterenko_6395_OOP/lab_3/output");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Motorcycle yamaha_2= (Motorcycle) in.readObject();
        ClassWithStaticMethods.printModelNames(yamaha_2);

        Car lada = new Car("Lada", 7);
        lada.addModel("Granta седан", 504900);
        lada.addModel("Granta лифтбек", 526900);
        lada.addModel("Granta хэтчбек", 550500);
        lada.addModel("Granta универсал", 533900);
        lada.addModel("Granta Cross", 625900);
        lada.addModel("Granta учебная", 547900);
        lada.addModel("Granta Drive Active", 694900);
    }
}
