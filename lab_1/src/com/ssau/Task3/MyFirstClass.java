public class MyFirstClass {

    public static void main(String[] args) {
        //каждая буква фамилии - элемент массива args.
        //индексация элементов массива начинается с нуля
        for (int i = 0; i < args.length; i++) //цикл для побуквенного вывода. i - индекс массива
            System.out.print(args[i] + "\n"); //вывод одной буквы args[i] и перевод строки \n
        System.out.println(); //перевод строки
    }
}