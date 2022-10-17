package HelperClass;

public class soloparenthelper {
    String soloname, soloaddress, solocontact,solophoto;
    String imageUrl;
    public soloparenthelper() {
    }

    public soloparenthelper(String fname ,String faddress, String fcontact, String furl){

    }

    public soloparenthelper(String soloname, String soloaddress, String solocontact, String solophoto, String imageUrl) {
        this.soloname = soloname;
        this.soloaddress = soloaddress;
        this.solocontact = solocontact;
        this.solophoto = solophoto;
        this.imageUrl = imageUrl;
    }

    public String getSoloname() {
        return soloname;
    }

    public void setSoloname(String soloname) {
        this.soloname = soloname;
    }

    public String getSoloaddress() {
        return soloaddress;
    }

    public void setSoloaddress(String soloaddress) {
        this.soloaddress = soloaddress;
    }

    public String getSolocontact() {
        return solocontact;
    }

    public void setSolocontact(String solocontact) {
        this.solocontact = solocontact;
    }

    public String getSolophoto() {
        return solophoto;
    }

    public void setSolophoto(String solophoto) {
        this.solophoto = solophoto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
