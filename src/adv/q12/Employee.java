package adv.q12;

import java.io.*;
import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
    }

    int empId, empAge;
    String empName;

    Employee(int empId, String empName, int empAge) {

        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId=" + empId +
                ", EmployeeAge=" + empAge +
                ", EmployeeName='" + empName + '\'' +
                '}';
    }

    static {
        BufferedReader br1, br2;
        FileReader file, file1;
        String line;

        int l_No = 0;
        String[] data;
        Employee[] employee = null;
        Scanner sc = new Scanner(System.in);

        try {
            file1 = new FileReader("src/adv/q12/EmpOutPut.txt");
            br1 = new BufferedReader(file1);

            int x = 0;
            while ((line = br1.readLine()) != null) {
                data = line.split(",");
                employee[x] = new Employee(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]));
                x++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int in;
        do {
            assert employee != null;
            for (Employee emp : employee) {
                System.out.println(emp);
            }
            System.out.println("_______________ Enter Your Choice ___________________" + "Edit: Press 1 for editing" + "Exit: Press 2 for Exit ");
            in = sc.nextInt();
            if (in == 1) {
                System.out.println("Editing enabling...");
                Employee[] employees = editEmployee(employee);
//
                updateFile(employees);
            }
        } while (in != 2);
        System.out.println("BYE BYE");
    }


    static Employee[] editEmployee(Employee[] employees) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id");
        int employeeID = sc.nextInt();
        for (Employee employee : employees) {
            if (employee.empId == employeeID) {
                System.out.println("Enter Name");
                String editName = sc.next();
                System.out.println("Enter Age");
                int editAge = sc.nextInt();
                employee.empName = editName;
                employee.empAge = editAge;
            }
        }
        return employees;
    }

    static void updateFile(Employee[] employees) {
        FileWriter writer = null;
        File fileToBeModified = new File("src/com/que12/EmpOutPut.txt");
        StringBuilder newContent = new StringBuilder();
        try {
            for (Employee emp : employees) {
                newContent.append(emp.empId).append(",").append(emp.empName).append(",").append(emp.empAge).append(System.lineSeparator());
            }
            System.out.println(newContent);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent.toString());
            System.out.println("The File has been updated successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
