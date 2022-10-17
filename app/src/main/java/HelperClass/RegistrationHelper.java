package HelperClass;

public class RegistrationHelper {
    String name,address,email,contact,password,conpassword;

    public RegistrationHelper() {

    }
    public RegistrationHelper(String name, String address, String email, String contact, String password, String conpassword) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.conpassword = conpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }
}
