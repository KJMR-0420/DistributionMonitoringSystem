package HelperClass;

public class seniorhelper {
    String seniorname, senioraddress,seniorcontact,seniorphoto;
    String imageUrl;
    public seniorhelper() {

    }

    public seniorhelper(String fname, String faddress, String fcontact, String furl){

    }

    public seniorhelper(String seniorname, String senioraddress, String seniorcontact, String seniorphoto, String imageUrl) {
        this.seniorname = seniorname;
        this.senioraddress = senioraddress;
        this.seniorcontact = seniorcontact;
        this.seniorphoto = seniorphoto;
        this.imageUrl = imageUrl;
    }

    public String getSeniorname() {
        return seniorname;
    }

    public void setSeniorname(String seniorname) {
        this.seniorname = seniorname;
    }

    public String getSenioraddress() {
        return senioraddress;
    }

    public void setSenioraddress(String senioraddress) {
        this.senioraddress = senioraddress;
    }

    public String getSeniorcontact() {
        return seniorcontact;
    }

    public void setSeniorcontact(String seniorcontact) {
        this.seniorcontact = seniorcontact;
    }

    public String getSeniorphoto() {
        return seniorphoto;
    }

    public void setSeniorphoto(String seniorphoto) {
        this.seniorphoto = seniorphoto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
