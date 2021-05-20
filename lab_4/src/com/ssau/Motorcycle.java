package com.ssau;

import com.ssau.exceptions.DuplicateModelNameException;
import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

//добавилась реализация интерфейса Cloneable
public class Motorcycle implements Vehicle, Serializable, Cloneable {
    //поле типа String, хранящее марку мотоцикла
    private String manufacturer;
    private Model head = new Model();

    {
        head.next = head;
        head.prev = head;
    }

    private int size;

    public Motorcycle(String Manufacturer) {
        this.manufacturer = Manufacturer;
    }

    public Motorcycle(String manufacturer, int size) throws
            DuplicateModelNameException {
        this.manufacturer = manufacturer;
        for (int i = 0; i < size; i++)
            addModel(null, 0);
    }

    public void setSize(int size) {
        this.size = size;
    }

    //метод для получения марки мотоцикла
    public String getManufacturer() {
        return manufacturer;
    }

    //метод для модификации марки мотоцикла
    public void setManufacturer(String mcManufacturer) {
        this.manufacturer = mcManufacturer;
    }

    private boolean isEndOfList(Model pn, Model pp) {
        return pn.next == head && pp.prev == head;
    }

    private boolean isFound(Model pn, Model pp, String searchString) {
        return pn.modelName.equals(searchString) || pp.modelName.equals(searchString);
    }

    private boolean isEmptyList() {
        return head.next == head && head.prev == head;
    }

    private Model findModelByName(String modelName)  {
        if (isEmptyList()) return null;
        else {
            Model pn = head.next;
            Model pp = head.prev;
            while (!isEndOfList(pn, pp) && !isFound(pn, pp, modelName)) {
                pn = pn.next;
                pp = pp.prev;
            }
            if (pn.modelName.equals(modelName)) return pn;
            else if (pp.modelName.equals(modelName)) return pp;
            else return null;
        }
    }

    //внутренний класс Модель, имеющий поля название модели и её цену,
    // а также конструктор
    private class Model implements Serializable, Cloneable {
        private String modelName;
        private double modelPrice;
        private Model prev;
        private Model next;

        public Model() {
            this.modelName = null;
            this.modelPrice = Double.NaN;
            this.prev = null;
            this.next = null;
        }

        public Model(String modelName, double modelPrice) {
            this.modelName = modelName;
            this.modelPrice = modelPrice;
            this.prev = null;
            this.next = null;
        }

        public Model(String modelName, double modelPrice, Model prev, Model next) {
            this.modelName = modelName;
            this.modelPrice = modelPrice;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() { // метод, который приводит модель в формат строки
            return "Имя модели: " + modelName + "\n" + "Цена модели: " + modelPrice; // возвращаем имя и цену модели в формате
            //Имя модели: *******
            //Цена модели: ****.***
        }

        @Override
        protected Object clone() throws CloneNotSupportedException { // метод клонирования модели
            return super.clone(); // вызывает родительский метод клонирования (метод из класса Object)
        }
    }

