package serviciosbancarios;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta extends Banco {

    private int numeroCuenta;
    private String cedDuenoCuenta;
    private String tipoCuenta;
    private Double saldo;
    private Date fechaApertura;
    private Persona titular;

    
    
    
    public Cuenta(int numeroCuenta, String cedDuenoCuenta, String tipoCuenta, Double saldo, Date fechaApertura, Persona titular,
            String numeroBanco, String nombreB, double montoMax, double montoMin, double cobroTrans, double interesAnual) 
    
    {
        super(numeroBanco, nombreB, montoMax, montoMin, cobroTrans, interesAnual);
        
        this.numeroCuenta = numeroCuenta;
        this.cedDuenoCuenta = cedDuenoCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.titular = titular;
    }

    
    
    
    
    
    
    public Cuenta() {
        
        super();
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCedDuenoCuenta() {
        return cedDuenoCuenta;
    }

    public void setCedDuenoCuenta(String cedDuenoCuenta) {
        this.cedDuenoCuenta = cedDuenoCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return "INFORMACIÓN DE LA CUENTA: \n" +"BANCO: "+getNombreB()+ " numeroCuenta= " + numeroCuenta + ", cedDuenoCuenta= " + cedDuenoCuenta + ", \ntipoCuenta= " + tipoCuenta + ", saldo= " + saldo + ", fechaApertura= " + formato.format(fechaApertura) ;
    }

}
