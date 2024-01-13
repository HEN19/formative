
public class PaycheckSystem {
    public static void main(String[] args) {
        Employee karyawan0Thread = new Employee("karyawan1", 5000000, "1 February 2024");
        Employee karyawan1Thread = new Employee("karyawan2", 8000000, "1 March 2024");
        Employee karyawan2Thread = new Employee("karyawan3", 12000000, "1 April 2024");

        karyawan0Thread.start();
        karyawan1Thread.start();
        karyawan2Thread.start();
    }
}