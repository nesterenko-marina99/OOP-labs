package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
            ClassWithStaticMethods.writeStringToByteStream(dos, vehicle.getManufacturer());
            dos.writeInt(size);
            int i = 0;
            while (i < size) {
                ClassWithStaticMethods.writeStringToByteStream(dos, modelNames[i]);
                dos.writeDouble(prices[i]);
                i++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void writeStringToByteStream(DataOutputStream dos, String message)
            throws IOException {
        byte[] byteMessage = message.getBytes(StandardCharsets.UTF_8);
        dos.writeInt(byteMessage.length);
        dos.write(byteMessage, 0, byteMessage.length);
    }

    private static String readStringFromByteStream(DataInputStream dis) throws IOException {
        int messageLength = dis.readInt();
        byte[] message = new byte[messageLength];
        if (dis.read(message, 0, messageLength) == -1)
            throw new IOException();
        return new String(message);
    }

    //метод чтения информации о транспортном средстве из байтового потока
    public static Car readingCarInfFromByteStream(InputStream in) {
        // обратное считывание из файлa
        try (DataInputStream dis = new DataInputStream(in)) {
            // записываем значения
            Car car = new Car(ClassWithStaticMethods.readStringFromByteStream(dis),
                    dis.readInt());
            int i = 0;
            while (i < car.getSize()) {
                car.addModel(ClassWithStaticMethods.readStringFromByteStream(dis),
                        dis.readDouble());
                i++;
            }
            return car;
        } catch (IOException | DuplicateModelNameException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Motorcycle readingMotorcycleInfFromByteStream(InputStream in) {
        try (DataInputStream dis = new DataInputStream(in)) {
            Motorcycle motorcycle = new Motorcycle(ClassWithStaticMethods.readStringFromByteStream(dis));
            int size = dis.readInt();
            int i = 0;
            while (i < size) {
                motorcycle.addModel(ClassWithStaticMethods.readStringFromByteStream(dis), dis.readDouble());
                i++;
            }
            return motorcycle;
        } catch (IOException | DuplicateModelNameException | NoSuchModelNameException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static void writingVehicleInfToSymbolStream(Vehicle vehicle, Writer out) {
        PrintWriter pwout = new PrintWriter(out);
        int size = vehicle.getSize();
        String[] modelNames = vehicle.getArrayOfNames();
        double[] prices = vehicle.getArrayOfPrices();
        pwout.println(vehicle.getManufacturer() + " , " + size);
        for (int i = 0; i < size; ++i)
            pwout.println(modelNames[i] + " , " + prices[i]);
        if (pwout.checkError())
            System.err.println("Error detected");
        pwout.close();
    }

    public static Car readingCarInfFromSymbolStream(Reader in) {
        try {
            BufferedReader reader = new BufferedReader(in);
            String[] line = reader.readLine().split(" , ");
            int size = Integer.parseInt(line[1]);
            Car car = new Car(line[0], size);
            for (int i = 0; i < size; i++) {
                line = reader.readLine().split(" , ");
                car.addModel(line[0], Double.parseDouble(line[1]));
            }
            reader.close();
            return car;
        } catch (IOException | DuplicateModelNameException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

    public static Motorcycle readingMotorcycleInfFromSymbolStream(Reader in) {
        try {
            BufferedReader reader = new BufferedReader(in);
            String[] line = reader.readLine().split(" , ");
            int size = Integer.parseInt(line[1]);
            Motorcycle motorcycle = new Motorcycle(line[0]);
            for (int i = 0; i < size; i++) {
                line = reader.readLine().split(" , ");
                motorcycle.addModel(line[0], Double.parseDouble(line[1]));
            }
            reader.close();
            return motorcycle;
        } catch (IOException | DuplicateModelNameException | NoSuchModelNameException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
