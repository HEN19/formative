public class PaycheckSystem {
    public static void main(String[] args) {
        // Creating Employee instances with different religions
        Employee muslimEmployee = new Employee("Muslim Employee", 5000000, "1 February 2024", "Islam");
        Employee christianEmployee = new Employee("Christian Employee", 5000000, "1 February 2024", "Christianity");
        Employee hinduEmployee = new Employee("Hindu Employee", 5000000, "1 February 2024", "Hinduism");
        Employee buddhistEmployee = new Employee("Buddhist Employee", 5000000, "1 February 2024", "Buddhism");

        // Start the threads
        muslimEmployee.start();
        christianEmployee.start();
        hinduEmployee.start();
        buddhistEmployee.start();

        try {
            // Wait for threads to finish
            muslimEmployee.join();
            christianEmployee.join();
            hinduEmployee.join();
            buddhistEmployee.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
