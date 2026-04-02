
public class Client {
    private String nume;
    private String prenume;
    private String idnp;
    private String adresa;
    private String nrTelefon;

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

    public Client(String nume, String prenume, String idnp, String adresa, String nrTelefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
    }

}
