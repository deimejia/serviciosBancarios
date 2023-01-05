/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosbancarios;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import jdk.dynalink.beans.MissingMemberHandlerFactory;

/**
 *
 * @author Zeus
 */
public class Excepciones extends Exception {

    public Excepciones() {
    }

    public Excepciones(String message) {
        super(message);
    }
//
//    public Excepciones(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public Excepciones(Throwable cause) {
//        super(cause);
//    }
//
//    public Excepciones(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
//    

    // PARA DEPOSITAR EN CUENTA
    public void cuentaADepositarExista() {

    }

    // EL SIGUIENTE METODO SOLO FUNCIONA PARA SABER SI EL INDICE DEL BANCO ELEGIDO ES VALIDO, SI NO LO ES ME ACTIVARÁ AL EXCEPCION IndexOutOfBoundsException
    public void bancoExista(List<Banco> listaBancosExistentes, int bancoIng) throws Exception {

        boolean clave = listaBancosExistentes.get(bancoIng - 1).getNombreB().isEmpty();

    }

    public void validarCedulaParaEliminarCuenta(String ceduIngParaEliminarCuenta, List<Cuenta> listaDecuentas) throws Exception {

        boolean bandera = false;

        for (Cuenta cuentaBuscada : listaDecuentas) {
            if (cuentaBuscada.getCedDuenoCuenta().equals(ceduIngParaEliminarCuenta)) {

                bandera = true;
                break;

            }

        }

        if (bandera) {

            JOptionPane.showMessageDialog(null, "EXCELENTE TIENES UNA O MÁS CUENTAS ASOCIADAS");

        } else {

            throw new Exception("NO TIENES CUENTAS ASOCIADAS A TU NUMERO DE CEDULA\n SERÁS DEVUELVO AL MENÚ PRINCIPAL");

        }

    }

    public void validarTipoDeCuenta(List<Banco> listaBancosExistentes, List<Cuenta> listaCuentasExistentes, String eleccionTipoCuenta,
            String numCedIng, int bancoIng) throws Exception {
        if (!eleccionTipoCuenta.isEmpty()) {

            int cantidadCuentasAhorros = 0;
            int cantidadCuentasCorrientesTotal = 0;
            int cantidadCuentasCorrientesMismoBanco = 0;
            //   int cantidadCuentasCorrientesBancosDiferentes = 0;

            for (Cuenta verificador : listaCuentasExistentes) {

                if (verificador.getCedDuenoCuenta().equals(numCedIng) && verificador.getTipoCuenta().equals(eleccionTipoCuenta)) {

                    if (eleccionTipoCuenta == "AHORROS") {

                        cantidadCuentasAhorros++;
                    } else {
                        cantidadCuentasCorrientesTotal++;
                    }

                }

                if (verificador.getCedDuenoCuenta().equals(numCedIng) && verificador.getNombreB().equals(listaBancosExistentes.get(bancoIng).getNombreB())) {

                    if (verificador.getTipoCuenta().equals(eleccionTipoCuenta)) {

                        if (eleccionTipoCuenta == "CORRIENTE") {

                            cantidadCuentasCorrientesMismoBanco++;
                        }

                    }

                }

            }

            if (cantidadCuentasAhorros > 0) {
                throw new Exception("YA TIENES UNA CUENTA DE AHORROS, NO PUEDES CREAR MÁS. SERÁS DEVUELTO AL MENÚ PRINCIPAL");
            } else {
                if (cantidadCuentasCorrientesTotal > 5) {
                    throw new Exception("YA TIENES 6 CUENTAS CORRIENTES EN TOTAL, NO PUEDES CREAR MÁS. SERÁS DEVUELTO AL MENÚ PRINCIPAL");
                } else {
                    if (cantidadCuentasCorrientesMismoBanco > 2) {
                        throw new Exception("YA TIENES 3 CUENTAS CORRIENTES EN EL BANCO ELEGIDO, NO PUEDES CREAR MÁS. SERÁS DEVUELTO AL MENÚ PRINCIPAL");
                    }

                }
            }

        }

    }

//
    // VALIDA NUM CUENTA DESEADO POR EL CLIENTE PARA CREAR CUENTA
    public void validarNumDeCuentaACrear(List<Cuenta> listaCuentasExistentes,
            String numCedIng, int bancoIng, int numCuentaIng) throws Exception {

        if (numCuentaIng != 0) {
            boolean banderaMismaPersona = false;
            boolean banderaExisteotraPersona = false;

            for (Cuenta verificador : listaCuentasExistentes) {

                if (verificador.getNumeroCuenta() == numCuentaIng && verificador.getCedDuenoCuenta().equals(numCedIng)) {

                    banderaMismaPersona = true;
                    break;
                } else {
                    if (verificador.getNumeroCuenta() == numCuentaIng && !verificador.getCedDuenoCuenta().equals(numCedIng)) {

                        banderaExisteotraPersona = true;
                        break;
                    }
                }

            }

            if (banderaMismaPersona) {

                mostrarCuentasEnExcepciones(listaCuentasExistentes, numCedIng, numCuentaIng);

            }
            if (banderaMismaPersona) {
                throw new Exception("Ya tienes una cuenta con el mismo número, por favor ingresa otro");

            } else {
                if (banderaExisteotraPersona) {
                    throw new Exception("Número de cuenta ya existe para otro usuario, por favor ingresar otro número");
                }
            }
        }

    }

