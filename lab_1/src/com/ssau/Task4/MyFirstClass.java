import java.util.Random;

public class MyFirstClass {
    public static void main(String[] args) {
        //создание экземпляра класса MySecondClass. Число 10 задает кол-во эл-тов массива
        MySecondClass mySecondClass = new MySecondClass(10);
        //преобразуем строку args[0] в число и присваиваем это значение переменной firstElementVal
        int firstElementVal = Integer.parseInt(args[0]);
        //устанавливаем значение первого элемента массива равным firstElementVal
        mySecondClass.setElementValByIndex(0, firstElementVal);
        //находим среднее арифметическое и присваиваем это значение переменной average
        int average = mySecondClass.getAverage();
        //выводим значение среднего арифметического
        System.out.println("Среднее арифметическое: " + average +
                "\nВсе элементы массива:");
        //выводим все элементы массива
        mySecondClass.showAllElements();
    }
}

 class MySecondClass {
        //поле класса - массив int
        private int numbers[];

        //конструктор. на вход получает кол-во элементов массива
        public MySecondClass(int number) {
            //инициализация массива для указанного кол-ва элементов
            this.numbers = new int[number];
            //Создание экземпляра класса random для генерации случайных значений
            Random random = new Random();
            //цикл для заполнения массива
            for (int i = 0; i < numbers.length; i++)
                //каждому эл-ту массива присваиваем случайное значение из заданного диапазона
                this.numbers[i] = (random.nextInt(Integer.MAX_VALUE) - Integer.MAX_VALUE / 2);
        }

        //метод для получения значения эл-та массива по индексу
        public int getElementValByIndex(int index) {
            return numbers[index];
        }

        //метод для изменения значения эл-та массива по индексу
        public void setElementValByIndex(int index, int val) {
            this.numbers[index] = val;
        }

        //метод для получения среднего арифметического
        public int getAverage() {
            //создание переменной для суммирования
            int sum = 0;
            //цикл foreach для суммирования
            for (int i : this.numbers)
                //к переменной sum на каждой итерации цикла добавляется последующий эл-т массива
                sum += i;
            //возвращаем частное суммы и кол-ва элементов массива, т.е. среднее арифметическое
            return sum / this.numbers.length;
        }

        //метод для вывода всех эл-тов на экран
        public void showAllElements() {
            //цикл foreach для прохода по эл-там массива
            for (int i : this.numbers)
                //вывод эл-та
                System.out.println(i);
        }
    }

