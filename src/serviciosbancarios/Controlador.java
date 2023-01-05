package serviciosbancarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Controlador {

    private List<Banco> listaBancos = new ArrayList<>();

    private List<Cuenta> listaCuentas = new ArrayList<>();
    private final Excepciones exceptuar = new Excepciones();
    private final Scanner datoIng = new Scanner(System.in);
    private final Cuenta metodoCuenta = new Cuenta();

    // private final Vistas metodoVistas = new Vistas(); ESTO PROVOCA RECURSIVIDAD
    public void EliminarBanco(Banco opciones) {
    }

    public void seleccionarBanco(Banco opciones) {

    }

    //
    //CREAR CUENTAS
    public void llenarDatosCuenta(int bancoElegido, List<Banco> listaBancos) {

        String nombreP, apellido, numCedula, profesion, tipoCuenta = "";
        int opcionCuenta = 0, numCuenta = 0;
        double saldoInicial = 0;
        String fechaAperturaS;
        Date fechaApertura = null;
        boolean bandera = true;

        System.out.println("\n\n\t Usted ha eligido crear un cuenta en el banco "
                + listaBancos.get(bancoElegido).getNombreB());

        System.out.println("\t Ingrese los datos del títular de la cuenta ");

        System.out.print("\n Número de cédula: ");
        numCedula = datoIng.next();

        System.out.print("\n Nombre: ");
        nombreP = datoIng.next();

        System.out.print("\n Apellido: ");
        apellido = datoIng.next();

        System.out.print("\n Profesión (si está desempleado por favor indicarlo): ");
        profesion = datoIng.next();

        boolean banderaInterna1 = true;
        while (bandera && banderaInterna1) {

            try {
                System.out.println("\n\t Ingrese el tipo de cuenta que desea\n");

                System.out.println("Opción 1 : AHORROS \nOpción 2 : CORRIENTE\nOpción 0: Salir y regresar al menú principal");

                System.out.print("\n\tDigite su número de opción preferida: ");
                opcionCuenta = datoIng.nextInt();

                switch (opcionCuenta) {
                    case 1: {
                        tipoCuenta = "AHORROS";
                        banderaInterna1 = false;

                        break;
                    }
                    case 2: {
                        tipoCuenta = "CORRIENTE";
                        banderaInterna1 = false;

                        break;
                    }
                    case 0: {

                        bandera = false;
                        break;
                    }

                    default:

                        JOptionPane.showMessageDialog(null, "OPCION NO VÁLIDA");
                        datoIng.nextLine();

                }

                exceptuar.validarTipoDeCuenta(listaBancos, listaCuentas, tipoCuenta, numCedula, bancoElegido);

            } catch (Exception e) {

                // if (opcionCuenta != 0) {
                if (e instanceof InputMismatchException) {

                    JOptionPane.showMessageDialog(null, "TIPO DE DE DATO NO VALIDO, POR FAVOR VOLVER A INGRESAR");
                    datoIng.nextLine();

                } else {

                    JOptionPane.showMessageDialog(null, e.getMessage());
                    bandera = false;
                    break;
                }

            }

        }

        while (bandera) {
            try {
                System.out.print("\n Ingrese el número de cuenta deseado (o ingresa 0 para salir): ");

                numCuenta = datoIng.nextInt();
                exceptuar.validarNumDeCuentaACrear(listaCuentas, numCedula, bancoElegido, numCuenta);
                if (numCuenta == 0) {
                    bandera = false;
                    break;
                }
                break;
                // bandera = false;
            } catch (Exception e) {

                if (e instanceof InputMismatchException) {
                    JOptionPane.showMessageDialog(null, "DATO INGRESADO ES INVALIDO\n\tVUELVA A INGRESARLO");
                    datoIng.nextLine();

                } else {

                    JOptionPane.showMessageDialog(null, e.getMessage());

                    bandera = false;
                    break;

                }

                //   System.out.println(bandera);
            }

        }

        while (bandera) {
            try {
                System.out.print("\n Ingrese el saldo inicial en su cuenta: ");
                saldoInicial = datoIng.nextDouble();
                exceptuar.validarSaldoIncial(saldoInicial);
                break;

            } catch (Exception e) {

                if (e instanceof InputMismatchException) {
                    JOptionPane.showMessageDialog(null, "DATO INGRESADO ES INVALIDO\n\tVUELVA A INGRESARLO");
                    datoIng.nextLine();
                } else {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    datoIng.nextLine();
                }

            }

        }

        while (bandera) {
            try {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                System.out.print("\n Fecha de apertura de la cuenta: ");
                fechaAperturaS = datoIng.next();

                fechaApertura = formato.parse(fechaAperturaS);
                break;
            } catch (Exception e) {

                if (e instanceof ParseException) {
                    JOptionPane.showMessageDialog(null, "formato de fecha debe ser el siguiente 31/05/2020 \n por favor vuelva a ingresarla");
                    datoIng.nextLine();

                } else {
                    JOptionPane.showMessageDialog(null, "DATO INGRESADO ES INVALIDO\n\tVUELVA A INGRESARLO");
                }

            }

        }

        while (bandera) {

            Persona cliente = new Persona(nombreP, apellido, numCedula, profesion, numCuenta);

            Cuenta cuentaNueva = new Cuenta(numCuenta, numCedula, tipoCuenta,
                    saldoInicial, fechaApertura, cliente, listaBancos.get(bancoElegido).getNumeroBanco(), listaBancos.get(bancoElegido).getNombreB(),
                    listaBancos.get(bancoElegido).getMontoMax(), listaBancos.get(bancoElegido).getMontoMin(), listaBancos.get(bancoElegido).getCobroTrans(),
                    listaBancos.get(bancoElegido).getInteresAnual());

            crearCuenta(cuentaNueva);
            bandera = false;

        }

    }

    public void crearCuenta(Cuenta cuentaCreada) {
        listaCuentas.add(cuentaCreada);

        System.out.println("\n\t CUENTA CREADA CON ÉXITO\n" + "\t" + cuentaCreada);
    }

    //
    //ELIMINAR CUENTAS
    public void eliminarCuenta(int numCuentaEliminar, String numCedIng) {
        int index = -1;

        for (Cuenta cuentaEliminar : listaCuentas) {

            if (cuentaEliminar.getNumeroCuenta() == (numCuentaEliminar)) {
                index = listaCuentas.indexOf(cuentaEliminar);
                break;

            }

        }

        listaCuentas.remove(index);
        System.out.println("\n\t CUENTA ELIMINADA CON ÉXITOOO \n");

        validarOtrasCuentasCorrientes(numCedIng, numCuentaEliminar);
        //  System.out.println("\n \n" + listaCuentas);

    }

    public void validarOtrasCuentasCorrientes(String numCedIng, int numCuentaIngEliminar) {

        for (Cuenta cuenta : listaCuentas) {

            if (cuenta.getNumeroCuenta() == numCuentaIngEliminar && cuenta.getTipoCuenta().equals("CORRIENTE")) {

                for (Cuenta cuenta2 : listaCuentas) {
                    if (cuenta2.getCedDuenoCuenta().equals(numCedIng) && cuenta2.getTipoCuenta().equals("CORRIENTE") && cuenta2.getNumeroCuenta() != numCuentaIngEliminar) {

                        JOptionPane.showMessageDialog(null, "CUENTA ELIMINADA: Usted tiene otras cuentas bancarias CORRIENTES que verá AL PRESIONAR EL BOTON");
                        mostrarCuentasCorrientesAdicionales(numCedIng, numCuentaIngEliminar);

                        break;

                    }
                }
                break;
            }

        }

    }

    public void mostrarCuentasCorrientesAdicionales(String numCedIng, int numCuentaIngEliminar) {
        System.out.println("\n\t INFORMACION DE OTRAS CUENTAS CORRIENTES A SU NOMBRE: \n");
        for (Cuenta cuenta2 : listaCuentas) {
            if (cuenta2.getCedDuenoCuenta().equals(numCedIng) && cuenta2.getTipoCuenta().equals("CORRIENTE") && cuenta2.getNumeroCuenta() != numCuentaIngEliminar) {

                System.out.println(cuenta2);

            }
        }
    }

    //PARA DEPOSITOS Y RETIROS
    public void ajustarSaldoCuenta(String tipoTransaccionIng, String numCedIng, int numCuentaIng, double montoTransaccion, Date fechaTransaccion) {


        int index = -1;
        double recargosDescontar = 0;
        double cargoComision = 0;
        double saldoBruto = 0;
        double saldoNeto = 0;

        for (Cuenta cuenta : listaCuentas) {

            if (cuenta.getCedDuenoCuenta().equals(numCedIng) && cuenta.getNumeroCuenta() == numCuentaIng) {

                index = listaCuentas.indexOf(cuenta);

                recargosDescontar = exceptuar.calculoDeRecargosValidarTransaccion(tipoTransaccionIng, listaCuentas, index, (montoTransaccion), fechaTransaccion);
                cargoComision = exceptuar.calcularRecargoComision(listaCuentas, index, (montoTransaccion));

                //
                System.out.println("RECARGOS POR TRANSACCION  " + cargoComision);

                if (tipoTransaccionIng == "RETIRO") {
                    cuenta.setSaldo(cuenta.getSaldo() - montoTransaccion - cargoComision);
                    saldoBruto = cuenta.getSaldo();
                    saldoNeto = cuenta.getSaldo() - (recargosDescontar - cargoComision);

                } else if (tipoTransaccionIng == "DEPOSITO") {

                    cuenta.setSaldo(cuenta.getSaldo() + montoTransaccion - cargoComision);
                    saldoBruto = cuenta.getSaldo();
                    saldoNeto = cuenta.getSaldo() - (recargosDescontar - cargoComision);
                }

                if (tipoTransaccionIng == "RETIRO") {

                    System.out.println("\n\tTRANSACCIÓN EXITOSA, NUEVO SALDO BRUTO* DE SU CUENTA ES " + saldoBruto + " colones.\nSALDO NETO (DISPONIBLE) DESPUÉS DE INTERESES HASTA ESTE AÑO " + saldoNeto);

                } else if (tipoTransaccionIng == "DEPOSITO") {

                    System.out.println("\n\tTRANSACCIÓN EXITOSA, NUEVO SALDO BRUTO* DE SU CUENTA ES " + saldoBruto + " colones");

                }
                break;

            }

        }

    }

    //
    //
    //
    public void validarRetirarDeCuenta(Banco opciones) {
    }

    public List<Banco> getListaBancos() {
        return listaBancos;
    }

    public void setListaBancos(List<Banco> listaBancos) {
        this.listaBancos = listaBancos;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

}