    public void mostrarCuentasEnExcepciones(List<Cuenta> listaCuentasExistentes, String numCedIng, int numCuentaIng) {

        for (Cuenta cuenta : listaCuentasExistentes) {

            if (cuenta.getNumeroCuenta() == numCuentaIng && cuenta.getCedDuenoCuenta().equals(numCedIng)) {

                System.out.println("\n\tSUS CUENTAS CREADAS SON\n\n" + cuenta + "\n");

            }

        }

    }

    public void validarSaldoDeCuentaEliminar(List<Cuenta> listaCuentasExistentes, int numCuentaIngEliminar, String numCedIng) throws Exception {

        if (numCuentaIngEliminar != 0) {
            boolean banderaSaldo = false;

            double extraccionSaldo = 0;

            for (Cuenta cuenta : listaCuentasExistentes) {

                if (cuenta.getNumeroCuenta() == numCuentaIngEliminar && cuenta.getSaldo() != 0) {

                    banderaSaldo = true;
                    extraccionSaldo = cuenta.getSaldo();
                    break;

                }
            }

            if (banderaSaldo) {
                throw new Exception("El saldo de la cuenta es de: " + extraccionSaldo + "\n IMPOSIBLE ELIMINAR "
                        + "cuentas con saldos que no sean iguales a 0\n");

            }
        }

    }

    public void validarSiCuentaEliminarExiste(List<Cuenta> listaCuentasExistentes, int numCuentaIng, String numCedIng) throws Exception {

        if (numCuentaIng != 0) {

            boolean banderaExisteCuentaEliminar = false;

            for (Cuenta cuenta : listaCuentasExistentes) {

                if (cuenta.getCedDuenoCuenta().equals(numCedIng) && cuenta.getNumeroCuenta() == numCuentaIng) {

                    banderaExisteCuentaEliminar = true;
                    break;

                }

            }

            if (!banderaExisteCuentaEliminar) {
                throw new Exception("CUENTA INGRESADA NO EXISTE O PERTENECE A OTRA PERSONA");

            }

        }

    }
    //
    //VALIDAR SALDO INICIAL PARA CREAR CUENTA

    public void validarSaldoIncial(double saldoIng) throws Exception {

        double saldoInicialPermitido = 100000;

        if (saldoIng < saldoInicialPermitido) {

            throw new Exception("SALDO INICIAL DEBE SER MAYOR O IGUAL 100.000");
        }

    }

