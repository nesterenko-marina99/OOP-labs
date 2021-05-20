package com.ssau.exceptions;

//создаваемое исключение является потомком класса RuntimeException,
// класса необъявляемых исключений
public class ModelPriceOutOfBoundsException extends RuntimeException{
    //конструктор класса исключения. на вход получает модель автомобиля
    public ModelPriceOutOfBoundsException() {
        //использование конструктора родительского класса RuntimeException.
        //создает новое исключение с указанным в скобках сообщением
        super("Model Price Out Of Bound Exception: " +
                "the price must be greater than 0");
    }
}
