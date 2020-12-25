import java.util.*;

public class Solution690 {
    Employee[] employees2;

    public int getImportance(List<Employee> employees, int id) {
        initial(employees);
        return Search(id);
    }

    public void initial(List<Employee> employees) {
        Employee[] es = new Employee[2001];
        for (Employee employee : employees) {
            es[employee.id] = employee;
        }
        employees2 = es;
    }

    public int Search(int id) {
        Employee employee = employees2[id];
        int importance = 0;
        importance += employee.importance;
        for (int subordinateID : employees2[id].subordinates) {
            importance += Search(subordinateID);
        }
        return importance;
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    public static void main(String[] args) {
        System.out.println();
    }
}
