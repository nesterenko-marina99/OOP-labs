package com.ssau.exceptions;

//создаваемое исключение является потомком класса Exception, класса объявляемых исключений
public class DuplicateModelNameException extends Exception{
    //конструктор класса исключения. на вход получает модель автомобиля
    public DuplicateModelNameException(String modelName) {
        //использование конструктора родительского класса Exception.
        //создает новое исключение с указанным в скобках сообщением
        super("Model named \"" + modelName +
                "\" already exists");
    }
}
