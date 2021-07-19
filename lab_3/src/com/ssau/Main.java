package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException, ClassNotFoundException {


        Motorcycle yamaha = new Motorcycle("Yamaha"); // создаем Мотоцикл
        // добавляем в него несколько моделей
        yamaha.addModel("YZF-R1", 1705000.0);
        yamaha.addModel("YZF-R6", 1153000.0);
        yamaha.addModel("YZF-R3", 499000);
        yamaha.addModel("MT-10 SP", 1523000);
        yamaha.addModel("MT-10", 1338000);
        yamaha.addModel("MT-09 SP", 989000);
        yamaha.addModel("MT-09", 891000);
        yamaha.addModel("MT-07", 757000);
        yamaha.addModel("MT-03", 473000);
        yamaha.addModel("XSR900", 888000);
        yamaha.addModel("WR450F", 818000);

        try {
            // ввод и вывод в байтовые потоки
            //file
            IOStaticMethods.outputVehicle(yamaha, new FileOutputStream("bytemoto"));
            System.out.println("\nчтение информации о Мотоцикле из FileInputStream");
            IOStaticMethods.printVehicle (IOStaticMethods.inputVehicle (new FileInputStream("bytemoto")));

            //символьные потоки
            IOStaticMethods.writeVehicle(yamaha, new FileWriter("inputMotorcycle.txt")); // записываем в файл
            System.out.println("\nВывод мотоцикла из файла, записанного символьным потоком");
            IOStaticMethods.printVehicle(IOStaticMethods.readVehicle (new FileReader("inputMotorcycle.txt")));

            //сериализация
            FileOutputStream fileOutMotorcycle = new FileOutputStream("outputMotorcycle");
            ObjectOutputStream outMotorcycle = new ObjectOutputStream(fileOutMotorcycle);
            outMotorcycle.writeObject(yamaha);
            //десериализация
            FileInputStream fileInMotorcycle = new FileInputStream("outputMotorcycle");
            ObjectInputStream inMotorcycle = new ObjectInputStream(fileInMotorcycle);
            Motorcycle yamaha_2 = (Motorcycle) inMotorcycle.readObject();
            System.out.println("\nДесериализация");
            IOStaticMethods.printVehicle(yamaha_2);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Car lada = new Car("Lada", 7); // создаем Машину
        // добавляем в нее несколько моделей
        lada.addModel("Granta седан", 504900);
        lada.addModel("Granta лифтбек", 526900);
        lada.addModel("Granta хэтчбек", 550500);
        lada.addModel("Granta универсал", 533900);
        lada.addModel("Granta Cross", 625900);
        lada.addModel("Granta учебная", 547900);
        lada.addModel("Granta Drive Active", 694900);

        try {
            // ввод и вывод в байтовые потоки
            //file
            IOStaticMethods.outputVehicle(lada, new FileOutputStream("bytecar"));
            System.out.println("\nВывод автомобиля из файла, записанного байтовым потоком");
            IOStaticMethods.printVehicle(IOStaticMethods.inputVehicle (new FileInputStream("bytecar")));

            //символьные потоки
            IOStaticMethods.writeVehicle(lada, new FileWriter("inputCar.txt")); // записываем в файл
            //выводим ту Машину, что считаем в первом
            System.out.println("\nВывод автомобиля из файла, записанного символьным потоком");
            IOStaticMethods.printVehicle(IOStaticMethods.readVehicle(new FileReader("inputCar.txt")));

            //сериализация
            FileOutputStream fileOutCar = new FileOutputStream("outputCar");
            ObjectOutputStream outCar = new ObjectOutputStream(fileOutCar);
            outCar.writeObject(lada);
            //десериализация
            FileInputStream fileInCar = new FileInputStream("outputCar");
            ObjectInputStream inCar = new ObjectInputStream(fileInCar);
            Car lada_2 = (Car) inCar.readObject();
            System.out.println("\nДесериализация");
            IOStaticMethods.printVehicle(lada_2);
            // ввод и вывод в байтовые потоки
            //system
            System.out.println("\nзапись информации о мотоцикле в system.out");
            IOStaticMethods.outputVehicle(yamaha, System.out);
            /*Motorcycle yamahaTestByteSystem = (Motorcycle) IOStaticMethods.inputVehicle(System.in);
        IOStaticMethods.printVehicle(yamahaTestByteSystem);*/
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
