
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employee extends Thread {
    private String name;
    private int paycheck;
    private String startDate;

    public Employee(String name, int paycheck, String startDate) {
        this.name = name;
        this.paycheck = paycheck;
        this.startDate = startDate;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".txt"))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
            Calendar calendar = Calendar.getInstance();

            try {
                for (int months = 0; months < 12; months++) {
                    calendar.setTime((dateFormat.parse(startDate)));
                    calendar.add(Calendar.MONTH, months);

                    int sumPaycheck = paycheck;
                    if (months == 4 || months == 12) {
                        sumPaycheck += paycheck / 12 * (months + 1);
                    }
                    String line = dateFormat.format((calendar.getTime()) + " - " + sumPaycheck + " = Montlhy");
                    writer.write(line);
                    writer.newLine();
                }

            } catch (Exception e) {
                // TODO: handle exception

                e.getMessage();
            }

            System.out.println("File " + name + ".txt berhasil dibuat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
