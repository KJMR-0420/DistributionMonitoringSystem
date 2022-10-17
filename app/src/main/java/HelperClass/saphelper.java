package HelperClass;

public class saphelper {
    String sapname,sapaddress,sapcontact,sapoccupation,sapsalary,sapphoto;
    String imageUrl;
    public saphelper() {
    }
    public saphelper(String sapname, String sapaddress, String sapcontact, String sapoccupation, String sapsalary, String sapphoto, String imageUrl) {
        this.sapname = sapname;
        this.sapaddress = sapaddress;
        this.sapcontact = sapcontact;
        this.sapoccupation = sapoccupation;
        this.sapsalary = sapsalary;
        this.sapphoto = sapphoto;
        this.imageUrl = imageUrl;
    }

    public saphelper(String fname, String faddress, String fcontact, String foccupation, String fsalary, String furl) {
    }

    public String getSapname() {
        return sapname;
    }

    public void setSapname(String sapname) {
        this.sapname = sapname;
    }

    public String getSapaddress() {
        return sapaddress;
    }

    public void setSapaddress(String sapaddress) {
        this.sapaddress = sapaddress;
    }

    public String getSapcontact() {
        return sapcontact;
    }

    public void setSapcontact(String sapcontact) {
        this.sapcontact = sapcontact;
    }

    public String getSapoccupation() {
        return sapoccupation;
    }

    public void setSapoccupation(String sapoccupation) {
        this.sapoccupation = sapoccupation;
    }

    public String getSapsalary() {
        return sapsalary;
    }

    public void setSapsalary(String sapsalary) {
        this.sapsalary = sapsalary;
    }

    public String getSapphoto() {
        return sapphoto;
    }

    public void setSapphoto(String sapphoto) {
        this.sapphoto = sapphoto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
