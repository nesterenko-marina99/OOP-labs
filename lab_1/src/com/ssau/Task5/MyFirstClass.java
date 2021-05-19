import MyFirstPackage.*;

public class MyFirstClass {

    public static void main(String[] args) {
        MyFirstPackage mySecondClass = new MyFirstPackage(10);
        int firstElementVal = Integer.parseInt(args[args.length - 1]);
        mySecondClass.setElementValByIndex(0, firstElementVal);
        double average = mySecondClass.getAverage();
        System.out.println(average);
        mySecondClass.showAllElements();
    }
}

