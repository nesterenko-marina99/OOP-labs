package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;

import java.io.*;
import java.util.ArrayList;

// класс со статическими методами
public class IOStaticMethods {
    // метод, позволяющий найти среднюю стоимость моделей
    public static double averageOFModelPrices(Vehicle vehicle) {
        double[] prices = vehicle.getArrayOfPrices(); // создается массив из цен моделей
        double number = vehicle.getSize(); // узнается его длинна
        double sum = 0; // переменная под сумму цен моделей
        for (double price : prices) sum += price; // суммируем цены моделей
        return sum / number; // возвращаем сумму / количество (среднее)
    }

    // метод, который выводит на экран названия моделей
    public static void printModelNames(Vehicle vehicle) {
        String[] modelNames = vehicle.getArrayOfNames(); // создаем массив из имен моделей
        for (String name : modelNames) System.out.println(name); // выводим каждый элемент этого массива в System.out
    }

    // метод, который выводит на экран цены моделей
    public static void printModelPrices(Vehicle vehicle) {
        double[] prices = vehicle.getArrayOfPrices(); // создаем массив цен моделей
        for (double price : prices) System.out.println(price); // выводим каждый элемент в System.out
    }

    // метод, позволяющий вывести технику на экран
    public static void printVehicle(Vehicle vehicle) {
        String[] modelNames = vehicle.getArrayOfNames(); // создается массив цен моделей
        double[] prices = vehicle.getArrayOfPrices(); // создается массив имен моделей
        System.out.println(vehicle.getClass().toString());
        System.out.println(vehicle.getManufacturer()); // выводим на экран название производителя
        for (int i = 0; i < vehicle.getSize(); i++) { //продвигаемся по массивам
            // (они оба длинной совпадают с количеством моделей)
            System.out.println(modelNames[i] + ' ' + prices[i]); // выводим информацию о модели на экран
        }
    }

    //метод записи информации о транспортном средстве в байтовый поток
    public static void outputVehicle(Vehicle vehicle, OutputStream out) throws
            IOException {
        String[] modelNames = vehicle.getArrayOfNames(); // создается массив из названий моделей
        double[] prices = vehicle.getArrayOfPrices(); // создается массив из цен моделей
        int size = vehicle.getSize(); // переменная в которой хранится длина массивов
        DataOutputStream dos = new DataOutputStream(out);
        // записываем значения
        dos.writeUTF(vehicle.getClass().toString());
        dos.writeUTF(vehicle.getManufacturer());
        dos.writeInt(size);// записываем количество моделей
        //dos.write("\n".getBytes(StandardCharsets.UTF_8)); // записываем перевод строки
        int i = 0; // переменная счетчик
        while (i < size) { // проходим по массиву
            dos.writeUTF(modelNames[i] + " "); // выводим название модели
            dos.writeDouble(prices[i]); // выводим цену модели
            //dos.write("\n".getBytes(StandardCharsets.UTF_8)); // перевод строки
            i++; // прохождение по массиву
        }
        dos.close();
    }

    //метод чтения информации о транспортном средстве из байтового потока
    public static Vehicle inputVehicle(InputStream in) throws IOException,
            DuplicateModelNameException {
        DataInputStream dis = new DataInputStream(in);
        String className = dis.readUTF();
        switch (className) {
            case "class com.ssau.Motorcycle":
                Motorcycle motorcycle = new Motorcycle(dis.readUTF());
                int size = dis.readInt(); // считываем из потока количество моделей
                int i = 0; // переменная счетчик
                while (i < size) { // пока не достигнем нужного нам количества моделей
                    // добавляем новую модель. Данные о ней (название, цена) считываются из байтового потока
                    motorcycle.addModel(dis.readUTF(), dis.readDouble());
                    i++; // проходим далее
                }
                return motorcycle; // возвращаем созданный мотоцикл со всеми данными
            case "class com.ssau.Car":
                Car car = new Car(dis.readUTF(), dis.readInt());
                i = 0; // переменная счетчик для массива
                while (i < car.getSize()) { // пока не выполним следующий код столько раз, сколько моделей
                    car.addModel(dis.readUTF(), dis.readDouble()); // добавляем новую модель
                    // (название и цену считываем из потока ввода)
                    i++; // продвигаемся далее
                }
                return car; // возвращаем car со всеми данными
            default:
                return null;
        }
    }

    //метод записи информации о транспортном средстве в символьный поток
    public static void writeVehicle(Vehicle vehicle, Writer out) {
        PrintWriter pwout = new PrintWriter(out); // создаем PrintWriter
        int size = vehicle.getSize(); // получаем информацию о количестве моделей
        String[] modelNames = vehicle.getArrayOfNames(); // создаем массив названий моделей
        double[] prices = vehicle.getArrayOfPrices(); // создаем массив цен моделей
        pwout.println(vehicle.getClass().toString()); //печатаем название класса
        pwout.println(vehicle.getManufacturer() + " , " + size); // печатаем информацию о производителе и количестве моделей
        for (int i = 0; i < size; ++i) // проходим по массиву
            pwout.println(modelNames[i] + " , " + prices[i]); // выводим информацию о модели (название и цена)
        if (pwout.checkError()) // проверяем на наличие ошибок при выводе
            System.err.println("Error detected"); // если нашли, то выводим в поток для ошибок, что была найдена ошибка
        pwout.close(); // закрываем поток
    }

    //метод чтения информации о транспортном средстве из символьного потока
    public static Vehicle readVehicle(Reader in) throws IOException,
            DuplicateModelNameException {
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine(); // считываем строку
        String[] readable = reader.readLine().split(" , ");// считываем строку и разделяем ее по запятой
        int size = Integer.parseInt(readable[1]); // в конце строчки было записано количество моделей
        switch (line) {
            case "class com.ssau.Motorcycle":
                Motorcycle motorcycle = new Motorcycle(readable[0]);// создаем Мотоцикл по данным в начале (названию производителя)
                for (int i = 0; i < size; i++) { //выполняем *количество моделей* раз
                    readable = reader.readLine().split(" , ");// считываем строку и разделяем ее по запятой
                    motorcycle.addModel(readable[0], Double.parseDouble(readable[1]));// добавляем модель на основе считанных данных (название , цена)
                }
                reader.close(); //закрываем поток
                return motorcycle; //возвращаем мотоцикл
            case "class com.ssau.Car":
                Car car = new Car(readable[0], size); // создаем Машину по данным в начале (названию производителя) и количеству моделей
                for (int i = 0; i < size; i++) { // выполняем *количество моделей* раз
                    readable = reader.readLine().split(" , "); // считываем строку и разделяем ее по запятой
                    car.addModel(readable[0], Double.parseDouble(readable[1])); // добавляем модель на основе считанных данных (название , цена)
                }
                reader.close(); // закрываем поток
                return car; // возвращаем Машину
            default:
                return null;
        }
    }
}
