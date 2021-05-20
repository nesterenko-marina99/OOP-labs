package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

//добавилась реализация интерфейса Cloneable
public class Car implements Vehicle, Serializable, Cloneable {
    //поле типа String, хранящее марку автомобиля
    private String manufacturer;
    private Model[] modelsArray;
    //private int size;

    public Car(String Manufacturer, int sizeArrayModels) {
        this.manufacturer = Manufacturer;
        modelsArray = new Model[sizeArrayModels];
    }

    public Car(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Car() {
    }

    //метод для поиска
    private Model findModelByName(String modelName) {
        int i = 0;
        while (i < modelsArray.length &&
                !(modelsArray[i] == null) &&
                !modelsArray[i].modelName.equals(modelName))
            i++;
        if (i == modelsArray.length || modelsArray[i] == null) return null;
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
    private class Model implements Serializable, Cloneable {
        private String modelName;
        private double modelPrice;

        public Model(String modelName, double modelPrice) {
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }

        //реализация метода toString
        @Override
        public String toString() {
            //возвращаем заданную строку
            return "Имя модели: " + modelName + "\n" + "Цена модели: " + modelPrice;
        }

        //реализация метод clone
        @Override
        protected Object clone() throws CloneNotSupportedException {
            //возвращает копию объекта
            return (Model) super.clone();
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

    //реализация метода toString
    @Override
    public String toString() {
        //создаем string buffer - похож на String, но может быть изменен
        final StringBuffer sb = new StringBuffer("Автомобиль");
        //добавляем в string buffer производителя и подписи c помощью append
        sb.append(" Производитель: ").append(manufacturer).append("\n");
        sb.append("Модельный ряд: ");
        //если ссылка на массив пуста, товыводим информацию об этом
        if (modelsArray == null) sb.append("пусто");
        //иначе
        else {
            //перевод строки
            sb.append("\n");
            //счётчик цикла
            int i = 0;
            //пока не дойдем до конца массива или до элемента, определяемого null
            while (i < this.getSize() && modelsArray[i] != null) {
                //добавляем в string buffer имя и цену каждой модели
                sb.append(modelsArray[i].modelName).append(" ").
                        append(modelsArray[i].modelPrice).append("\n");
                //увеличиваем счётчик цикла
                i++;
            }
        }
        //возвращаем полученный stringbuffer, преобразованный к String
        return sb.toString();
    }

    //реализация метода equals - проверка на равенство
    @Override
    public boolean equals(Object o) {
        //если тот же класс - возвращаем true
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        //Car car = (Car) o;
        //return manufacturer.equals(car.manufacturer) && Arrays.equals(modelsArray, car.modelsArray);
        //проверяем, создан ли  данный объект на основе Vehicle
        if (o instanceof Vehicle) {
            //если да, создаем vehicle и присваиваем ему этот объект
            Vehicle v = (Vehicle) o;
            //int curSize = getSize();
            //создаем переменную с размерностью данного класса
            int comparedSize = v.getSize();
            //если размерность совпадает с текущим классом, то
            if (comparedSize == getSize())
                //проверяем, одинаковый ли производитель
                if (v.getManufacturer().equals(manufacturer)) {
                    //создаем массив имен моделей текущего класса
                    String[] currentNames = getArrayOfNames();
                    //сортируем его
                    Arrays.sort(currentNames);
                    //создаем массив цен моделей текущего класса
                    double[] currentPrices = getArrayOfPrices();
                    //сортируем его
                    Arrays.sort(currentPrices);
                    //создаем массив имен моделей данного класса
                    String[] comparedNames = v.getArrayOfNames();
                    //сортируем его
                    Arrays.sort(comparedNames);
                    //создаем массив цен моделей текущего класса
                    double[] comparedPrices = v.getArrayOfPrices();
                    //сортируем его
                    Arrays.sort(comparedPrices);
                    //создаем счётчик цикла
                    int i = 0;
                    //до тех пор, пока счётчик меньше размерности, имя модели в массиве имен
                    //текущего класса совпадает с именем в данном классе и цена модели в
                    //массиве цен текущего класса равна цене в массиве цен
                    while (i < comparedSize && currentNames[i].equals(comparedNames[i]) &&
                            (Double.compare(currentPrices[i], comparedPrices[i]) == 0))
                        //увеличиваем счётчик
                        i++;
                    //возвращаем, дошёл ли счётчик до конца. если да, то все равно, true
                    return i == comparedSize;
                }
        }
        //в любых других случаях возвращаем ложь
        return false;
    }

    //реализация метода hashcode
    @Override
    public int hashCode() {
        int result = Objects.hash(manufacturer);
        result = 31 * result + Arrays.hashCode(modelsArray);
        return result;
    }

    //реализация метода clone. глубокое клонирование
    @Override
    protected Object clone() throws CloneNotSupportedException {
        //создаем клон объекта целиком
        Car vehicleClone = (Car) super.clone();
        //клонируем массив
        vehicleClone.modelsArray = modelsArray.clone();
        for (int i = 0; i < getSize(); i++) {
            //клонируем каждый элемент отдельно
            vehicleClone.modelsArray[i] = (Model) modelsArray[i].clone();
        }
        //присваиваем клону название производителя
        vehicleClone.manufacturer = getManufacturer();
        //vehicleClone.size = getSize();
        //возвращаем независимый клон объекта
        return vehicleClone;
    }
}
