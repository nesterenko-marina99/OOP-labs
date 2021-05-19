import java.util.Random;

public class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass mySecondClass = new MySecondClass(10);
        int firstElementVal = Integer.parseInt(args[args.length - 1]);
        mySecondClass.setElementValByIndex(0, firstElementVal);
        double average = mySecondClass.getAverage();
        System.out.println(average);
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

        public double getAverage() {
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

