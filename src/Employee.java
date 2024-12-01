public class Employee extends User {
    private String position;
    private double hourlyRate;
    private double salary;
    private boolean isLoggedIn;

    public Employee(String name, String position, double hourlyRate) {
        super(name, "");
        this.position = position;
        this.hourlyRate = hourlyRate;
        this.salary = 0.0;
        this.isLoggedIn = false;
    }

    public String getName() {
        return getUsername();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login() {
        this.isLoggedIn = true;
    }

    public void logout() {
        this.isLoggedIn = false;
    }

    public void calculateSalary(int minutesLate, int minutesOvertime, PayrollSettings settings) {
        double deduction = 0;
        double addition = 0;

        if (minutesLate > 0) {
            deduction = (minutesLate / 60.0) * hourlyRate;
            System.out.printf("Late: %d minutes. Deducted: %.2f PHP%n", minutesLate, deduction);
        }

        if (minutesOvertime > 0) {
            addition = (minutesOvertime / 60.0) * hourlyRate * settings.getOvertimeRate();
            System.out.printf("Overtime: %d minutes. Added: %.2f PHP%n", minutesOvertime, addition);
        }

        double baseSalary = 8 * hourlyRate;
        this.salary = baseSalary - deduction + addition;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Employee: " + getName() + ", Position: " + position + ", Hourly Rate: " + hourlyRate);
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Position: " + position + "\n" +
                "Hourly Rate: " + hourlyRate + "\n" +
                "Current Salary: " + salary;
    }
}

