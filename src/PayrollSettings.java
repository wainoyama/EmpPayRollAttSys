public class PayrollSettings {
    private double overtimeRate;
    private double holidayRate;

    public PayrollSettings(double overtimeRate, double holidayRate) {
        this.overtimeRate = overtimeRate;
        this.holidayRate = holidayRate;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public double getHolidayRate() {
        return holidayRate;
    }

    public void setHolidayRate(double holidayRate) {
        this.holidayRate = holidayRate;
    }

    public boolean isHoliday(String date) {
        return date.equals("2024-12-25"); // Example: Christmas holiday
    }
}

