package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Vehicle, Serializable {
    //поле типа String, хранящее марку автомобиля
    private String manufacturer;
    //массив объектов типа Model. Массив хранит в себе модели, которые есть у производителя автомобилей
    private Model[] modelsArray;

    public Car(String Manufacturer, int sizeArrayModels) {
        // конструктор. Принимает название производителя (строка) и число - размер массива моделей
        this.manufacturer = Manufacturer;   // сохраняем название как поле
        modelsArray = new Model[sizeArrayModels]; // создаем массив заданной длины
    }

    public Car(String manufacturer) {
        // конструктор. Принимает название производителя (строка)
        this.manufacturer = manufacturer; // сохраняем название как поле
    }

    public Car() { // конструктор по умолчанию (полям не присваиваются какие-либо значения)
    }

    //метод для поиска
    private Model findModelByName(String modelName) {
        int i = 0; // создаем переменную счетчик
        while (i < modelsArray.length && // пока не достигнем конца массива либо
                !(modelsArray[i] == null) && // пока не наткнемся на пустой элемент в массиве либо
                !modelsArray[i].modelName.equals(modelName)) // пока не найдем элемент, значение которого равно искомому
            i++; // прибавляем 1 к счетчику (продвигаемся по массиву)
        // если вышли из цикла на пустом элементе или же в конце, значит совпадений нет, возвращаем null
        if (i == modelsArray.length || modelsArray[i] == null) return null;
            // в противном случае (когда совпадение нашлось) возвращаем искомый элемент
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
    private class Model implements Serializable {
        private String modelName; // поле, которое хранит название модели
        private double modelPrice; // поле, которое хранит цену модели

        public Model(String modelName, double modelPrice) {
            // конструктор. Принимает в себя название модели (строка) и цену модели (число с точкой, например 23842.432)
            this.modelName = modelName; // сохраняем в поле для названия название
            this.modelPrice = modelPrice; // сохраняем в поле для цены цену
        }

    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfNames() {
        String[] carModels = new String[modelsArray.length]; // создаем массив длинной с массив моделей
        for (int i = 0; i < carModels.length; i++) // проходим по этому массиву
            carModels[i] = modelsArray[i].modelName; // каждому элементу присваиваем значение имени соответсвующей модели
        return carModels; // возвращаем получившийся массив
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfPrices() {
        double[] carPrices = new double[modelsArray.length]; // создаем массив длинной с массив моделей
        for (int i = 0; i < carPrices.length; i++)// проходим по этому массиву
            carPrices[i] = modelsArray[i].modelPrice;// каждому элементу присваиваем значение цены соответсвующей модели
        return carPrices;// возвращаем получившийся массив
    }

    //метод для получения значения цены модели по её названию
    public double getPriceByName(String carModel) throws NoSuchModelNameException {
        Model soughtModel = findModelByName(carModel); // используем поиск по названию модели
        if (soughtModel == null) throw new NoSuchModelNameException(carModel); // если не нашли, выбрасываем исключение
        else return soughtModel.modelPrice; // если нашли, возвращаем ее цену
    }

    //метод для модификации значения названия модели
    public void modifyName(String oldCarModel, String newCarModel)
            throws NoSuchModelNameException, DuplicateModelNameException {
        if (findModelByName(newCarModel) != null)
            throw new // если найти по новому имени удалось, выбрасываем исключение
                    DuplicateModelNameException(newCarModel);
        else {
            Model soughtModel = findModelByName(oldCarModel); // ищем модель по имени, которое хотим поменять
            if (soughtModel == null)
                throw new NoSuchModelNameException(oldCarModel); // если не нашли, выбрасываем исключение
            else
                soughtModel.modelName = newCarModel; // если нашли, меняем имя на новое
        }
    }

    //метод для модификации значения цены модели по её названию
    public void modifyPriceByName(String carModel, double newCarPrice) throws
            NoSuchModelNameException {
        // если вдруг новая цена автомобиля < 0, выбрасываем исключение
        if (newCarPrice < 0) throw new ModelPriceOutOfBoundsException();
        else { // если новая цена больше 0
            Model soughtModel = findModelByName(carModel); // ищем модель по имени
            if (soughtModel == null)
                throw new NoSuchModelNameException(carModel); // если не нашли, выбрасываем исключение
            else
                soughtModel.modelPrice = newCarPrice; // если нашли, записываем новое значение цены
        }
    }

    //метод добавления названия модели и её цены (путем создания нового
    // массива Моделей), использовать метод Arrays.copyOf()
    public void addModel(String carModel, double carPrice) throws
            DuplicateModelNameException {
        if (findModelByName(carModel) != null) throw new // если добавляемая модель уже есть, выбрасываем исключение
                DuplicateModelNameException(carModel);
        else {
            if (carPrice < 0)
                throw new ModelPriceOutOfBoundsException(); // если цена новой модели < 0, выбрасываем исключение
            else { // если новая модель уникальна и ее цена больше 0
                int i = 0; // создаем переменную счетчик
                while (i < modelsArray.length && modelsArray[i] != null) // пока не дойдем до конца массива, либо до пустого элемента
                    i++; // прибавляем 1 к счетчику
                Model newModel = new Model(carModel, carPrice); // создаем новый объект типа Модель
                if (i == modelsArray.length) { // если дошли до конца массива
                    modelsArray = Arrays.copyOf(modelsArray, modelsArray.length + 1); // расширяем массив на 1
                    modelsArray[modelsArray.length - 1] = newModel; // сохраняем новую модель в конец
                } else modelsArray[i] = newModel; // если попали на пустой элемент, сохраняем на его месте новую модель
            }
        }
    }

    //метод удаления модели с заданным именем и её цены, использовать
    // методы System.arraycopy, Arrays.copyOf()
    public void removeModel(String carModel) throws NoSuchModelNameException {
        if (findModelByName(carModel) == null) throw new // если удаляемой модели не нашлось, выбрасываем исключение
                NoSuchModelNameException(carModel);
        else {
            int indexForModelToRemove = -1; // переменная для индекса удаляемой модели в массиве
            int i = 0; // переменная счетчик
            while (i < modelsArray.length && modelsArray[i] != null) { // пока не дойдем до конца массива или до пустого элемента
                if (modelsArray[i].modelName.equals(carModel)) // если нашли удаляемую модель
                    indexForModelToRemove = i; // сохраняем ее индекс в массиве
                i++; // продвижение по массиву
            }
            if (indexForModelToRemove != (modelsArray.length - 1)) // если удаляется не последний элемент
                System.arraycopy(modelsArray, indexForModelToRemove + 1, modelsArray,
                        indexForModelToRemove, modelsArray.length -
                                (indexForModelToRemove + 1)); // перемещение каждой следующей модели от удаляемой на 1 назад
            modelsArray = Arrays.copyOf(modelsArray, modelsArray.length - 1); // сужение размера массива
            // либо на 155 строке последний элемент - удаляемая модель, либо ненужный элемент, тк он был перемещен на 1 назад (152-154)
        }
    }

    //метод для получения размера массива Моделей
    public int getSize() {
        return modelsArray.length;
    }

    // метод, печатающий все модели производителя
    public void printAllModels() {
        int i = 0; // переменная счетчик
        while (i < modelsArray.length && modelsArray[i] != null) { // пока не дойдем до конца массива либо до пустого элемента
            System.out.println("Name: " + this.manufacturer + " " +
                    modelsArray[i].modelName + " Price: " + // вывод модели в System.out
                    modelsArray[i].modelPrice);
            i++; // прохождение по массиву
        }
    }

}
