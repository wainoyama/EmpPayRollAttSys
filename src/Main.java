import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Employee> employees = new ArrayList<>();
    private static Admin currentAdmin;
    private static final double USD_TO_PHP_RATE = 50.0;
    private static AttendanceSystem attendanceSystem = new AttendanceSystem();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Employee Attendance and Payroll System");
            System.out.println("1. Employee Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Add New Employee");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (mainChoice) {
                case 1:
                    employeeLogin(scanner);
                    break;
                case 2:
                    adminLogin(scanner);
                    break;
                case 3:
                    addNewEmployee(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void initializeData() {
        currentAdmin = new Admin("admin", "batabata");
        PayrollSettings settings = new PayrollSettings(1.5, 2.0);
        currentAdmin.setPayrollSettings(settings);
    }

    private static void employeeLogin(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Employee loggedInEmployee = attendanceSystem.authenticate(username, password);

        if (loggedInEmployee != null) {
            System.out.println("Login successful!");
            boolean loggedIn = true;

            while (loggedIn) {
                System.out.println("\n1. Check In");
                System.out.println("2. Check Out");
                System.out.println("3. View Payroll");
                System.out.println("4. Logout");
                System.out.print("Select an option: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (userChoice) {
                    case 1:
                        attendanceSystem.checkIn(username);
                        break;
                    case 2:
                        attendanceSystem.checkOut(username);
                        break;
                    case 3:
                        attendanceSystem.viewPayroll(username);
                        break;
                    case 4:
                        loggedIn = false;
                        loggedInEmployee.logout();
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String inputPassword = scanner.nextLine();

        if (currentAdmin.getUsername().equals(inputUsername) && currentAdmin.validatePassword(inputPassword)) {
            System.out.println("Admin login successful!");
            adminDashboard(scanner);
        } else {
            System.out.println("Invalid admin username or password.");
        }
    }

    private static void adminDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. View all employees");
            System.out.println("2. Update employee information");
            System.out.println("3. Add employee");
            System.out.println("4. Remove employee");
            System.out.println("5. Calculate salary");
            System.out.println("6. Return to main menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllEmployees();
                    break;
                case 2:
                    updateEmployeeInfo(scanner);
                    break;
                case 3:
                    addEmployee(scanner);
                    break;
                case 4:
                    removeEmployee(scanner);
                    break;
                case 5:
                    calculateSalary(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewAllEmployees() {
        for (Employee emp : employees) {
            System.out.println(emp);
            System.out.println("--------------------");
        }
    }

    private static void updateEmployeeInfo(Scanner scanner) {
        System.out.print("Enter employee name to update: ");
        String name = scanner.nextLine();

        Employee empToUpdate = findEmployeeByName(name);
        if (empToUpdate == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new position (or press enter to skip): ");
        String newPosition = scanner.nextLine();
        if (!newPosition.isEmpty()) {
            empToUpdate.setPosition(newPosition);
        }

        System.out.print("Enter new salary (or press enter to skip): ");
        String newSalaryStr = scanner.nextLine();
        if (!newSalaryStr.isEmpty()) {
            double newSalary = Double.parseDouble(newSalaryStr);
            empToUpdate.setSalary(newSalary);
        }

        System.out.println("Employee information updated successfully.");
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();
        System.out.print("Enter employee hourly rate: ");
        double hourlyRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Employee newEmployee = new Employee(name, position, hourlyRate);
        employees.add(newEmployee);
        System.out.println("Employee added successfully.");
    }

    private static void addNewEmployee(Scanner scanner) {
        System.out.print("Enter new employee username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new employee password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();
        System.out.print("Enter employee hourly rate: ");
        double hourlyRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        attendanceSystem.addEmployee(newUsername, newPassword);
        Employee newEmployee = new Employee(newUsername, position, hourlyRate);
        newEmployee.setPassword(newPassword);
        employees.add(newEmployee);
        System.out.println("Employee added successfully to both systems.");
    }

    private static void removeEmployee(Scanner scanner) {
        System.out.print("Enter employee name to remove: ");
        String name = scanner.nextLine();

        Employee empToRemove = findEmployeeByName(name);
        if (empToRemove != null) {
            employees.remove(empToRemove);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void calculateSalary(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        Employee emp = findEmployeeByName(name);
        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter minutes late: ");
        int minutesLate = scanner.nextInt();
        System.out.print("Enter minutes overtime: ");
        int minutesOvertime = scanner.nextInt();

        emp.calculateSalary(minutesLate, minutesOvertime, currentAdmin.getPayrollSettings());
        System.out.printf("Final Salary for %s: %.2f PHP%n", emp.getName(), emp.getSalary());
    }

    private static Employee findEmployeeByName(String name) {
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                return emp;
            }
        }
        return null;
    }
}

