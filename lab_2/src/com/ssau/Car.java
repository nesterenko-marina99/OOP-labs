package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.util.Arrays;

public class Car implements Vehicle {
    //поле типа String, хранящее марку автомобиля
    private String manufacturer;
    //поле - массив ссылочных объектов типа Model
    private Model[] modelsArray;

    //конструктор класса, принимающий на вход производителя и кол-во моделей
    public Car(String Manufacturer, int sizeArrayModels) {
        //присваиваем полю manufacturer текущего класса данное значение Manufacturer
        this.manufacturer = Manufacturer;
        //инициализируем массив данным кол-вом эл-тов
        modelsArray = new Model[sizeArrayModels];
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
        //поле типа String (строка) - имя модели
        private String modelName;
        //поле типа double (число с плавающей точкой) - цена модели
        private double modelPrice;

        //конструктор класса, принимающий на вход имя модели и ее цену
        public Model(String modelName, double modelPrice) {
            //присваиваем полю modelName текущего класса данное значение modelName
            this.modelName = modelName;
            //присваиваем полю modelPrice текущего класса данное значение modelPrice
            this.modelPrice = modelPrice;
        }
    }

    //метод для поиска модели по имени в массиве
    private Model findModelByName(String modelName) {
        //вспомогательная переменная для прохождения по массиву
        int i = 0;
        //цикл прокручивается до тех пор, пока i меньше длинны массива,
        //текущий эл-т не равен null - пустому значению,
        //поле modelName текущего эл-та массива не равно заданному имени
        while (i < modelsArray.length &&
                !(modelsArray[i] == null) &&
                !modelsArray[i].modelName.equals(modelName))
            //увеличиваем счётчик цикла на единицу
            i++;
        //если счетчик цикла дошел до конца или до пустого эл-та - возвращаем null
        if (i == modelsArray.length || modelsArray[i] == null) return null;
        //в другом случаем возвращаем нужную модель
        else return modelsArray[i];
    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfNames() {
        //создаем массив строковых значений для имен моделей
        String[] carModels = new String[modelsArray.length];
        //цикл для прохода по массиву - до конца
        for (int i = 0; i < carModels.length; i++)
            //значению текущего эл-та массива названий всех моделей присваиваем
            //текущее имя модели из массива моделей
            carModels[i] = modelsArray[i].modelName;
        //возвращаем название модели
        return carModels;
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfPrices() {
        //создаем массив численных значений для цен моделей
        double[] carPrices = new double[modelsArray.length];
        //цикл для прохода по массиву - до конца
        for (int i = 0; i < carPrices.length; i++)
            //значению текущего эл-та массива цен всех моделей присваиваем
            //текущую цену модели из массива моделей
            carPrices[i] = modelsArray[i].modelPrice;
        //возвращаем цену модели
        return carPrices;
    }

    //метод для получения значения цены модели по её названию
    public double getPriceByName(String carModel) throws NoSuchModelNameException {
        Model soughtModel = findModelByName(carModel);
        if (soughtModel == null) throw new NoSuchModelNameException(carModel);
        else return soughtModel.modelPrice;
    }

    //метод для модификации значения названия модели
    public void setName(String oldCarModel, String newCarModel)
    //указываем исключения , которые может выбрасывать этот метод
            throws NoSuchModelNameException, DuplicateModelNameException {
        //если найдена машина с заданным новым  именем в массиве, то выбрасываем исключение
        if (findModelByName(newCarModel) != null) throw new
                DuplicateModelNameException(newCarModel);
        //в другом случае
        else {
            //Создаем ссылочную переменную типа Model, ей присваиваем найденную модель
            //искали модель со старым именем
            Model soughtModel = findModelByName(oldCarModel);
            //если найденная модель null, то выбрасываем исключение
            if (soughtModel == null) throw new NoSuchModelNameException(oldCarModel);
            //в  другом случае
            else
                //найденной модели присваиваем новое имя
                soughtModel.modelName = newCarModel;
        }
    }

    //метод для модификации значения цены модели по её названию
    public void setPriceByName(String carModel, double newCarPrice) throws
            //указываем исключения , которые может выбрасывать этот метод
            NoSuchModelNameException {
        //если новая цена машины меньше нуля, то выбрасываем исключение
        if (newCarPrice < 0) throw new ModelPriceOutOfBoundsException();
        //иначе
        else {
            //Создаем ссылочную переменную типа Model, ей присваиваем найденную модель
            Model soughtModel = findModelByName(carModel);
            //если найденная модель null, то выбрасываем исключение
            if (soughtModel == null) throw new NoSuchModelNameException(carModel);
            //иначе
            else
                //найденной модели присваиваем новую цену
                soughtModel.modelPrice = newCarPrice;
        }
    }

    //метод добавления названия модели и её цены (путем создания нового
    // массива Моделей), использовать метод Arrays.copyOf()
    public void addModel(String carModel, double carPrice) throws
            //указываем исключения , которые может выбрасывать этот метод
            DuplicateModelNameException {
        //если найдена машина с заданным новым  именем в массиве, то выбрасываем исключение
        if (findModelByName(carModel) != null) throw new
                DuplicateModelNameException(carModel);
        //иначе
        else {
            //если новая цена машины меньше нуля, то выбрасываем исключение
            if (carPrice < 0) throw new ModelPriceOutOfBoundsException();
            //иначе
            else {
                //создаем счётчик цикла
                int i = 0;
                //пока счётчик меньше длины массивы и текущая модель не пуста
                while (i < modelsArray.length && modelsArray[i] != null)
                    //увеличиваем счетчик
                    i++;
                //инициализация новой модели с заданным именем и ценой
                Model newModel = new Model(carModel, carPrice);
                //если цикл дошел до конца массива
                if (i == modelsArray.length) {
                    //создаем копию текущего массива, добавляем в массив один эл-т
                    modelsArray = Arrays.copyOf(modelsArray, modelsArray.length + 1);
                    //последнему элементу присваиваем новую модель
                    modelsArray[modelsArray.length - 1] = newModel;
                }
                //если был обнаружен пустой элемент, то этому элементу присваиваем новую модель
                else modelsArray[i] = newModel;
            }
        }
    }


    //метод удаления модели с заданным именем и её цены, использовать
    // методы System.arraycopy, Arrays.copyOf()
    public void removeModel(String carModel) throws NoSuchModelNameException {
        //если модель не найдена в массиве, выбрасываем исключение
        if (findModelByName(carModel) == null) throw new
                NoSuchModelNameException(carModel);
        //иначе
        else {
            //вспомогательная переменная для индекса удаляемого эл-та
            int indexForModelToRemove = -1;
            //счётчик цикла
            int i = 0;
            //цикл крутится, пока счётчик меньше кол-ва эл-тов в массиве
            //и текущий эл-т не пуст
            while (i < modelsArray.length && modelsArray[i] != null) {
                //если имя текущей модели равно заданному, то
                if (modelsArray[i].modelName.equals(carModel))
                    //индекс удаляемого эл-та равен текущему индексу
                    indexForModelToRemove = i;
                //увеличиваем значение счётчика
                i++;
            }
            //если удаляемый эл-т в масииве не последний,то
            if (indexForModelToRemove != (modelsArray.length - 1))
                //сдвигаем все элементы после удаляемого на 1 влево
                System.arraycopy(modelsArray, indexForModelToRemove + 1, modelsArray,
                        indexForModelToRemove, modelsArray.length -
                                (indexForModelToRemove + 1));
            //создаем копию массива, удаляя последний эл-т
            modelsArray = Arrays.copyOf(modelsArray, modelsArray.length - 1);
        }
    }

    //метод для получения размера массива Моделей
    public int getSize() {
        return modelsArray.length;
    }
}
