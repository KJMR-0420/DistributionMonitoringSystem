package HelperClass;

public class pwdhelper {
    String pwdname,pwdaddress,pwdcontact,pwdphoto;
    String imageUrl;
    public pwdhelper() {
    }

    public pwdhelper(String fname , String faddress, String fcontact, String furl){

    }
    public pwdhelper(String pwdname, String pwdaddress, String pwdcontact, String pwdphoto, String imageUrl) {
        this.pwdname = pwdname;
        this.pwdaddress = pwdaddress;
        this.pwdcontact = pwdcontact;
        this.pwdphoto = pwdphoto;
        this.imageUrl = imageUrl;
    }


    public String getPwdname() {
        return pwdname;
    }

    public void setPwdname(String pwdname) {
        this.pwdname = pwdname;
    }

    public String getPwdaddress() {
        return pwdaddress;
    }

    public void setPwdaddress(String pwdaddress) {
        this.pwdaddress = pwdaddress;
    }

    public String getPwdcontact() {
        return pwdcontact;
    }

    public void setPwdcontact(String pwdcontact) {
        this.pwdcontact = pwdcontact;
    }

    public String getPwdphoto() {
        return pwdphoto;
    }

    public void setPwdphoto(String pwdphoto) {
        this.pwdphoto = pwdphoto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
