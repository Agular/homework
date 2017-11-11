package praks9;

import praks9.student.Student;
import praks9.travel.Destination;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Peeter", 4.5));
        students.add(new Student("Aivo", 3.2));
        students.add(new Student("Marko", 1.8));
        students.add(new Student("Sander", 2.0));
        students.add(new Student("Mikk", 5.0));
        students.add(new Student("Fanda", 3.9));
        students.add(new Student("Mako", 4.2));
        students.add(new Student("Noki", 1.1));
        students.add(new Student("Taive", 1.1));

        System.out.println("Get students whose name contains i or e");
        students.stream().filter(s -> s.getName().contains("i"))
                         .filter(s -> s.getName().contains("e"))
                .forEach(System.out::println);

        // Filtri tulemust ignoreeritakse, s.t. mitte midagi ei tehta sellega edasi.
        // Filter ootab predikaati, aga meie tagastame lihtsalt double, mis ei sobi predikaadiks.
        // Predikaat one tõeväärtusfunktsioon!
        //students.stream().filter((s, m) -> s.getGrade());
        System.out.println("Get students whose grade is higher or equal to 3.0");
        students.stream().filter(s -> s.getGrade() >= 3.0).forEach(System.out::println);

        Destination dubai = new Destination("Dubai", 303.15);
        Destination tallinn = new Destination("Tallinn", 288.15);
        Destination Tartu = new Destination("Tartu", 283.15);

        //Get the average weather in Celsius.
        System.out.println(dubai.getAvgWeatherInCelsius());
        //Get the average weather in Fahrenheit.
        System.out.println(dubai.getAvgWeatherInFahrenheit());

        //With lambda
        System.out.println("With lambda");
        System.out.println(dubai.getAvgWeather(d -> d - 273.15));
        System.out.println(dubai.getAvgWeather(d -> d * (9.0)/(5.0) - 459.67));

        //With static methods
        System.out.println("With static methods");
        System.out.println(tallinn.getAvgWeather(Destination::convertFromKevinToCelsius));
        System.out.println(tallinn.getAvgWeather(Destination::convertFromKevinToFahrenheit));

        //New stream
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("out.txt", true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.setOut(new PrintStream(out));

        //With static methods and to out.txt file.
        System.out.println("With static methods");
        System.out.println(tallinn.getAvgWeather(Destination::convertFromKevinToCelsius));
        System.out.println(tallinn.getAvgWeather(Destination::convertFromKevinToFahrenheit));
    }
}
