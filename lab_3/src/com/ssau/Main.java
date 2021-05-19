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

        IOStaticMethods.writeVehicle(yamaha, new FileWriter("inputMotorcycle.txt"));
        IOStaticMethods.writeVehicle(IOStaticMethods.readMotorcycle
                (new FileReader("inputMotorcycle.txt")),
                new FileWriter("outputMotorcycle.txt"));

        IOStaticMethods.outputVehicle(yamaha, System.out);
        IOStaticMethods.inputMotorcycle(System.in);
        //сериализация
        FileOutputStream fileOutMotorcycle = new FileOutputStream("outputMotorcycle");
        ObjectOutputStream outMotorcycle = new ObjectOutputStream(fileOutMotorcycle);
        outMotorcycle.writeObject(yamaha);
        //десериализация
        FileInputStream fileInMotorcycle = new FileInputStream("outputMotorcycle");
        ObjectInputStream inMotorcycle = new ObjectInputStream(fileInMotorcycle);
        Motorcycle yamaha_2 = (Motorcycle) inMotorcycle.readObject();
        IOStaticMethods.printVehicle(yamaha_2);

        Car lada = new Car("Lada", 7);
        lada.addModel("Granta седан", 504900);
        lada.addModel("Granta лифтбек", 526900);
        lada.addModel("Granta хэтчбек", 550500);
        lada.addModel("Granta универсал", 533900);
        lada.addModel("Granta Cross", 625900);
        lada.addModel("Granta учебная", 547900);
        lada.addModel("Granta Drive Active", 694900);

        IOStaticMethods.writeVehicle(lada, new FileWriter("inputCar.txt"));
        IOStaticMethods.writeVehicle(IOStaticMethods.readCar
                (new FileReader("inputCar.txt")), new FileWriter("outputCar.txt"));
        //IOStaticMethods.writeVehicle(lada, new PrintWriter(System.out));
        //IOStaticMethods.readCar( new InputStreamReader(System.in));

        IOStaticMethods.outputVehicle(lada, System.out);
        IOStaticMethods.inputCar(System.in);
        //сериализация
        FileOutputStream fileOutCar = new FileOutputStream("outputCar");
        ObjectOutputStream outCar = new ObjectOutputStream(fileOutCar);
        outCar.writeObject(lada);
        //десериализация
        FileInputStream fileInCar = new FileInputStream("outputCar");
        ObjectInputStream inCar = new ObjectInputStream(fileInCar);
        Car lada_2 = (Car) inCar.readObject();
        IOStaticMethods.printVehicle(lada_2);
    }
}
