
public class Filiala {
    private String nume, nrInregistrare, adresa, nrTelefon;

    public Filiala(String nume, String nrInregistrare, String adresa, String nrTelefon) {
        this.nume = nume;
        this.nrInregistrare = nrInregistrare;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
    }

    public String getNume() {
        return nume;
    }

    public String getNrInregistrare() {
        return nrInregistrare;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNrInregistrare(String nrInregistrare) {
        this.nrInregistrare = nrInregistrare;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    
}

