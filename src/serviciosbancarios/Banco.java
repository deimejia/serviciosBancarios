package serviciosbancarios;

public class Banco {

    private String numeroBanco;
    private String nombreB;
    private double montoMax;
    private double montoMin;
    private double cobroTrans;
    private double interesAnual;

    public Banco(String numeroBanco, String nombreB, double montoMax, double montoMin, double cobroTrans, double interesAnual) {
        this.numeroBanco = numeroBanco;
        this.nombreB = nombreB;
        this.montoMax = montoMax;
        this.montoMin = montoMin;
        this.cobroTrans = cobroTrans;
        this.interesAnual = interesAnual;
    }

    public Banco() {
    }

    public String getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(String numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public String getNombreB() {
        return nombreB;
    }

    public void setNombreB(String nombreB) {
        this.nombreB = nombreB;
    }

    public double getMontoMax() {
        return montoMax;
    }

    public void setMontoMax(double montoMax) {
        this.montoMax = montoMax;
    }

    public double getMontoMin() {
        return montoMin;
    }

    public void setMontoMin(double montoMin) {
        this.montoMin = montoMin;
    }

    public double getCobroTrans() {
        return cobroTrans;
    }

    public void setCobroTrans(double cobroTrans) {
        this.cobroTrans = cobroTrans;
    }

    public double getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(double interesAnual) {
        this.interesAnual = interesAnual;
    }

    @Override
    public String toString() {
        return "\nNúmero Identificador Banco: " + numeroBanco + "\nNombre Banco: " + nombreB+"\n\n";
    }
    

}