    //
    //EXCEPCIONES PARA DEPOSITAR CUENTA
    // VALIDA NUM CUENTA DESEADO POR EL CLIENTE PARA DEPOSITAR EN CUENTA
    public void validarNumDeCuentaTransaccion(List<Cuenta> listaCuentasExistentes, List<Banco> listaBancos,
            String numCedIng, int bancoIng, int numCuentaIng) throws Exception {

        if (numCuentaIng != 0) {
            boolean banderaMismaPersona = false;
            boolean banderaNoPerteneceAOtroBanco = false;

            for (Cuenta verificador : listaCuentasExistentes) {

                if (verificador.getCedDuenoCuenta().equals(numCedIng) && verificador.getNumeroCuenta() == numCuentaIng) {

                    banderaMismaPersona = true;

                    if (verificador.getCedDuenoCuenta().equals(numCedIng) && verificador.getNumeroCuenta() == numCuentaIng && verificador.getNombreB().equals(listaBancos.get(bancoIng - 1).getNombreB())) {

                        banderaNoPerteneceAOtroBanco = true;
                        break;

                    }

                }

            }

            if (!banderaMismaPersona) {
                throw new Exception("NÚMERO DE CUENTA NO EXISTE O NO TE PERTENECE");

            }
            if (!banderaNoPerteneceAOtroBanco) {
                throw new Exception("NÚMERO DE CUENTA NO PERTENECE AL BANCO ELEGIDO");
            }

        }

    }

    public void fechaCracionYFechaTransaccion(List<Cuenta> listaCuentasExistentes, List<Banco> listaBancos, String numCedIng, int numCuentaIng, int bancoElegidoParadeposito, Date fechaTransaccionDate) throws Exception {

        boolean bandera = false;
        int index = -1;

        for (Cuenta cuenta : listaCuentasExistentes) {

            if (cuenta.getCedDuenoCuenta().equals(numCedIng) && cuenta.getNumeroCuenta() == numCuentaIng && cuenta.getNombreB().equals(listaBancos.get(bancoElegidoParadeposito).getNombreB())) {
                index = listaCuentasExistentes.indexOf(cuenta);
            }
            if (cuenta.getCedDuenoCuenta().equals(numCedIng) && cuenta.getNumeroCuenta() == numCuentaIng && cuenta.getNombreB().equals(listaBancos.get(bancoElegidoParadeposito).getNombreB()) && cuenta.getFechaApertura().before(fechaTransaccionDate)) {
                bandera = true;
                break;
            }

        }

        if (!bandera) {

            throw new Exception("FECHA DE ESTA TRANSACCIÓN NO PUEDE SER ANTES DE LA FECHA DE CREACIÓN DE LA CUENTA (" + listaCuentasExistentes.get(index).getFechaApertura() + "),\n POR FAVOR VALIDE LA FECHA");

        }

    }

    public void validarMontoTransaccion(List<Cuenta> listaCuentasExistentes, String numCedIng, String tipoTransaccionIng, int numCuentaIng, double montoTransaccion, Date fechaTransaccion) throws Exception {
        if (montoTransaccion != 0) {
            String tipoTransaccion = tipoTransaccionIng;

            boolean banderaMayorQueMaximo = false;
            boolean banderaMenorQueminimo = false;
            boolean banderaSaldoInsuficiente = false;
            boolean banderaSaldoDeCuentaNoPermitido = false;
            double saldoActual = 0;
            double totalRecargos = 0;

            for (Cuenta cuenta : listaCuentasExistentes) {

                if (cuenta.getCedDuenoCuenta().equals(numCedIng) && cuenta.getNumeroCuenta() == numCuentaIng) {

                    saldoActual = cuenta.getSaldo();

                    if (cuenta.getMontoMax() >= montoTransaccion) {
                        banderaMayorQueMaximo = true;

                    }

                    if (cuenta.getMontoMin() <= montoTransaccion) {
                        banderaMenorQueminimo = true;

                    }

                    if (tipoTransaccion == "RETIRO") {
                        totalRecargos = calculoDeRecargosValidarTransaccion(tipoTransaccionIng, listaCuentasExistentes, listaCuentasExistentes.indexOf(cuenta), montoTransaccion, fechaTransaccion);

                        if (cuenta.getSaldo() >= montoTransaccion) {
                            banderaSaldoInsuficiente = true;
                        }

                        if ((cuenta.getSaldo() - totalRecargos - montoTransaccion) >= 100000) {
                            banderaSaldoDeCuentaNoPermitido = true;
                        }
                    }

                    break;

                }

            }

            if (!banderaMayorQueMaximo) {

                throw new Exception("EL MONTO INDICADO ES MAYOR AL MÁXIMO PERMITIDO EN UNA TRANSACCION");

            }

            if (!banderaMenorQueminimo) {

                throw new Exception("EL MONTO INDICADO ES MENOR AL MÍNIMO PERMITIDO EN UNA TRANSACCION");

            }

            if (tipoTransaccionIng == "RETIRO") {

                if (!banderaSaldoInsuficiente) {

                    throw new Exception("SALDO INSUFICIENTE, SALDO CORRESPONDE A " + saldoActual);

                }

                if (!banderaSaldoDeCuentaNoPermitido) {

                    throw new Exception("EL SALDO FINAL NO PUEDE SER MENOR A 100.000, POR FAVOR RETIRE UN MONTO MENOR\n(SALDO ACTUAL " + saldoActual + ")\nSaldo si se realiza la transacción (descontando intereses anuales y comisión por transacción) : " + (saldoActual - totalRecargos - montoTransaccion));

                }

            }

        }

    }

