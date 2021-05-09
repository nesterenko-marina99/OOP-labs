package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;

public class ClassWithStaticMethods {
    public static double averageOFModelPrices(Vehicle vehicle) {
        double[] prices = vehicle.getArrayOfPrices();
        double number = vehicle.getSize();
        double sum = 0;
        for (double price : prices) sum += price;
        return sum / number;
    }

    public static void printModelNames(Vehicle vehicle) {
        String[] modelNames = vehicle.getArrayOfNames();
        for (String name : modelNames) System.out.println(name);
    }

    public static void printModelPrices(Vehicle vehicle) {
        double[] prices = vehicle.getArrayOfPrices();
        for (double price : prices) System.out.println(price);
    }

    //метод записи информации о транспортном средстве в байтовый поток
    public static void writingVehicleInfToByteStream(Vehicle vehicle, OutputStream out) {
        String[] modelNames = vehicle.getArrayOfNames();
        double[] prices = vehicle.getArrayOfPrices();
        int size = vehicle.getSize();
        //конструкция try-with-resources
        try (DataOutputStream dos = new DataOutputStream(out)) {
            // записываем значения
            dos.writeUTF(vehicle.getManufacturer());
            dos.writeInt(size);
            int i = 0;
            while (i < size) {
                dos.writeUTF(modelNames[i]);
                dos.writeDouble(prices[i]);
                i++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //метод чтения информации о транспортном средстве из байтового потока
    public static Car readingCarInfFromByteStream (InputStream in) {
        // обратное считывание из файла
        try(DataInputStream dos = new DataInputStream(in))
        {
            // записываем значения
            Car car = new Car(dos.readUTF(), dos.readInt());
            int i = 0;
            while (i<car.getSize())
            {
                car.addModel(dos.readUTF(), dos.readDouble());
                i++;
            }
        }
        catch(IOException | DuplicateModelNameException ex){
                System.out.println(ex.getMessage());
        }
    }
}
