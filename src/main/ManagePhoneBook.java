package main;

import contact.Contact;
import contact.PhoneBookManager;
import read_write_file.ReadFile;

import java.io.IOException;
import java.util.List;

public class ManagePhoneBook {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "src/file/phoneBook.txt";
        PhoneBookManager phoneBookManager = new PhoneBookManager(filePath);

        Menu menu = new Menu();
        menu.mainMenu(phoneBookManager);
    }

}