    public double calculoDeRecargosValidarTransaccion(String tipoTransaccion, List<Cuenta> listaCuentasExistentes, int index, double montoTransaccion, Date fechaTransaccion) {

        double porcentajeComision = listaCuentasExistentes.get(index).getCobroTrans();
        double porcentajeInteresAnual = listaCuentasExistentes.get(index).getInteresAnual();
        double saldoActual = listaCuentasExistentes.get(index).getSaldo();
        Date fechaApertura = listaCuentasExistentes.get(index).getFechaApertura();
        long tiempoTransaccion = fechaTransaccion.getTime();

        long tiempoApertura = fechaApertura.getTime();

        long tiempoTranscurrido = tiempoTransaccion - tiempoApertura;

        int aniosTranscurridos = (int) (tiempoTranscurrido / 1000 / 60 / 60 / 24 / 365);

        //
        double interesesAnuales = 0;
        double comisionPorTransaccion = 0;
        double totalRecargos = 0;

        //
        interesesAnuales = (aniosTranscurridos * porcentajeInteresAnual) * saldoActual;
        System.out.println("INTERES ANUAL " + interesesAnuales);
        comisionPorTransaccion = montoTransaccion * porcentajeComision;
        System.out.println("COMISION TRANS\n*—————*—————*\n " + comisionPorTransaccion);

        totalRecargos = comisionPorTransaccion + interesesAnuales;

        //
        return totalRecargos;

    }

    public double calcularRecargoComision(List<Cuenta> listaCuentasExistentes, int index, double montoTransaccion) {

        double porcentajeComision = listaCuentasExistentes.get(index).getCobroTrans();

        //
        double comisionPorTransaccion = 0;

        //
        comisionPorTransaccion = montoTransaccion * porcentajeComision;

        //
        return comisionPorTransaccion;

    }

    //
    public void validarEliminarBanco(String numeroBanco, String nombreB, List<Banco> listaBancos) throws Exception {

        for (Banco banco : listaBancos) {

            if (banco.getNombreB().equals(nombreB)) {
                throw new Exception("Nombre de banco ya existe, intente con otro");

            } else if (banco.getNumeroBanco().equals(numeroBanco)) {
                throw new Exception("Número de banco ya existe, intente con otro");
            }
        }

    }

    public void validarEliminarBancoDos(String numeroBancoEliminar, List<Banco> listaBancos, List<Cuenta> listaCuentas) throws Exception {

        boolean bandera = false;

        for (Banco banco : listaBancos) {

            if (banco.getNumeroBanco().equals(numeroBancoEliminar)) {
                bandera = true;
                break;

            }
        }

        if (!bandera) {

            throw new Exception("Número de banco incorrecto o no existe, intente con otro");

        } else {
            for (Cuenta cuenta : listaCuentas) {

                if (cuenta.getNumeroBanco().equals(numeroBancoEliminar)) {
                    throw new Exception("Banco no se puede eliminar ya que tiene cuentas asociadas, intente con otro");

                }

            }
        }

    }
    //

}
