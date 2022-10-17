package HelperClass;

public class freehelper {
    String milkparentsname,milkchildname,milkchildage,milkaddress,milkbrand,milkcontact;
    String status;

    public freehelper() {

    }

    public freehelper(String milkparentsname, String milkchildname, String milkchildage, String milkaddress, String milkbrand, String milkcontact, String status) {
        this.milkparentsname = milkparentsname;
        this.milkchildname = milkchildname;
        this.milkchildage = milkchildage;
        this.milkaddress = milkaddress;
        this.milkbrand = milkbrand;
        this.milkcontact = milkcontact;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMilkparentsname() {
        return milkparentsname;
    }

    public void setMilkparentsname(String milkparentsname) {
        this.milkparentsname = milkparentsname;
    }

    public String getMilkchildname() {
        return milkchildname;
    }

    public void setMilkchildname(String milkchildname) {
        this.milkchildname = milkchildname;
    }

    public String getMilkchildage() {
        return milkchildage;
    }

    public void setMilkchildage(String milkchildage) {
        this.milkchildage = milkchildage;
    }

    public String getMilkaddress() {
        return milkaddress;
    }

    public void setMilkaddress(String milkaddress) {
        this.milkaddress = milkaddress;
    }

    public String getMilkbrand() {
        return milkbrand;
    }

    public void setMilkbrand(String milkbrand) {
        this.milkbrand = milkbrand;
    }

    public String getMilkcontact() {
        return milkcontact;
    }

    public void setMilkcontact(String milkcontact) {
        this.milkcontact = milkcontact;
    }
}
