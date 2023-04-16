package main;

import contact.PhoneBookManager;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void returnOrExit() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print("Nhập '0' để quay lại hoặc 'Exit' để thoát hoàn toàn chương trình: ");
            input = scanner.nextLine();

            switch (input) {
                case "0" -> {return;}
                case "Exit" -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
            }
        } while (true);
    }

    public void mainMenu(PhoneBookManager phoneBookManager) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMainMenu();
            try {
                System.out.println("Nhập lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> displayAllContacts(phoneBookManager);
                    case 2 -> displayByType(phoneBookManager);
                    case 3 -> searchContact(phoneBookManager);
                    case 4 -> sortByName(phoneBookManager);
                    case 5 -> addNewContact(phoneBookManager);
                    case 6 -> updateContactInfo(phoneBookManager);
                    case 7 -> deleteContact(phoneBookManager);
                    case 0 -> {return;}
                    default -> System.out.println("Lựa chọn không hợp lệ!");

                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    private void deleteContact(PhoneBookManager phoneBookManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên liên lạc cần xóa: ");
        String name = scanner.nextLine();

        phoneBookManager.removePhone(name);
        returnOrExit();
    }

    private void updateContactInfo(PhoneBookManager phoneBookManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên liên lạc cần cập nhật: ");
        String name = scanner.nextLine();
        System.out.println("Nhập số điện thoại mới cần cập nhật: ");
        String newPhoneNumber = scanner.nextLine();

        phoneBookManager.updatePhone(name, newPhoneNumber);
        returnOrExit();
    }

    private void addNewContact(PhoneBookManager phoneBookManager) throws IOException {
        phoneBookManager.insertPhone();
        returnOrExit();
    }

    private void sortByName(PhoneBookManager phoneBookManager) throws IOException, ClassNotFoundException {
        phoneBookManager.sort();
        phoneBookManager.display("");
        returnOrExit();
    }


    private void searchContact(PhoneBookManager phoneBookManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên liên lạc cần tìm kiếm: ");
        String name = scanner.nextLine();
        phoneBookManager.searchPhone(name);
        returnOrExit();
    }

    public void displayMainMenu(){
        System.out.println("QUẢN LÝ DANH BẠ");
        System.out.println("1. Hiển thị toàn bộ danh bạ");
        System.out.println("2. Hiển thị danh bạ theo type");
        System.out.println("3. Tìm kiếm liên lạc");
        System.out.println("4. Sắp xếp theo tên");
        System.out.println("5. Thêm liên lạc mới");
        System.out.println("6. Sửa thông tin");
        System.out.println("7. Xóa liên lạc");
        System.out.println("0. Thoát");
    }

    private void displayByType(PhoneBookManager phoneBookManager) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh sách các loại liên hệ đã có: ");
        phoneBookManager.getTypeList().forEach(type -> System.out.println(type.getName()));
        System.out.println("Nhập loại liên hệ cần tìm kiếm: ");
        String type = scanner.nextLine();
        phoneBookManager.display(type);
        returnOrExit();
    }

    public void displayAllContacts(PhoneBookManager phoneBookManager) throws IOException, ClassNotFoundException {
        phoneBookManager.display("");
        returnOrExit();
    }



}
