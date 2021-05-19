package com.ssau.exceptions;

//создаваемое исключение является потомком класса Exception, класса объявляемых исключений
public class NoSuchModelNameException extends Exception{
    //конструктор класса исключения. на вход получает модель автомобиля
    public NoSuchModelNameException(String modelName) {
        //использование конструктора родительского класса Exception.
        //создает новое исключение с указанным в скобках сообщением
        super("Model with the name \"" + modelName +
                "\" not found");
    }
}
