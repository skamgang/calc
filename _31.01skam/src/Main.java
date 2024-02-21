import java.awt.image.SinglePixelPackedSampleModel;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.print("Введите выражение: ");
        Scanner scn = new Scanner(System.in);
        String vvod = scn.nextLine();
        String otvet = calc(vvod);
        System.out.println("Ваш результат: "+otvet);

    }


    static String calc(String input) {
        int num1 = 1;
        int num2 = 1;
        int res = 0;
        Roma rNum1 = Roma.I;
        Roma rNum2 = Roma.I;


        String znak = null;
        input = input.replace(" ", "");
        boolean romaOrArab = false;

        String[] split = input.split("[+, \\-, *, /]");


        if (split.length == 1) {
            throw new RuntimeException("Строка не является математической операцией");
        } else if (split.length > 2 || split.length < 2)
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if (input.contains("+")) {
            znak = "+";
        } else if (input.contains("-")) {
            znak = "-";
        } else if (input.contains("*")) {
            znak = "*";
        } else if (input.contains("/")) {
            znak = "/";
        }

        String strNum1 = split[0];
        String strNum2 = split[1];

        try {
            num1 = Integer.parseInt(strNum1);
            num2 = Integer.parseInt(strNum2);
        } catch (NumberFormatException e) {
            try {
                rNum1 = Roma.valueOf(strNum1);
                rNum2 = Roma.valueOf(strNum2);
                romaOrArab = true;
            } catch (IllegalArgumentException ee) {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            }
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10 || rNum1.number() < 1 || rNum1.number() > 10 || rNum2.number() < 1 || rNum2.number() > 10) {
            throw new IllegalArgumentException("Калькулятор принимает числа от 1 до 10");
        } // добавил ограничение по количеству чисел в вв0д



        if (!romaOrArab) {
            switch (znak) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    res = num1 / num2;
                    break;
            }
            return String.valueOf(res);
        } else {
            switch (znak) {
                case "+":
                    res = rNum1.number() + rNum2.number();
                    break;
                case "-":
                    res = rNum1.number() - rNum2.number();
                    break;
                case "*":
                    res = rNum1.number() * rNum2.number();
                    break;
                case "/":
                    res = rNum1.number() / rNum2.number();
                    break;
            }
            if (res > 1) {
                for (Roma rRes : Roma.values()) {
                    if (rRes.number() == res) {
                        return String.valueOf(rRes);
                    }
                }
            } else {
                throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
            }
        }
        return null;
    }
}

