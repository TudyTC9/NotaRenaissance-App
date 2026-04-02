

public class Angajat {
    private String nume;
    private String prenume;
    private String idnp;
    private String adresa;
    private String nrTelefon; 
    private String servicii;

    public String getAdresa() {
        return adresa;
    }

    public String getIdnp() {
        return idnp;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getServicii() {
        return servicii;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setServicii(String servicii) {
        this.servicii = servicii;
    }

    public Angajat(String nume, String prenume, String idnp, String adresa, String nrTelefon, String servicii) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.servicii = servicii;
    }

}
