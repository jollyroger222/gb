/**
 * 1 Напишите метод на вход которого подается двумерный строковый массив размером 4х4, при
 * подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 *
 * 2 Далее метод должен пройтись по всем элементам массива, преобразовать в int, и
 * просуммировать их. Если в каком-то элементе массива не удалось преобразование
 * (например, в ячейке лежит символ вместо числа), необходимо бросить исключение
 * MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
 *
 * 3 В методе main() необходимо вызвать полученный метод, обработать возможные исключения
 * MySizeArrayException и MyArrayDataException, и вывести результат расчета.

 */

public class dz2 {
    public static void main(String[] args) {
        String[][] correctMatrix = {
                {"2", "8", "3", "0"},
                {"1", "2", "1", "9"},
                {"4", "4", "2", "8"},
                {"6", "5", "9", "2"}
        };
        String[][] wrongSizeMatrix = {
                {"2", "8", "3", "0"},
                {"1", "2",},
                {"4", "4", "2", "8"},
                {"6", "5", "9", "2"}
        };
        String[][] wrongChar = {
                {"2", "8", "3", "0"},
                {"1", "2", "1", "2"},
                {"4", "", "2", "8"},
                {"6", "5", "9", "2"}
        };

        try {
            System.out.println(Converter.strConverter(correctMatrix));
        } catch (CustomException e) {
            e.getMessage();
        }


        try {
            System.out.println(Converter.strConverter(wrongSizeMatrix));
        } catch (CustomException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println(Converter.strConverter(wrongChar));
        } catch (CustomException e) {
            System.err.println(e.getMessage());
        }
    }
}


 class Converter {

    public static int strConverter(String[][] strArray)
            throws MyArraySizeException, MyArrayDataException {

        int sum = 0;

        if (4 != strArray.length) throw new MyArraySizeException();

        for (int i = 0; i < strArray.length; i++) {

            if (4 != strArray[i].length) throw new MyArraySizeException();

            for (int k = 0; k < strArray[i].length; k++) {

                try {
                    sum += Integer.parseInt(strArray[i][k]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, k);
                }
            }
        }

        return sum;
    }
}

 class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }
}

 class MyArrayDataException extends CustomException {

    public MyArrayDataException(int row, int col) {
        super(String.format("Ошибка в массиве в позиции array[%d, %d]", row, col));
    }
}

class MyArraySizeException extends CustomException {

    public MyArraySizeException() {
        super("Матрица должна быть 4x4");
    }
}