import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOptional {
    /*
    Напишите метод findMinMax, находящий в стриме минимальный и максимальный
    элементы в соответствии порядком, заданным Comparator'ом.
    Stream<? extends T> stream,
    Comparator<? super T> order,
    BiConsumer<? super T, ? super T> minMaxConsumer
     */
    public static void main(String[] args) {
        // Лист для Тестов:
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add((int) (Math.random() * 100));
        }
        // Тестирование методов:
//        numbers.stream().limit(5).forEach(System.out::print);
        numbers.stream().sorted().forEachOrdered(x -> System.out.print(x + "|"));
        System.out.println();
        findMinMax(numbers.stream(), Integer::compareTo, (x, y) -> System.out.println("min: " + x + ", max: " + y));
        System.out.println("of which are even :");
        printEven(numbers);
        System.out.println("Вариант 2");
        printEven2(numbers);

    }

    //Сами методы:
    //вариант 1 Задача 1
    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream
                .sorted(order)
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            minMaxConsumer.accept(list.get(0), list.get(list.size() - 1));
        } else {
            minMaxConsumer.accept(null, null);
        }
    }
    //вариант 1 Задача 2
    public static void printEven(List<Integer> list) {
        int count = (int) list.stream().filter(x -> x % 2 == 0).count();
        System.out.println("Total - "+count +":");
        list.stream().filter(x -> x % 2 == 0).forEach(System.out::println);
    }
    // вариант 2 Задача 2 (оптимизация метода):
    public static void printEven2(List<Integer> list) {
        int count = (int) list.stream().filter(x -> x % 2 == 0)
                .peek((x)->System.out.print("|" +x+"|"))
                .count();
        System.out.println("\n"+count);
    }//убран лишний поток с фильтрацией
}