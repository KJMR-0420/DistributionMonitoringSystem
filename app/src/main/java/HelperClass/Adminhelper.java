package HelperClass;

public class Adminhelper {
    String adminemail, adminpassword;

    public Adminhelper() {

    }

    public Adminhelper(String adminemail, String adminpassword) {
        this.adminemail = adminemail;
        this.adminpassword = adminpassword;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
}