    //метод для модификации значения названия модели
    public void modifyName(String oldMcModel, String newMcModel) throws
            NoSuchModelNameException, DuplicateModelNameException {
        if (findModelByName(newMcModel) != null) throw new
                DuplicateModelNameException(newMcModel);
        else {
            Model soughtModel = findModelByName(oldMcModel);
            if (soughtModel == null) throw new NoSuchModelNameException(oldMcModel);
            else
                soughtModel.modelName = newMcModel;
        }
    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfNames() {
        String[] allMcModels = new String[size];
        Model p = head.next;
        int i = 0;
        while (p != head) {
            allMcModels[i] = p.modelName;
            p = p.next;
            i++;
        }
        return allMcModels;
    }

    //метод для получения значения цены модели по её названию
    public double getPriceByName(String mcModel) throws NoSuchModelNameException {
        Model soughtModel = findModelByName(mcModel);
        if (soughtModel == null) throw new NoSuchModelNameException(mcModel);
        else return soughtModel.modelPrice;
    }

    //метод для модификации значения цены модели по её названию
    public void modifyPriceByName(String mcModel, double mcPrice) throws
            NoSuchModelNameException {
        if (mcPrice < 0) throw new ModelPriceOutOfBoundsException();
        else {
            Model soughtModel = findModelByName(mcModel);
            if (soughtModel == null) throw new NoSuchModelNameException(mcModel);
            else
                soughtModel.modelPrice = mcPrice;
        }
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfPrices() {
        double[] mcPrices = new double[size];
        Model p = head.next;
        int i = 0;
        while (p != head) {
            mcPrices[i] = p.modelPrice;
            p = p.next;
            i++;
        }
        return mcPrices;
    }

    //метод добавления названия модели и её цены
    public void addModel(String mcModel, double mcPrice) throws
            DuplicateModelNameException {
        if (findModelByName(mcModel) != null) throw new
                DuplicateModelNameException(mcModel);
        else if (mcPrice < 0) throw new ModelPriceOutOfBoundsException();
        else {
            Model p = head;
            Model newModel = new Model(mcModel, mcPrice, p, p.next);
            p.next.prev = newModel;
            p.next = newModel;
            size++;
        }
    }

    //метод удаления модели с заданным именем и её цены
    public void removeModel(String mcModel) throws NoSuchModelNameException {
        Model p = findModelByName(mcModel);
        if (p == null) throw new NoSuchModelNameException(mcModel);
        else {
            p.prev.next = p.next;
            p.next.prev = p.prev;
            size--;
        }
    }

    //метод для получения размера массива Моделей
    public int getSize() {
        return size;
    }

    @Override
    public String toString() { // метод приведения Мотоцикла к строке
        final StringBuffer sb = new StringBuffer("Мотоцикл"); // создаем переменную, записываем в нее, что мы говорим про мотоцикл
        sb.append(" Производитель: ").append(manufacturer).append("\n"); // добавляем производителя и перевод строки
        sb.append("Модельный ряд: "); // добавляем фразу "Модельный ряд: "
        if (isEmptyList()) sb.append("пусто"); // если список пуст, добавляем соответствующую пометку
        else {
            sb.append("\n"); // если есть модели, то тогда добавляем перевод строки
            Model p = head.next; // проходимся по списку, начиная с элемента, следующего за головой
            while (p != head) { // пока не придем обратно к голове
                // добавляем в строку название модели и стоимость через пробел, а также перевод строки
                sb.append(p.modelName).append(" ").append(p.modelPrice).append("\n");
                p = p.next; // переходим к следующей в списке модели
            }
        }
        return sb.toString(); // возвращаем получившуюся строку
    }

    @Override
    public boolean equals(Object o) { // метод сравнения текущего объекта с неким иным объектом
        if (this == o) return true; // если к нам попали 2 абсолютно одинаковых объекта, то сразу возвращаем true
        //if (o == null || getClass() != o.getClass()) return false;
        //Car car = (Car) o;
        //return manufacturer.equals(car.manufacturer) && Arrays.equals(modelsArray, car.modelsArray);
        if (o instanceof Vehicle) { // если получили на вход Технику
            Vehicle v = (Vehicle) o; // сохраняем в другую переменную уже типа Техника
            //int curSize = getSize();
            int comparedSize = v.getSize(); // получаем количество моделей
            if (comparedSize == getSize()) // сравниваем количество моделей с текущим объектом
                if (v.getManufacturer().equals(manufacturer)) { // сравниваем производителей
                    String[] currentNames = getArrayOfNames(); // сохраняем массив моделей
                    Arrays.sort(currentNames); // сортируем его (вдруг в полученном объекте те же модели, но в другом порядке)
                    double[] currentPrices = getArrayOfPrices(); // сохраняем массив цен
                    Arrays.sort(currentPrices); // сортируем его
                    String[] comparedNames = v.getArrayOfNames(); // получаем массив имен из сравниваемого объекта
                    Arrays.sort(comparedNames); // сортируем его
                    double[] comparedPrices = v.getArrayOfPrices(); // получаем массив цен из сравниваемого объекта
                    Arrays.sort(comparedPrices); // сортируем его
                    int i = 0; // переменная счетчик
                    // пока не дойдем до конца массивов и пока на каждой итерации элементы с одним индексом в
                    // соответствующих массивах двух объектов совпадают
                    while (i < comparedSize && currentNames[i].equals(comparedNames[i]) &&
                            (Double.compare(currentPrices[i], comparedPrices[i]) == 0))
                        i++; // проходим по массиву
                    return i == comparedSize; // если дошли до конца массива, значит совпали все элементы
                }
        }
        return false; // во всех противных случаях, возвращается false
    }


    // функция хэш-кода. Вызывает аналогичную функцию у класса Object, передавая поля объекта как параметры
    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, head, size);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {  // метод, создающий копию объекта
        // вызываем метод clone у родительского класса (Object) и результат сохраняется в новой переменной
        Motorcycle vehicleClone =  (Motorcycle) super.clone();
        vehicleClone.head = (Model) head.clone(); // клонируем голову, вызывая метод clone у головы
        Model pn = vehicleClone.head; // сохраняем голову в отдельной переменной
        for (int i = 0; i < getSize(); i++) { // проходим по списку
            Model newClone = (Model) pn.next.clone(); // клонируем следующий элемент
            newClone.prev = pn; // привязываем текущий элемент
            pn.next = newClone; // с клоном следующего
            pn = pn.next; // проходим далее по массиву
        }
        pn.next = vehicleClone.head; // последний элемент массива привязываем к новой голове
        vehicleClone.head.prev = pn; // а новую голову - к последнему элементу
        return vehicleClone; // возвращаем клонированный объект
    }
}
