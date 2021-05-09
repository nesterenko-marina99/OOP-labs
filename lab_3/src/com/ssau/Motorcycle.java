package com.ssau;

import com.ssau.exceptions.ModelPriceOutOfBoundsException;
import com.ssau.exceptions.NoSuchModelNameException;
import com.ssau.exceptions.DuplicateModelNameException;

public class Motorcycle implements Vehicle {
    //поле типа String, хранящее марку мотоцикла
    private String manufacturer;
    private Model head;
    private int size;

    public Motorcycle(String Manufacturer) {
        this.manufacturer = Manufacturer;
        head = new Model();
        head.next = head;
        head.prev = head;
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

    private Model findModelByName(String modelName) throws NoSuchModelNameException {
        if (isEmptyList()) throw new
                NoSuchModelNameException(modelName);
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
    private class Model {
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
            DuplicateModelNameException, NoSuchModelNameException {
        if (isEmptyList()) createNewModel(mcModel, mcPrice);
        else if (findModelByName(mcModel) != null) throw new
                DuplicateModelNameException(mcModel);
        else createNewModel(mcModel, mcPrice);
    }

    //добавление в начало списка
    private void createNewModel(String mcModel, double mcPrice) {
        if (mcPrice < 0) throw new ModelPriceOutOfBoundsException();
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

}
