
package serviciosbancarios;

import java.util.Date;

public class Transaccion {
    
    
    private double montoTrans;
    private String NumCuentaTrans;
    private String tipoTrans;
    private Date fechaTrans;
    private String cedUsTrans;

    public Transaccion(double montoTrans, String NumCuentaTrans, String tipoTrans, Date fechaTrans, String cedUsTrans) {
        this.montoTrans = montoTrans;
        this.NumCuentaTrans = NumCuentaTrans;
        this.tipoTrans = tipoTrans;
        this.fechaTrans = fechaTrans;
        this.cedUsTrans = cedUsTrans;
    }

    public Transaccion() {
    }

    public double getMontoTrans() {
        return montoTrans;
    }

    public void setMontoTrans(double montoTrans) {
        this.montoTrans = montoTrans;
    }

    public String getNumCuentaTrans() {
        return NumCuentaTrans;
    }

    public void setNumCuentaTrans(String NumCuentaTrans) {
        this.NumCuentaTrans = NumCuentaTrans;
    }

    public String getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(String tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public Date getFechaTrans() {
        return fechaTrans;
    }

    public void setFechaTrans(Date fechaTrans) {
        this.fechaTrans = fechaTrans;
    }

    public String getCedUsTrans() {
        return cedUsTrans;
    }

    public void setCedUsTrans(String cedUsTrans) {
        this.cedUsTrans = cedUsTrans;
    }
    
}
