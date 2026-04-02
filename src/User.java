
public class User {
    private String status;
    private String username;
    private String password;
    private String nume;
    private String prenume;
    private String nrTelefon;
    private String adresa;

    public String getStatus(){
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public User(String status, String username, String password, String nume, String prenume, String nrTelefon, String adresa) {
        this.status = status;
        this.username = username;
        this.password = password;
        this.nume = nume;
        this.prenume = prenume;
        this.nrTelefon = nrTelefon;
        this.adresa = adresa;
    }

    

}