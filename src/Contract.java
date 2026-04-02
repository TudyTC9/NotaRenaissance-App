

public class Contract {
    private String nrInregistrare;
    private String clientNume;
    private String clientPrenume;
    private String notarNume;
    private String notarPrenume;
    private String angajatNume;
    private String angajatPrenume;
    private String dataIntocmire;
    private String dataValabilitate;
    private double valoare;

    public Contract(String nrInregistrare, String clientNume, String clientPrenume, String notarNume, String notarPrenume, String angajatNume, String angajatPrenume, String dataIntocmire, String dataValabilitate, double valoare) {
        this.nrInregistrare = nrInregistrare;
        this.clientNume = clientNume;
        this.clientPrenume = clientPrenume;
        this.notarNume = notarNume;
        this.notarPrenume = notarPrenume;
        this.angajatNume = angajatNume;
        this.angajatPrenume = angajatPrenume;
        this.dataIntocmire = dataIntocmire;
        this.dataValabilitate = dataValabilitate;
        this.valoare = valoare;
    }

    public String getAngajatNume() {
        return angajatNume;
    }

    public String getAngajatPrenume() {
        return angajatPrenume;
    }

    public String getClientNume() {
        return clientNume;
    }

    public String getClientPrenume() {
        return clientPrenume;
    }

    public String getDataIntocmire() {
        return dataIntocmire;
    }

    public String getDataValabilitate() {
        return dataValabilitate;
    }

    public String getNotarNume() {
        return notarNume;
    }

    public String getNotarPrenume() {
        return notarPrenume;
    }

    public String getNrInregistrare() {
        return nrInregistrare;
    }

    public double getValoare() {
        return valoare;
    }
    

    public void setAngajatNume(String angajatNume) {
        this.angajatNume = angajatNume;
    }

    public void setAngajatPrenume(String angajatPrenume) {
        this.angajatPrenume = angajatPrenume;
    }

    public void setClientNume(String clientNume) {
        this.clientNume = clientNume;
    }

    public void setClientPrenume(String clientPrenume) {
        this.clientPrenume = clientPrenume;
    }

    public void setDataIntocmire(String dataIntocmire) {
        this.dataIntocmire = dataIntocmire;
    }

    public void setDataValabilitate(String dataValabilitate) {
        this.dataValabilitate = dataValabilitate;
    }

    public void setNotarNume(String notarNume) {
        this.notarNume = notarNume;
    }

    public void setNotarPrenume(String notarPrenume) {
        this.notarPrenume = notarPrenume;
    }

    public void setNrInregistrare(String nrInregistrare) {
        this.nrInregistrare = nrInregistrare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }
    
    
}
