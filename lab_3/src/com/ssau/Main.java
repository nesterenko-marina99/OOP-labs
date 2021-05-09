package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.NoSuchModelNameException;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException {
        // write your code here
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
        //проверка на выбрасывание исключений
        //yamaha.modifyName("MT-09", "XSR900");
        //yamaha.modifyName("YZ450F", "WR250F");

        yamaha.modifyName("YZF-R3", "YZF-R4");
        yamaha.removeModel("YZF-R6");
        System.out.println(yamaha.getPriceByName("MT-10"));
        ClassWithStaticMethods.printModelNames(yamaha);
    }
}
