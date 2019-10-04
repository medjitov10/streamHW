package GA.homework.streamHW;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        list.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employeesOver50k = employees.stream()
                .filter(emp -> emp.getSalary() > 50_000)
                .collect(Collectors.toList());
        printList(employeesOver50k);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<String> employeesAfter2012 = employees.stream()
                .filter(e -> e.getHireDate().isAfter(LocalDate.ofYearDay(2012, 1)))
                .map(e -> e.getName())
                .collect(Collectors.toList());

        printList(employeesAfter2012);
    }

    @Test
    public void getMaxSalary() {
        double max = employees.stream()
                .max(Comparator.comparing(el -> el.getSalary()))
                .get().getSalary();
        System.out.println("Max:" + max);

    }

    @Test
    public void getMinSalary() {
        double min = employees.stream()
                .min(Comparator.comparing(e -> e.getSalary()))
                .get().getSalary();
        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
        double averageMale = 0;
        double averageFemale = 0;
        averageMale = employees.stream()
                .filter(el -> el.getGender().equals("Male"))
                .map(el -> el.getSalary())
                .reduce((double) 0, (el, acc) -> el + acc ) / employees.size();
        averageFemale = employees.stream()
                .filter(el -> el.getGender().equals("Female"))
                .map(el -> el.getSalary())
                .reduce((double) 0, (el, acc) -> el + acc ) / employees.size();

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = employees.stream()
                .max(Comparator.comparing(e -> e.getSalary()))
                .get();
        System.out.println("MAX employee salary: " + highest);
    }
}