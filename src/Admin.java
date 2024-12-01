public class Admin extends User {
    private PayrollSettings payrollSettings;

    public Admin(String username, String password) {
        super(username, password);
    }

    public void setPayrollSettings(PayrollSettings settings) {
        this.payrollSettings = settings;
    }

    public PayrollSettings getPayrollSettings() {
        return payrollSettings;
    }

    public void updateOvertimeRate(double overtimeRate) {
        payrollSettings.setOvertimeRate(overtimeRate);
        System.out.println("Overtime rate updated successfully to: " + overtimeRate);
    }

    public void updateHolidayRate(double holidayRate) {
        payrollSettings.setHolidayRate(holidayRate);
        System.out.println("Holiday rate updated successfully to: " + holidayRate);
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Admin: " + getUsername());
    }
}

