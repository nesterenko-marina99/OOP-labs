import java.util.Random;

public class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass mySecondClass = new MySecondClass(10);
        int firstElementVal = Integer.parseInt(args[0]);
        mySecondClass.setElementValByIndex(0, firstElementVal);
        int average = mySecondClass.getAverage();
        System.out.println("Среднее арифметическое: " + average +
                "\nВсе элементы массива:");
        mySecondClass.showAllElements();
    }
}
 class MySecondClass {
        private int numbers[];
        private Random random = new Random();

        public MySecondClass(int number) {
            this.numbers = new int[number];
            for (int i = 0; i < numbers.length; i++)
                this.numbers[i] = (random.nextInt(Integer.MAX_VALUE) - Integer.MAX_VALUE / 2);
        }

        public int getElementValByIndex(int index) {
            return numbers[index];
        }

        public void setElementValByIndex(int index, int val) {
            this.numbers[index] = val;
        }

        public int getAverage() {
            int sum = 0;
            for (int i : this.numbers)
                sum += i;
            return sum / this.numbers.length;
        }

        public void showAllElements() {
            for (int i : this.numbers)
                System.out.println(i);
        }
    }

