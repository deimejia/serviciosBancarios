package serviciosbancarios;

public class Persona {

    private String nombreP;
    private String apellido;
    private String numCedula;
    private String profesion;
    private int numCuenta;

    public Persona(String nombreP, String apellido, String numCedula, String profesion, int numCuenta) {
        this.nombreP = nombreP;
        this.apellido = apellido;
        this.numCedula = numCedula;
        this.profesion = profesion;
        this.numCuenta = numCuenta;
    }

    public Persona() {
    }
    
    
    public void ajustarSaldoCuenta(){}
    
    
    

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumCedula() {
        return numCedula;
    }

    public void setNumCedula(String numCedula) {
        this.numCedula = numCedula;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

}
