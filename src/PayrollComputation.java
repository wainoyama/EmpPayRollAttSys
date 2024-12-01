public class PayrollComputation {
    private final double baseRate;
    private final int regularHours;
    private int overtimeHours;
    private int holidayHours;
    private double deductions;

    public PayrollComputation(double baseRate) {
        this.baseRate = baseRate;
        this.regularHours = 8;
        this.overtimeHours = 0;
        this.holidayHours = 0;
        this.deductions = 0;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setHolidayHours(int holidayHours) {
        this.holidayHours = holidayHours;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double calculateBasePay() {
        return baseRate * regularHours;
    }

    public double calculateOvertimePay() {
        double overtimeRate = baseRate * 1.5;
        return overtimeHours * overtimeRate;
    }

    public double calculateHolidayPay() {
        double holidayRate = baseRate * 2.0;
        return holidayHours * holidayRate;
    }

    public double calculateDeductions() {
        return deductions;
    }

    public double calculateNetPay() {
        return calculateBasePay() + calculateOvertimePay() + calculateHolidayPay() - calculateDeductions();
    }

    public double calculateWeeklyNetPay() {
        return calculateNetPay() * 5;
    }

    public void printPayrollDetails() {
        System.out.printf("Base Pay: %.2f%n", calculateBasePay());
        System.out.printf("Overtime Pay: %.2f%n", calculateOvertimePay());
        System.out.printf("Holiday Pay: %.2f%n", calculateHolidayPay());
        System.out.printf("Deductions: %.2f%n", calculateDeductions());
        System.out.printf("Net Pay: %.2f%n", calculateNetPay());
        System.out.printf("Net Pay of the week: %.2f%n", calculateWeeklyNetPay());
    }
}

