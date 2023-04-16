package contact;

import read_write_file.ReadFile;
import read_write_file.WriteFile;
import superClass_interface.IPhone;
import superClass_interface.Phone;

import java.io.IOException;
import java.util.*;

public class PhoneBookManager extends Phone implements IPhone {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private String filePath;

    private WriteFile writeFile;
    private ReadFile readFile;

    public PhoneBookManager(String filePath) {
        this.filePath = filePath;
        writeFile = new WriteFile(filePath);
        readFile = new ReadFile(filePath);
        readFile();
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public void writeToFile(){
        writeFile.write(contactList);
    }

    public void readFile() {
        contactList = (ArrayList<Contact>) readFile.read();
    }

    public void display(String typeName) throws IOException, ClassNotFoundException {
        contactList = (ArrayList<Contact>) readFile.read();
        if (typeName.equalsIgnoreCase("")) contactList.forEach(System.out::println);
        else contactList.stream().filter(contact -> contact.getType().getName().equalsIgnoreCase(typeName)).forEach(System.out::println);
    }

    @Override
    public void display(Type type) throws IOException, ClassNotFoundException {
    }

    @Override
    public void insertPhone(Contact contact) throws IOException {
        contactList.add(contact);
        writeToFile();
    }

    public void insertPhone() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Thêm liên lạc mới");
        System.out.println("Nhập tên liên hệ: ");
        String name = scanner.nextLine();

        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();

        Type type = getInputType(scanner);

        addOrUpdateContact(name, phoneNumber, type);

        writeToFile();
    }

    public Type getInputType(Scanner scanner) {

        HashSet<Type> availableTypes = getTypeList();
        Type selectedType = null;

        while (selectedType == null) {
            System.out.println("Danh sách các loại liên hệ đã có: ");
            availableTypes.forEach(type -> System.out.println(type.getName()));

            System.out.println("Nhập tên loại liên hệ đã có hoặc nhập tên mới: ");
            String typeInput = scanner.nextLine();

            Optional<Type> optionalType = availableTypes.stream()
                    .filter(type -> type.getName().equalsIgnoreCase(typeInput))
                    .findFirst();

            if (optionalType.isPresent()) {
                selectedType = optionalType.get();
            }
            else {
                Type newType = new Type();
                newType.setName(typeInput);

                selectedType = newType;
            }
        }

        return selectedType;
    }

    public HashSet<Type> getTypeList(){
        HashSet<Type> typeList = new HashSet<>();
        contactList.forEach(contact -> typeList.add(contact.getType()));
        return typeList;
    }

    public void addOrUpdateContact(String name, String phoneNumber, Type selectedType) throws IOException {

        Optional<Contact> existingContact = contactList.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .findFirst();

        if (existingContact.isPresent()) {

            Contact contactToUpdate = existingContact.get();

            contactToUpdate.setPhoneNumber(phoneNumber);
            contactToUpdate.setType(selectedType);

            System.out.println("Đã cập nhật liên hệ '" + name + "' với số điện thoại mới và loại liên hệ '" + selectedType.getName() + "'");

        }

        else {
            Contact newContact = new Contact(name, phoneNumber, selectedType);

            contactList.add(newContact);

            System.out.println("Đã thêm mới liên hệ '" + name + "' với số điện thoại '" + phoneNumber + "' và loại liên hệ '" + selectedType.getName() + "'");
        }
    }

    @Override
    public void removePhone(String name) {

        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contactList.remove(contact);
                System.out.println("Đã xóa thành công Contact có tên " + name);
                return;
            }
        }

        System.out.println("Không tìm thấy Contact có tên " + name);
    }

    @Override
    public void updatePhone(String name, String newPhone) {

        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setPhoneNumber(newPhone);
                System.out.println("Đã cập nhật số điện thoại của " + name + " thành công. Số điện thoại mới: " + newPhone);
                return;
            }
        }

        System.out.println("Không tìm thấy Contact có tên " + name);
    }


    @Override
    public void searchPhone(String name) {

        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
                return;
            }
        }

        System.out.println("Không tìm thấy Contact có tên " + name);
    }

    @Override
    public void sort() {
        contactList.sort(Comparator.comparing(Contact::getName));
        System.out.println("Danh sách liên hệ đã được sắp xếp theo tên");
        writeToFile();
    }
}
