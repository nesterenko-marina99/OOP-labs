package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException, IOException, ClassNotFoundException {

        //создаем модель для проверки
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

        //проверяем toString
        System.out.println(yamaha);

        // task 2
        //создаем такой же объект для тестирования equals
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

        //тестируем equals на таком же объекте
        System.out.println(yamaha.equals(yamaha2)); // result task 2

        //тестируем hashCode
        System.out.println(yamaha.hashCode()); // result task 3

        //тестируем clone
        //task 4
        try {
            //создаем клон объекта yamaha и сохраняем в yamaha3
            Motorcycle yamaha3 = (Motorcycle) yamaha.clone();
            //выводим yamaha
            System.out.println(yamaha3.equals(yamaha));
            //изменяем производителя и имя модели для проверки на глубокое клонирование
            yamaha3.setManufacturer("Yamaha3");
            yamaha3.modifyName("MT-09 SP", "Changes to test");
            //печатаем клон и оригинал для сравнения
            System.out.println(yamaha3); // result task 4
            System.out.println(yamaha); // result task 1
            //проверяем по equals
            System.out.println(yamaha3.equals(yamaha));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //создаем модель для проверки
        //task 1
        Car lada = new Car("Lada", 7);
        lada.addModel("Granta седан", 504900);
        lada.addModel("Granta лифтбек", 526900);
        lada.addModel("Granta хэтчбек", 550500);
        lada.addModel("Granta универсал", 533900);
        lada.addModel("Granta Cross", 625900);
        lada.addModel("Granta учебная", 547900);
        lada.addModel("Granta Drive Active", 694900);

        //проверяем toString
        System.out.println(lada);

        //task 2
        //создаем такой же объект для тестирования equals
        Car lada2 = new Car("Lada", 7);
        lada2.addModel("Granta седан", 504900);
        lada2.addModel("Granta лифтбек", 526900);
        lada2.addModel("Granta хэтчбек", 550500);
        lada2.addModel("Granta универсал", 533900);
        lada2.addModel("Granta Cross", 625900);
        lada2.addModel("Granta учебная", 547900);
        lada2.addModel("Granta Drive Active", 694900);

        //тестируем equals на таком же объекте
        System.out.println(lada.equals(lada2)); // result task 2

        //тестируем hashCode
        System.out.println(lada.hashCode()); // result task 3

        //тестируем clone
        //task 4
        try {
            //создаем клон объекта lada и сохраняем в lada3
            Car lada3 = (Car) lada.clone();
            //изменяем производителя и имя модели для проверки на глубокое клонирование
            lada3.setManufacturer("Lada3");
            lada3.modifyName("Granta хэтчбек", "Granta hatchback");
            //печатаем клон и оригинал для сравнения
            System.out.println(lada3);
            System.out.println(lada);
        }
        catch (CloneNotSupportedException ex)
        {}
    }
}
