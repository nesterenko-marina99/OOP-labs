package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.util.Arrays;

public class Car implements Vehicle {
    //поле типа String, хранящее марку автомобиля
    private String manufacturer;
    private Model[] modelsArray;

    public Car(String Manufacturer, int sizeArrayModels) {
        this.manufacturer = Manufacturer;
        modelsArray = new Model[sizeArrayModels];
    }

    public Car(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    //метод для поиска
    private Model findModelByName(String modelName) {
        int i = 0;
        while (i < modelsArray.length &&
                !modelsArray[i].modelName.equals(modelName))
            i++;
        if (i == modelsArray.length) return null;
        else return modelsArray[i];
    }


    //метод для получения марки автомобиля
    public String getManufacturer() {
        return manufacturer;
    }

    //метод для модификации марки автомобиля
    public void setManufacturer(String carManufacturer) {
        this.manufacturer = carManufacturer;
    }

    //внутренний класс Модель, имеющий поля название модели и её цену,
    // а также конструктор (класс Автомобиль хранит массив Моделей)
    private class Model {
        private String modelName;
        private double modelPrice;

        public Model(String modelName, double modelPrice) {
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }

    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfNames() {
        String[] carModels = new String[modelsArray.length];
        for (int i = 0; i < carModels.length; i++)
            carModels[i] = modelsArray[i].modelName;
        return carModels;
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfPrices() {
        double[] carPrices = new double[modelsArray.length];
        for (int i = 0; i < carPrices.length; i++)
            carPrices[i] = modelsArray[i].modelPrice;
        return carPrices;
    }

    //метод для получения значения цены модели по её названию
    public double getPriceByName(String carModel) throws NoSuchModelNameException {
        Model soughtModel = findModelByName(carModel);
        if (soughtModel == null) throw new NoSuchModelNameException(carModel);
        else return soughtModel.modelPrice;
    }

    //метод для модификации значения названия модели
    public void modifyName(String oldCarModel, String newCarModel)
            throws NoSuchModelNameException, DuplicateModelNameException {
        if (findModelByName(newCarModel) != null) throw new
                DuplicateModelNameException(newCarModel);
        else {
            Model soughtModel = findModelByName(oldCarModel);
            if (soughtModel == null) throw new NoSuchModelNameException(oldCarModel);
            else
                soughtModel.modelName = newCarModel;
        }
    }

    //метод для модификации значения цены модели по её названию
    public void modifyPriceByName(String carModel, double newCarPrice) throws
            NoSuchModelNameException {
        if (newCarPrice < 0) throw new ModelPriceOutOfBoundsException();
        else {
            Model soughtModel = findModelByName(carModel);
            if (soughtModel == null) throw new NoSuchModelNameException(carModel);
            else
                soughtModel.modelPrice = newCarPrice;
        }
    }

    //метод добавления названия модели и её цены (путем создания нового
    // массива Моделей), использовать метод Arrays.copyOf()
    public void addModel(String carModel, double carPrice) throws
            DuplicateModelNameException {

            if (findModelByName(carModel) != null) throw new
                    DuplicateModelNameException(carModel);
            else {
                if (carPrice < 0) throw new ModelPriceOutOfBoundsException();
                else {
                    int i = 0;
                    while (i < modelsArray.length && modelsArray[i] != null)
                        i++;
                    Model newModel = new Model(carModel, carPrice);
                    if (i == modelsArray.length) {
                        modelsArray = Arrays.copyOf(modelsArray, modelsArray.length + 1);
                        modelsArray[modelsArray.length - 1] = newModel;
                    } else modelsArray[i] = newModel;
                }
            }

    }

    //метод удаления модели с заданным именем и её цены, использовать
    // методы System.arraycopy, Arrays.copyOf()
    public void removeModel(String carModel) throws NoSuchModelNameException {
        if (findModelByName(carModel) == null) throw new
                NoSuchModelNameException(carModel);
        else {
            int indexForModelToRemove = -1;
            int i = 0;
            while (i < modelsArray.length && modelsArray[i] != null) {
                if (modelsArray[i].modelName.equals(carModel))
                    indexForModelToRemove = i;
                i++;
            }
            if (indexForModelToRemove != (modelsArray.length - 1))
                System.arraycopy(modelsArray, indexForModelToRemove + 1, modelsArray,
                        indexForModelToRemove, modelsArray.length -
                                (indexForModelToRemove + 1));
            modelsArray = Arrays.copyOf(modelsArray, modelsArray.length - 1);
        }
    }

    //метод для получения размера массива Моделей
    public int getSize() {
        return modelsArray.length;
    }

    public void printAllModels() {
        int i = 0;
        while (i < modelsArray.length && modelsArray[i] != null) {
            System.out.println("Name: " + this.manufacturer + " " +
                    modelsArray[i].modelName + " Price: " +
                    modelsArray[i].modelPrice);
            i++;
        }
    }

}
