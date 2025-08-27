import java.util.*;

public class User {
    private String[] users = new String[10]; 
    private int userCount = 0;
    private Scanner scanner;

    public User() {
        scanner = new Scanner(System.in);
        users[userCount++] = "admin:admin123,admin";
    }

    public boolean validateUser(String username, String password, boolean[] isAdmin) {
        String userData = username + ":" + password + ",";
        for (int i = 0; i < userCount; i++) {
            if (users[i].startsWith(userData)) {
                isAdmin[0] = users[i].endsWith("admin");
                return true;
            }
        }
        return false;
    }

    public void createUser() {
        if (userCount >= users.length) {
            System.out.println("User limit reached.");
            return;
        }
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users[userCount++] = username + ":" + password + ",user";
        System.out.println("User created.");
    }
}