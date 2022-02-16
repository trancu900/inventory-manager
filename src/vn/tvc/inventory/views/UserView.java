package vn.tvc.inventory.views;


import vn.tvc.inventory.VariableGlobal;
import vn.tvc.inventory.model.Role;
import vn.tvc.inventory.model.User;
import vn.tvc.inventory.services.IUserService;
import vn.tvc.inventory.services.UserService;
import vn.tvc.inventory.utils.ValidateUtils;

import java.util.Scanner;

public class UserView {
    private static final IUserService userService = new UserService();
    private static final Scanner scanner = new Scanner(System.in);


    public static void run() {
        int option = -1;
        do {
            try {
                show();
                System.out.println("\nChọn chức năng ");
                System.out.print(" ⭆ ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        updateUser();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception e) {
                if (VariableGlobal.DEV_MODE)
                    e.printStackTrace();
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (option != 0);
    }

    private static void addUser() {
        System.out.println("Nhập thông tin người dùng");
        long id = System.currentTimeMillis() / 1000;
        String fullName = getFullName();
        String email = getEmail(1);
        String phone = existByPhone(1);
        Role role = getRole();
        User newUser = new User(id, fullName, phone, email, role);

        userService.add(newUser);
        System.out.println("Đã thêm thành công!\uD83C\uDF8A");
///        System.out.println(newUser);
    }

    private static String existByPhone(int flag) {
        String phone = getPhone(flag);
        while (userService.existByPhone(phone)) {
            System.out.println("Số điện thoại này đã tồn tại! vui lòng kiểm tra lại!");
            phone = getPhone(flag);
        }
        return phone;
    }

    public static String getFullName() {
        System.out.println("Nhập Ho va ten");
        System.out.print(" ⭆ ");
        return scanner.nextLine();
    }

    private static void updateUser() {
        long id = System.currentTimeMillis() / 1000;
        String fullName = scanner.nextLine();
        String email = getEmail(2);
        String phone = getPhone(2);


    }

    public static Role getRole() {
        Role role = null;
        do {
            int option = -1;
            try {
                System.out.println("= = SET ROLE = =");
                System.out.println("∥   1. USER    ∥");
                System.out.println("∥   2. ADMIN   ∥");
                System.out.println("= = = =  = = = = ");
                System.out.println("Chọn Role: ");
                System.out.print(" ⭆ ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        role = Role.USER;
                        break;
                    case 2:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.out.println("Nhập không đúng! Vui lòng nhập lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }

        }
        while (role == null);
        return role;
    }

    private static String getEmail(int flag) {
        if (flag == 1)
            System.out.println("Nhập email (vd: thuan@gmail.com)");
        else
            System.out.println("Nhập email mà bạn muốn đổi (vd: thuan@gmail.com)");
        System.out.print(" ⭆ ");
        String email = scanner.nextLine();
        while (!ValidateUtils.isEmailValid(email)) {
            System.out.println("Email" + email + "của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
            System.out.println("Nhập email (vd: thuan@gmail.com)");
            System.out.print(" ⭆ ");
            email = scanner.nextLine();
        }
        return email;
    }

    private static String getPhone(int flag) {
        if (flag == 1)
            System.out.println("Nhập số điện thoại (vd: 0345129876): ");
        else
            System.out.println("Nhập số điện thoại mà bạn muốn đổi (vd: 0345129876):");
        System.out.print(" ⭆ ");
        String phone = scanner.nextLine();
        while (!ValidateUtils.isPhoneValid(phone)) {
            System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
            System.out.println("Nhập số điện thoại (vd: 0345129876)");
            System.out.print(" ⭆ ");
            phone = scanner.nextLine();
        }
        return phone;
    }

    public static void show() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪  USERS MANAGER  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪     1. Thêm người dùng               ⚪");
        System.out.println("⚪     2. Sửa thông tin người dùng      ⚪");
        System.out.println("⚪     3. Hiện danh sách người dùng     ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }


}
