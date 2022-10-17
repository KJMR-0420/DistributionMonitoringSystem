package HelperClass;

public class vaccinehelper {
    String vaccinename,vaccineaddress,vaccinecontact,vaccinephoto;
    String imageUrl;
    public vaccinehelper() {

    }
public vaccinehelper(String fname, String faddress, String fcontact, String furl){

}
    public vaccinehelper(String vaccinename, String vaccineaddress, String vaccinecontact, String vaccinephoto, String imageUrl) {
        this.vaccinename = vaccinename;
        this.vaccineaddress = vaccineaddress;
        this.vaccinecontact = vaccinecontact;
        this.vaccinephoto = vaccinephoto;
        this.imageUrl = imageUrl;
    }

    public String getVaccinename() {
        return vaccinename;
    }

    public void setVaccinename(String vaccinename) {
        this.vaccinename = vaccinename;
    }

    public String getVaccineaddress() {
        return vaccineaddress;
    }

    public void setVaccineaddress(String vaccineaddress) {
        this.vaccineaddress = vaccineaddress;
    }

    public String getVaccinecontact() {
        return vaccinecontact;
    }

    public void setVaccinecontact(String vaccinecontact) {
        this.vaccinecontact = vaccinecontact;
    }

    public String getVaccinephoto() {
        return vaccinephoto;
    }

    public void setVaccinephoto(String vaccinephoto) {
        this.vaccinephoto = vaccinephoto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
