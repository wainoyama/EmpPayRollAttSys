import java.util.HashMap;

public class AttendanceSystem {
    private HashMap<String, Employee> employees;

    public AttendanceSystem() {
        employees = new HashMap<>();
    }

    public void addEmployee(String username, String password) {
        if (!employees.containsKey(username)) {
            Employee newEmployee = new Employee(username, "", 0);
            newEmployee.setPassword(password);
            employees.put(username, newEmployee);
            System.out.println("Employee added successfully to AttendanceSystem. Username: " + username);
        } else {
            System.out.println("Employee with this username already exists in AttendanceSystem.");
        }
    }

    public Employee authenticate(String username, String password) {
        Employee employee = employees.get(username);
        if (employee != null && employee.validatePassword(password)) {
            employee.login();
            return employee;
        }
        return null;
    }

    public void checkIn(String username) {
        System.out.println(username + " has checked in.");
    }

    public void checkOut(String username) {
        System.out.println(username + " has checked out.");
    }

    public void viewPayroll(String username) {
        System.out.println("Viewing payroll data for " + username);
    }
}

