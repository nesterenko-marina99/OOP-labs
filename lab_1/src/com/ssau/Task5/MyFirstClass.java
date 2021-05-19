import MyFirstPackage.*;

public class MyFirstClass {

    public static void main(String[] args) {
        MyFirstPackage mySecondClass = new MyFirstPackage(10);
        int firstElementVal = Integer.parseInt(args[args.length - 1]);
        mySecondClass.setElementValByIndex(0, firstElementVal);
        int average = mySecondClass.getAverage();
        System.out.println("Среднее арифметическое: " + average +
                "\nВсе элементы массива:");
        mySecondClass.showAllElements();
    }
}

