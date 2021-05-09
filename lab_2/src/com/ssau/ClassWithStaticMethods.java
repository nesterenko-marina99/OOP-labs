package com.ssau;

public class ClassWithStaticMethods {
    public static double averageOFModelPrices (Vehicle vehicle)
    {
        double[] prices = vehicle.getArrayOfPrices();
        double number = vehicle.getSize();
        double sum = 0;
        for (double price : prices) sum += price;
        return sum/number;
    }

    public static void printModelNames(Vehicle vehicle)
    {
        String[] modelNames = vehicle.getArrayOfNames();
        for (String name: modelNames) System.out.println(name);
    }

    public static void printModelPrices(Vehicle vehicle)
    {
        double[] prices= vehicle.getArrayOfPrices();
        for (double price: prices) System.out.println(price);
    }
}
