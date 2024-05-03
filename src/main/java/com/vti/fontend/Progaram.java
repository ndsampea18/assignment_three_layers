package com.vti.fontend;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.vti.backend.presentationlayer.UserController;
import com.vti.backend.utils.ScannerUtils;
import com.vti.entity.Users;

public class Progaram {
    public static void main(String[] args) throws SQLException {
        int choice = -1;
        boolean exitProgram = false;
        while (!exitProgram) {
            menu();
            choice = ScannerUtils.inputInt("");
            UserController usController = new UserController();
            switch (choice) {
                case 1:
                    System.out.println("Register account");
                    System.out.print("\tEnter username: ");
                    String username = ScannerUtils.inputString("You cannot leave it blank");
                    System.out.print("\tEnter password: ");
                    String password = ScannerUtils.inputString("You cannot leave it blank");
                    System.out.print("\tEnter email: ");
                    String email = ScannerUtils.inputString("You cannot leave it blank");
                    System.out.print("\tEnter phone: ");
                    String phone = ScannerUtils.inputString("You cannot leave it blank");

                    try {
                        if (usController.registerUser(username, password, email, phone)) {
                            System.out.println("Register successful");
                        } else {
                            System.out.println("Register fail");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Register fail");
                    }
                    break;
                case 2:
                    System.out.println("Login");
                    System.out.print("\tEnter username: ");
                    String username1 = ScannerUtils.inputString("You cannot leave it blank");
                    System.out.print("\tEnter password: ");
                    String password1 = ScannerUtils.inputString("You cannot leave it blank");

                    try {
                        if (usController.login(username1, password1)) {
                            System.out.println("Login successful");
                        } else {
                            System.out.println("Login fail");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Login fail");
                    }
                    break;
                case 3:
                    System.out.println("List All Users");
                    List<Users> usersList = usController.getAllUsers();
                    for (Users user : usersList) {
                        System.out.println(user);
                    }
                case 4:
                    System.out.println("User Detail");
                    System.out.print("\tEnter user id: ");
                    String id = ScannerUtils.inputString("You cannot leave it blank");
                    Optional<Users> optionalUsers = usController.getUserDetail(Integer.parseInt(id));

                    if (optionalUsers.isPresent()) {
                        System.out.println(optionalUsers.get());
                    } else {
                        System.out.println("ID is not correct");
                    }
                case 5:
                    System.out.println("Exited program!");
                    exitProgram = true;
					break;
                default:
                    System.out.println("Mời bạn nhập lại trong khoảng 1 - 5");
            }
        }
    }

    public static void menu() {
        System.out.println("\n=================================Manage User=================================");
        String leftAlignFormat = "| %-72s |%n";
        System.out.format("+--------------------------------------------------------------------------+%n");
        System.out.format("|                               Please choose                               |%n");
        System.out.format("+--------------------------------------------------------------------------+%n");
        System.out.format(leftAlignFormat, "1. Register");
        System.out.format(leftAlignFormat, "2. Login");
        System.out.format(leftAlignFormat, "3. All Users");
        System.out.format(leftAlignFormat, "4. User Detail");
        System.out.format(leftAlignFormat, "5. Exit");
        System.out.format("+--------------------------------------------------------------------------+%n");
    }
}
