package functions;

import functions.basic.*;


import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TEST OVERRIDDEN METHODS ===");

        // Создаем тестовые функции
        System.out.println("\n1) Creating test functions...");

        // ArrayTabulatedFunction
        FunctionPoint[] points1 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 7.0),
                new FunctionPoint(3.0, 13.0)
        };
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);

        // LinkedListTabulatedFunction
        FunctionPoint[] points2 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 7.0),
                new FunctionPoint(3.0, 13.0)
        };
        LinkedListTabulatedFunction linkedFunc1 = new LinkedListTabulatedFunction(points2);

        // Функции с немного другими значениями
        FunctionPoint[] points3 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 7.0),
                new FunctionPoint(3.0, 12.0) // отличается последняя точка
        };
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points3);

        FunctionPoint[] points4 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 7.0)
        };
        LinkedListTabulatedFunction linkedFunc2 = new LinkedListTabulatedFunction(points4);

        // 2) Тестирование toString()
        System.out.println("\n2) Testing toString() method:");
        System.out.println("ArrayTabulatedFunction 1: " + arrayFunc1.toString());
        System.out.println("LinkedListTabulatedFunction 1: " + linkedFunc1.toString());
        System.out.println("ArrayTabulatedFunction 2: " + arrayFunc2.toString());
        System.out.println("LinkedListTabulatedFunction 2: " + linkedFunc2.toString());

        // 3) Тестирование equals()
        System.out.println("\n3) Testing equals() method:");

        // Сравнение одинаковых объектов одного класса
        System.out.println("arrayFunc1.equals(arrayFunc1): " + arrayFunc1.equals(arrayFunc1));
        System.out.println("linkedFunc1.equals(linkedFunc1): " + linkedFunc1.equals(linkedFunc1));

        // Сравнение одинаковых объектов разных классов (одинаковые точки)
        System.out.println("arrayFunc1.equals(linkedFunc1): " + arrayFunc1.equals(linkedFunc1));
        System.out.println("linkedFunc1.equals(arrayFunc1): " + linkedFunc1.equals(arrayFunc1));

        // Сравнение разных объектов
        System.out.println("arrayFunc1.equals(arrayFunc2): " + arrayFunc1.equals(arrayFunc2));
        System.out.println("arrayFunc1.equals(linkedFunc2): " + arrayFunc1.equals(linkedFunc2));
        System.out.println("linkedFunc1.equals(arrayFunc2): " + linkedFunc1.equals(arrayFunc2));

        // Сравнение с null и другим типом
        System.out.println("arrayFunc1.equals(null): " + arrayFunc1.equals(null));
        System.out.println("arrayFunc1.equals(\"string\"): " + arrayFunc1.equals("string"));

        // 4) Тестирование hashCode()
        System.out.println("\n4) Testing hashCode() method:");
        System.out.println("arrayFunc1.hashCode(): " + arrayFunc1.hashCode());
        System.out.println("linkedFunc1.hashCode(): " + linkedFunc1.hashCode());
        System.out.println("arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
        System.out.println("linkedFunc2.hashCode(): " + linkedFunc2.hashCode());

        // Проверка согласованности equals() и hashCode()
        System.out.println("\nConsistency check (equal objects should have equal hashCodes):");
        System.out.println("arrayFunc1.equals(linkedFunc1): " + arrayFunc1.equals(linkedFunc1));
        System.out.println("arrayFunc1.hashCode() == linkedFunc1.hashCode(): " +
                (arrayFunc1.hashCode() == linkedFunc1.hashCode()));

        // Изменение объекта и проверка изменения hashCode
        System.out.println("\nTesting hashCode change after modification:");
        int originalHash = arrayFunc1.hashCode();
        System.out.println("Original hashCode: " + originalHash);

        // Незначительно изменяем точку
        arrayFunc1.setPointY(2, 7.001); // изменяем на несколько тысячных
        int modifiedHash = arrayFunc1.hashCode();
        System.out.println("After modifying point Y: " + modifiedHash);
        System.out.println("HashCode changed: " + (originalHash != modifiedHash));

        // Восстанавливаем исходное значение для дальнейших тестов
        arrayFunc1.setPointY(2, 7.0);

        // 5) Тестирование clone()
        System.out.println("\n5) Testing clone() method:");

        // Клонирование ArrayTabulatedFunction
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc1.clone();
        System.out.println("Array original: " + arrayFunc1.toString());
        System.out.println("Array clone: " + arrayClone.toString());
        System.out.println("Original == Clone: " + (arrayFunc1 == arrayClone));
        System.out.println("Original.equals(Clone): " + arrayFunc1.equals(arrayClone));

        // Клонирование LinkedListTabulatedFunction
        LinkedListTabulatedFunction linkedClone = (LinkedListTabulatedFunction) linkedFunc1.clone();
        System.out.println("Linked original: " + linkedFunc1.toString());
        System.out.println("Linked clone: " + linkedClone.toString());
        System.out.println("Original == Clone: " + (linkedFunc1 == linkedClone));
        System.out.println("Original.equals(Clone): " + linkedFunc1.equals(linkedClone));

        // 6) Проверка глубокого клонирования
        System.out.println("\n6) Testing deep cloning:");

        // Изменяем исходный Array
        System.out.println("Before modification - Array:");
        System.out.println("Original: " + arrayFunc1.toString());
        System.out.println("Clone: " + arrayClone.toString());

        arrayFunc1.setPointY(1, 999.0); // изменяем исходный объект
        arrayFunc1.setPointX(2, 2.5);

        System.out.println("After modifying original - Array:");
        System.out.println("Original: " + arrayFunc1.toString());
        System.out.println("Clone: " + arrayClone.toString());
        System.out.println("Clone NOT affected: " + (!arrayFunc1.equals(arrayClone)));

        // Изменяем исходный LinkedList
        System.out.println("\nBefore modification - LinkedList:");
        System.out.println("Original: " + linkedFunc1.toString());
        System.out.println("Clone: " + linkedClone.toString());

        linkedFunc1.setPointY(0, 888.0); // изменяем исходный объект
        linkedFunc1.deletePoint(1); // удаляем точку из оригинала

        System.out.println("After modifying original - LinkedList:");
        System.out.println("Original: " + linkedFunc1.toString());
        System.out.println("Clone: " + linkedClone.toString());
        System.out.println("Clone NOT affected: " + (!linkedFunc1.equals(linkedClone)));

        // 7) Дополнительные тесты с FunctionPoint
        System.out.println("\n7) Testing FunctionPoint methods:");

        FunctionPoint point1 = new FunctionPoint(1.5, 2.5);
        FunctionPoint point2 = new FunctionPoint(1.5, 2.5);
        FunctionPoint point3 = new FunctionPoint(1.5, 2.6);
        FunctionPoint point4 = new FunctionPoint(1.6, 2.5);

        System.out.println("Point1: " + point1.toString());
        System.out.println("Point2: " + point2.toString());
        System.out.println("Point3: " + point3.toString());
        System.out.println("Point1.equals(Point2): " + point1.equals(point2));
        System.out.println("Point1.equals(Point3): " + point1.equals(point3));
        System.out.println("Point1.equals(Point4): " + point1.equals(point4));
        System.out.println("Point1.hashCode(): " + point1.hashCode());
        System.out.println("Point2.hashCode(): " + point2.hashCode());
        System.out.println("Point3.hashCode(): " + point3.hashCode());

        FunctionPoint pointClone = (FunctionPoint) point1.clone();
        System.out.println("Point1 clone: " + pointClone.toString());
        System.out.println("Point1 == Clone: " + (point1 == pointClone));
        System.out.println("Point1.equals(Clone): " + point1.equals(pointClone));

        // 8) Тестирование с аналитическими функциями
        System.out.println("\n8) Testing with analytical functions:");

        // Создаем табулированные версии аналитических функций
        TabulatedFunction sinTab = TabulatedFunctions.tabulate(new Sin(), 0, Math.PI, 5);
        TabulatedFunction cosTab = TabulatedFunctions.tabulate(new Cos(), 0, Math.PI, 5);

        System.out.println("Sin tabulated: " + sinTab.toString());
        System.out.println("Cos tabulated: " + cosTab.toString());
        System.out.println("Sin.equals(Cos): " + sinTab.equals(cosTab));
        System.out.println("Sin.hashCode(): " + sinTab.hashCode());
        System.out.println("Cos.hashCode(): " + cosTab.hashCode());

        // Клонирование и проверка
        TabulatedFunction sinClone = (TabulatedFunction) sinTab.clone();
        System.out.println("Sin clone equals original: " + sinTab.equals(sinClone));

    }
}