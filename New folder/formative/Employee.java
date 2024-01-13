import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employee extends Thread {
    private String name;
    private int paycheck;
    private String startDate;
    private String religion;
    private StringBuilder outputData;

    public Employee(String name, int paycheck, String startDate, String religion) {
        this.name = name;
        this.paycheck = paycheck;
        this.startDate = startDate;
        this.religion = religion;
        this.outputData = new StringBuilder();
    }

    public String getOutputData() {
        return outputData.toString();
    }

    @Override
    public void run() {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("d MMMM yyyy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".txt"))) {
            calendar.setTime(inputDateFormat.parse(startDate));

            for (int months = 0; months < 12; months++) {
                int sumPaycheck = paycheck;

                // Check for THR based on religion
                if ("Islam".equalsIgnoreCase(religion) && months == 3) {
                    // THR two weeks before 20 April
                    calendar.set(Calendar.MONTH, Calendar.APRIL);
                    calendar.set(Calendar.DAY_OF_MONTH, 20);
                    calendar.add(Calendar.DATE, -14);

                    int thrPaycheck = paycheck / 12 * (months + 1); // THR calculation
                    sumPaycheck += thrPaycheck;

                    // Format the THR paycheck
                    String thrLine = name + ": THR Paycheck - " + decimalFormat.format(thrPaycheck) +
                            " on " + outputDateFormat.format(calendar.getTime());
                    outputData.append(thrLine).append("\n");

                    writer.write(thrLine);
                    writer.newLine();

                    System.out.println(thrLine);
                }

                // Check for Christmas paycheck based on religion
                if ("Christianity".equalsIgnoreCase(religion) && months == 11) {
                    // Christmas paycheck two weeks before Christmas Eve (December 24th)
                    calendar.add(Calendar.DATE, -14);
                    int christmasPaycheck = paycheck / 12 * (months + 1); // Christmas paycheck calculation
                    sumPaycheck += christmasPaycheck;

                    // Format the Christmas paycheck
                    String christmasLine = name + ": Christmas Paycheck - " + decimalFormat.format(christmasPaycheck) +
                            " on " + outputDateFormat.format(calendar.getTime());
                    outputData.append(christmasLine).append("\n");

                    writer.write(christmasLine);
                    writer.newLine();

                    System.out.println(christmasLine);
                }

                // For Hindu and Buddhist employees, provide THR in the 12th month
                if (("Hindu".equalsIgnoreCase(religion) || "Buddhist".equalsIgnoreCase(religion)) && months == 11) {
                    // THR in the 12th month
                    calendar.add(Calendar.MONTH, 1);
                    int thrPaycheck = paycheck / 12 * (months + 1); // THR calculation
                    sumPaycheck += thrPaycheck;

                    // Format the THR paycheck
                    String thrLine = name + ": THR Paycheck - " + decimalFormat.format(thrPaycheck) +
                            " on " + outputDateFormat.format(calendar.getTime());
                    outputData.append(thrLine).append("\n");

                    writer.write(thrLine);
                    writer.newLine();

                    System.out.println(thrLine);
                }

                // Format the sumPaycheck for the current month
                String line = name + ": " + outputDateFormat.format(calendar.getTime()) + " - " +
                        decimalFormat.format(sumPaycheck) + " = Monthly";
                outputData.append(line).append("\n");

                writer.write(line);
                writer.newLine();

                System.out.println(line);

                // Move to the next month
                calendar.add(Calendar.MONTH, 1);
            }

            System.out.println("File " + name + ".txt berhasil dibuat");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
