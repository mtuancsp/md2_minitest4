package superClass_interface;

import contact.Contact;
import contact.Type;

import java.io.IOException;

public abstract class Phone {
    public abstract void display(Type type) throws IOException, ClassNotFoundException;
    public abstract void insertPhone(Contact contact) throws IOException;
    public abstract void removePhone(String name);
    public abstract void updatePhone(String name, String newPhone);
}
