package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;

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
        for (int i = 0; i < vehicle.getSize(); i++) { //продвигаемся по массивам (они оба длинной совпадают с количеством моделей)
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
        byte [] writable = vehicle.getClass().toString().getBytes();
        dos.writeInt(writable.length);
        dos.write(writable);//записываем класс
        writable = vehicle.getManufacturer().getBytes();
        dos.writeInt(writable.length); // записываем название производителя
        dos.write(writable);
        writable = Integer.toString(size).getBytes();
        dos.writeInt(writable.length); // записываем количество моделей
        dos.write(writable);
        //dos.write("\n".getBytes(StandardCharsets.UTF_8)); // записываем перевод строки
        int i = 0; // переменная счетчик
        while (i < size) { // проходим по массиву
            writable = modelNames[i].getBytes();
            dos.writeInt(writable.length);
            dos.write(writable); // выводим название модели
            writable = Double.toString(prices[i]).getBytes();
            dos.writeInt(writable.length);
            dos.write(writable); // выводим цену модели
            //dos.write("\n".getBytes(StandardCharsets.UTF_8)); // перевод строки
            i++; // прохождение по массиву
        }
        dos.close();
    }
    /*public static void outputVehicle(Vehicle vehicle, OutputStream out) {
        String[] modelNames = vehicle.getArrayOfNames(); // создается массив из названий моделей
        double[] prices = vehicle.getArrayOfPrices(); // создается массив из цен моделей
        int size = vehicle.getSize(); // переменная в которой хранится длина массивов
        //конструкция try-with-resources
        try (DataOutputStream dos = new DataOutputStream(out)) {
            // записываем значения
            IOStaticMethods.outputString(dos, vehicle.getClass().toString()); //записываем класс
            IOStaticMethods.outputString(dos, vehicle.getManufacturer()); // записываем название производителя
            dos.write((new Integer(size)).toString().getBytes(StandardCharsets.UTF_8)); // записываем количество моделей
            dos.write("\n".getBytes(StandardCharsets.UTF_8)); // записываем перевод строки
            int i = 0; // переменная счетчик
            while (i < size) { // проходим по массиву
                IOStaticMethods.outputString(dos, modelNames[i]); // выводим название модели
                dos.write(Double.toString(prices[i]).getBytes(StandardCharsets.UTF_8)); // выводим цену модели
                dos.write("\n".getBytes(StandardCharsets.UTF_8)); // перевод строки
                i++; // прохождение по массиву
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // если try-catch поймал ошибку, выводим ее сообщение на экран
        }
    }*/

        // вспомогательный метод для вывода строки в байтовый поток
    /*private static void outputString(DataOutputStream dos, String message)
            throws IOException {
        byte[] byteMessage = message.getBytes(StandardCharsets.UTF_8); // переводим сообщение в массив байтов
        dos.write(Integer.toString(byteMessage.length).getBytes(StandardCharsets.UTF_8)); // записываем длинну сообщения
        dos.write("\n".getBytes(StandardCharsets.UTF_8)); // перевод строки
        dos.write(byteMessage, 0, byteMessage.length); // записываем само сообщение
        dos.write("\n".getBytes(StandardCharsets.UTF_8)); // перевод строки
    }

    // вспомогательный метод для чтения числа из байтового потока
    private static int inputInt(DataInputStream dis) throws IOException {
        int result = 0; // переменная для результата
        String token = ""; // вспомогательная переменная для ввода данных
        while (true) { // бесконечный цикл
            byte[] length = new byte[1]; // создаем массив длинной 1
            if (dis.read(length) == -1) // если не удалось считать 1 символ
                throw new IOException(); // выбрасываем исключение
            token = new String(length); // если удалось, то записываем его в token
            if (token.equals("\n")) // Если token это перевод строки (т.е. ввод числа окончен)
                return result; // возвращаем число (выход из бесконечного цикла)
            result = result * 10 + Integer.parseInt(token); // иначе мы дописываем цифру в конец числа
        }
    }

    // вспомогательный метод для чтения числа с точкой из байтового потока
    private static double inputDouble(DataInputStream dis) throws IOException {
        StringBuilder line = new StringBuilder(); // переменная для записи результата
        String token = ""; // вспомогательная переменная для ввода данных
        while (true) { // бесконечный цикл
            byte[] length = new byte[1]; // создаем массив байт длинной 1
            if (dis.read(length) == -1) // если не удалось считать символ
                throw new IOException(); // выбрасываем исключение
            token = new String(length); // если удалось, записываем его в token
            if (token.equals("\n")) // если token это перевод строки (т.е. окончание ввода)
                return Double.parseDouble(line.toString()); // возвращаем число
            // (т.к. line по факту результат конкатенации строк, его нужно перевести в формат числа с плавающей точкой)
            line.append(token); // если не перевод строки, дописываем в конец символ
        }
    }

    // вспомогательный метод для чтения строк из байтового потока данных
    private static String inputString(DataInputStream dis) throws IOException {
        int messageLength = inputInt(dis); // считываем число (длинну строки)
        byte[] message = new byte[messageLength]; // создаем массив байтов этой длинны
        if (dis.read(message) == -1) // если не удалось считать сообщение
            throw new IOException(); // выбрасываем исключение
        dis.skipBytes(1); // пропускаем 1 байт (перевод строки)
        return new String(message); // возвращаем строковый эквивалент считанного сообщения
    }

    //метод чтения информации о автомобиле из байтового потока
    public static Car inputCar(InputStream in) throws IOException,
            DuplicateModelNameException {
        // обратное считывание из файлa
        DataInputStream dis = new DataInputStream(in);
        // записываем значения
        Car car = new Car(IOStaticMethods.inputString(dis),
                inputInt(dis));
        int i = 0; // переменная счетчик для массива
        while (i < car.getSize()) { // пока не выполним следующий код столько раз, сколько моделей
            car.addModel(IOStaticMethods.inputString(dis), // добавляем новую модель (название и цену считываем из потока ввода)
                    inputDouble(dis));
            i++; // продвигаемся далее
        }
        return car; // возвращаем car со всеми данными

    }

    //метод чтения информации о Мотоцикле из байтового потока
    public static Motorcycle inputMotorcycle(InputStream in) throws IOException,
            DuplicateModelNameException {
        DataInputStream dis = new DataInputStream(in);
        // создаем сам Мотоцикл со считанным из потока именем
        Motorcycle motorcycle = new Motorcycle(IOStaticMethods.inputString(dis));
        int size = inputInt(dis); // считываем из потока количество моделей
        int i = 0; // переменная счетчик
        while (i < size) { // пока не достигнем нужного нам количества моделей
            // добавляем новую модель. Данные о ней (название, цена) считываются из байтового потока
            motorcycle.addModel(IOStaticMethods.inputString(dis), inputDouble(dis));
            i++; // проходим далее
        }
        return motorcycle; // возвращаем созданный мотоцикл со всеми данными
    }*/

        public static Vehicle inputVehicle (InputStream in) throws IOException,
                DuplicateModelNameException
        {
            DataInputStream dis = new DataInputStream(in);
            switch (dis.readUTF()) {
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
                    Car car = new Car(dis.readUTF(),
                            dis.readInt());
                    i = 0; // переменная счетчик для массива
                    while (i < car.getSize()) { // пока не выполним следующий код столько раз, сколько моделей
                        car.addModel(dis.readUTF(), // добавляем новую модель (название и цену считываем из потока ввода)
                                dis.readDouble());
                        i++; // продвигаемся далее
                    }
                    return car; // возвращаем car со всеми данными
                default:
                    return null;
            }
        }

        //метод записи информации о транспортном средстве в символьный поток
        public static void writeVehicle (Vehicle vehicle, Writer out){
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
        public static Vehicle readVehicle (Reader in) throws IOException,
                DuplicateModelNameException
        {
            BufferedReader reader = new BufferedReader(in);
            String line = reader.readLine(); // считываем строку
            String[] readable = reader.readLine().split(" , ");// считываем строку и разделяем ее по запятой
            int size = Integer.parseInt(readable[1]); // в конце строчки было записано количество моделей
            switch (line) {
                case "class com.ssau.Motorcycle":
                    Motorcycle motorcycle = new Motorcycle(readable[0]);// создаем Мотоцикл по данным в начале (названию производителя)
                    for (int i = 0; i < size; i++) {// выполняем *количество моделей* раз
                        readable = reader.readLine().split(" , ");// считываем строку и разделяем ее по запятой
                        motorcycle.addModel(readable[0], Double.parseDouble(readable[1]));// добавляем модель на основе считанных данных (название , цена)
                    }
                    reader.close();// закрываем поток
                    return motorcycle;// возвращаем мотоцикл
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
