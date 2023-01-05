package serviciosbancarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Vistas {

    private final Controlador listas = new Controlador();

    private final Controlador metodosControlador = new Controlador();

    private final Excepciones excepciones = new Excepciones();

    private final Scanner datoIng = new Scanner(System.in);

    //
    //CREAR
    public void vistaInicio() {

        int opcion = 0;

        do {
            //  datoIng.nextLine();

            try {

                System.out.println("\n\tHola, bienvenido a la plaforma bancaria \n "
                        + "a continuación encontrará diferentes funciones de la plataforma:\n");

                System.out.println("1 . Crear una cuenta");
                System.out.println("2 . Eliminar cuenta");
                System.out.println("3 . Depositar en una cuenta");
                System.out.println("4 . Retirar de una cuenta");
                System.out.println("5 . Crear un banco");
                System.out.println("6 . Eliminar un banco");
                System.out.println("7 . Salir\n");

                System.out.print("Digite una opción : ");
                opcion = datoIng.nextInt();

                switch (opcion) {
                    case 1 -> {
                        vistaEleccionBancoCrearCuenta();

                    }
                    case 2 -> {
                        vistaEliminarCuenta();
                    }
                    case 3 -> {
                        vistaDepositarEnCuentas();
                    }
                    case 4 -> {
                        vistaRetirarEnCuentas();
                    }
                    case 5 -> {
                        vistaCrearBanco();
                    }
                    case 6 -> {
                        vistaEliminar();
                    }
                    case 7 ->

                        JOptionPane.showMessageDialog(null, "Fue un placer servirle");

                    default ->

                        JOptionPane.showMessageDialog(null, "OPCION NO DISPONIBLE");

                }

            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "POR FAVOR INGRESE UN NUMERO");
                datoIng.nextLine();

                // JOptionPane.showMessageDialog(null, "SOLUCION");
                //      opcion=-1;
            }

        } while (opcion != 7);

    }

    //USADO EN VISTA INICIO
    public void vistaEleccionBancoCrearCuenta() {

        int bancoElegido = -1;

        do {
            try {
                System.out.println("\n\t  Por favor elija un banco donde desea "
                        + "realizar su gestión,\n (elija el que más le convenga):\n");

                for (Banco bn : listas.getListaBancos()) {

                    System.out.println("Opción:" + (listas.getListaBancos().indexOf(bn) + 1));

                    System.out.println("Banco " + bn.getNombreB());
                    System.out.println("Monto máximo posible: " + bn.getMontoMax());
                    System.out.println("Monto mínimo posible: " + bn.getMontoMin());
                    System.out.println("Cobro por transferencia: " + bn.getCobroTrans());
                    System.out.println("Interés anual recargado: " + bn.getInteresAnual() + "\n\n");

                }

                System.out.print("Opción 0: ir atrás\n\n");
                System.out.print("Indique su opción por favor: ");
                bancoElegido = datoIng.nextInt();

                // EL SIGUIENTE METODO SOLO FUNCIONA PARA SABER SI EL INDICE DEL BANCO ELEGIDO ES VALIDO, SI NO LO ES ME ACTIVARÁ AL EXCEPCION IndexOutOfBoundsException
                excepciones.bancoExista(listas.getListaBancos(), bancoElegido);

                //  excepciones.bancoExista(listas.getListaBancos(), bancoElegido);
                metodosControlador.llenarDatosCuenta((bancoElegido - 1), listas.getListaBancos());
                break;

            } catch (Exception e) {
                if (e instanceof IndexOutOfBoundsException && bancoElegido != 0) {

                    JOptionPane.showMessageDialog(null, "BANCO NO EXISTE");
                    datoIng.nextLine();

                } else {

                    if (e instanceof Exception && bancoElegido != 0) {
                        JOptionPane.showMessageDialog(null, "DATO INVALIDO, VUELVA A INGRESARLO");
                        datoIng.nextLine();

                    } else {
                        break;
                    }

                }

            }

        } while (true);

    }

    //
    //ELIMINAR USADO EN VISTA INICIO
    public void vistaEliminarCuenta() {

        do {
            try {
                System.out.print("\n\tPor favor ingrese su número de cédula para validar sus cuentas disponibles: ");
                String ceduIng = datoIng.next();

                excepciones.validarCedulaParaEliminarCuenta(ceduIng, metodosControlador.getListaCuentas());
                vistaMostrarCuentasParaEliminarPorCedula(ceduIng);
                int numCuentaEliminar = vistaRecibirCuentaParaEliminar(ceduIng);

                if (numCuentaEliminar > 0) {
                    metodosControlador.eliminarCuenta(numCuentaEliminar, ceduIng);

                }

                break;
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
                break;

            }
        } while (true);

    }

    // SE USA EN EL METÓDO vistaEliminarCuenta()
    public void vistaMostrarCuentasParaEliminarPorCedula(String ceduIng) {

        System.out.println("\n\tCUENTAS QUE PUEDES ELIMINAR\n");

        for (Cuenta cuenta : metodosControlador.getListaCuentas()) {

            if (cuenta.getCedDuenoCuenta().equals(ceduIng)) {

                System.out.println("\n" + "CUENTA  \n" + cuenta + "\n");
            }

        }

    }

    //SE USA EN EL MÉTODO vistaEliminarCuenta()
    public int vistaRecibirCuentaParaEliminar(String ceduIng) {
        int numCuenta = 0;

        do {

            try {
                System.out.print("\n\t Por favor digite el número de cuenta que desea eliminar (o ingrese 0 para ir atrás) ");
                numCuenta = datoIng.nextInt();
                excepciones.validarSiCuentaEliminarExiste(metodosControlador.getListaCuentas(), numCuenta, ceduIng);
                excepciones.validarSaldoDeCuentaEliminar(metodosControlador.getListaCuentas(), numCuenta, ceduIng);
                if (numCuenta == 0) {
                    numCuenta = 0;

                    break;
                } else {
                    metodosControlador.validarOtrasCuentasCorrientes(ceduIng, numCuenta);
                    break;
                }

            } catch (Exception e) {

                if (e instanceof InputMismatchException) {

                    JOptionPane.showMessageDialog(null, "DATO INVALIDO, VUELVA A INGRESARLO");
                    datoIng.nextLine();

                } else {

                    // SI LANZA UNA EXCEPCION DE ESTE TIPO NO SALE PORQUE EL USUARIO PODRIA INTENTAR CON OTRO NUMERO DE CUENTA, 
                    //ADEMÁS TIENE LA OPCION DEL 0 PARA SALIR EN ESTE CASO*
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }

            }

        } while (true);

        return numCuenta;
    }

    //
    //----------------------DEPOSITOS---------------------------------
    //
    //VISTA CUANDO EL USUARIO QUIERE DEPOSITAR EN CUENTA
    public int vistaEleccionBancoTransaccionEnCuenta() {

        int bancoElegido = -1;

        do {
            try {
                System.out.println("\n\nTRANSACCIONES BANCARIAS \n\t  Por favor elija un banco donde desea "
                        + "\trealizar su gestión,\n\t (elija el que más le convenga):\n");

                for (Banco bn : listas.getListaBancos()) {

                    System.out.println("Opción:" + (listas.getListaBancos().indexOf(bn) + 1));

                    System.out.println("Banco " + bn.getNombreB() + "\n");

                }

                System.out.print("Opción 0: ir atrás\n\n");
                System.out.print("Indique su opción por favor: ");
                bancoElegido = datoIng.nextInt();

                // EL SIGUIENTE METODO SOLO FUNCIONA PARA SABER SI EL INDICE DEL BANCO ELEGIDO ES VALIDO, SI NO LO ES ME ACTIVARÁ AL EXCEPCION IndexOutOfBoundsException
                excepciones.bancoExista(listas.getListaBancos(), bancoElegido);

                //  excepciones.bancoExista(listas.getListaBancos(), bancoElegido);
                break;

            } catch (Exception e) {
                if (e instanceof IndexOutOfBoundsException && bancoElegido != 0) {

                    JOptionPane.showMessageDialog(null, "BANCO NO EXISTE");
                    datoIng.nextLine();

                } else {

                    if (e instanceof Exception && bancoElegido != 0) {
                        JOptionPane.showMessageDialog(null, "DATO INVALIDO, VUELVA A INGRESARLO");
                        datoIng.nextLine();

                    } else {
                        break;
                    }

                }

            }

        } while (true);

        return bancoElegido;

    }

    //USADO EN VISTA INICIO
    public void vistaDepositarEnCuentas() {

        String numCedIng = "";
        int bancoElegidoParadeposito = -1;
        int numCuentaIng = 0;
        double montoTransaccion = 0;
        boolean banderaGlobal = true;
        boolean banderaLocal = true;
        String tipoTransaccionIng = "DEPOSITO";

        do {
            try {
                bancoElegidoParadeposito = vistaEleccionBancoTransaccionEnCuenta();

                if (bancoElegidoParadeposito == 0) {
                    banderaGlobal = false;
                    break;

                } else {

                    System.out.println("\tDEPOSITOS BANCARIOS");
                    System.out.println("\tUsted va a ingresar dinero a una cuenta bancaria\n");
                    System.out.print("Por favor ingrese su número de cédula: ");
                    numCedIng = datoIng.next();
                    //
                    System.out.print("\nPor favor ingrese el número de cuenta a realizar el depósito: ");
                    numCuentaIng = datoIng.nextInt();
                    excepciones.validarNumDeCuentaTransaccion(metodosControlador.getListaCuentas(), listas.getListaBancos(),
                            numCedIng, bancoElegidoParadeposito, numCuentaIng);
                    break;
                }

            } catch (Exception e) {

                if (e instanceof InputMismatchException) {

                    JOptionPane.showMessageDialog(null, "DATO INVÁLIDO, POR FAVOR VUELVA A INGRESARLO");
                    datoIng.nextLine();

                } else {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                    datoIng.nextLine();
                }

            }
        } while (true);

        String fechaTransaccionString;
        Date fechaTransaccionDate = null;

        while (banderaGlobal) {
            try {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                System.out.print("\n Ingrese la fecha de esta transacción por favor: ");
                fechaTransaccionString = datoIng.next();
                fechaTransaccionDate = formato.parse(fechaTransaccionString);

                excepciones.fechaCracionYFechaTransaccion(metodosControlador.getListaCuentas(), listas.getListaBancos(), numCedIng, numCuentaIng, (bancoElegidoParadeposito - 1), fechaTransaccionDate);
                break;
            } catch (Exception e) {

                if (e instanceof ParseException) {
                    JOptionPane.showMessageDialog(null, "formato de fecha debe ser el siguiente 31/05/2020 \n por favor vuelva a ingresarla");
                    datoIng.nextLine();

                } else {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    datoIng.nextLine();
                }

            }

        }

        //
        //
        while (banderaGlobal) {

            try {
                System.out.print("\nPor favor ingrese el monto que desea depositar (o ingrese 0 para salir): ");
                montoTransaccion = datoIng.nextDouble();
                excepciones.validarMontoTransaccion(metodosControlador.getListaCuentas(), numCedIng, tipoTransaccionIng, numCuentaIng, montoTransaccion, fechaTransaccionDate);

                if (montoTransaccion == 0) {
                    break;
                } else {
                    metodosControlador.ajustarSaldoCuenta(tipoTransaccionIng, numCedIng, numCuentaIng, montoTransaccion, fechaTransaccionDate);
                    break;
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
                datoIng.nextLine();

            }

        }

    }

    //
    public void crearBancosPred() {

        Banco bancoPredUno = new Banco("1", "BAC", 1000000, 100000, 0.03, 0.05);
        Banco bancoPredDos = new Banco("2", "HSBC", 1500000, 150000, 0.04, 0.06);
        listas.getListaBancos().add(bancoPredUno);
        listas.getListaBancos().add(bancoPredDos);

    }

    //
    //
    //----------------------RETIROS---------------------------------
    public void vistaRetirarEnCuentas() {

        String numCedIng = "";
        int bancoElegidoParaRetiro = -1;
        int numCuentaIng = 0;
        double montoTransaccion = 0;
        boolean banderaGlobal = true;
        boolean banderaLocal = true;
        String tipoTransaccionIng = "RETIRO";

        do {
            try {
                bancoElegidoParaRetiro = vistaEleccionBancoTransaccionEnCuenta();

                if (bancoElegidoParaRetiro == 0) {
                    banderaGlobal = false;
                    break;

                } else {

                    System.out.println("\tRETIROS BANCARIOS");
                    System.out.println("\tUsted va a retirar dinero a una cuenta bancaria\n");
                    System.out.print("Por favor ingrese su número de cédula: ");
                    numCedIng = datoIng.next();
                    //
                    System.out.print("\nPor favor ingrese el número de cuenta a realizar el retiro: ");
                    numCuentaIng = datoIng.nextInt();
                    excepciones.validarNumDeCuentaTransaccion(metodosControlador.getListaCuentas(), listas.getListaBancos(),
                            numCedIng, bancoElegidoParaRetiro, numCuentaIng);
                    break;
                }

            } catch (Exception e) {

                if (e instanceof InputMismatchException) {

                    JOptionPane.showMessageDialog(null, "DATO INVÁLIDO, POR FAVOR VUELVA A INGRESARLO");
                    datoIng.nextLine();

                } else {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                    datoIng.nextLine();
                }

            }
        } while (true);

        String fechaTransaccionString;
        Date fechaTransaccionDate = null;

        while (banderaGlobal) {
            try {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                System.out.print("\n Ingrese la fecha de esta transacción por favor: ");
                fechaTransaccionString = datoIng.next();
                fechaTransaccionDate = formato.parse(fechaTransaccionString);

                excepciones.fechaCracionYFechaTransaccion(metodosControlador.getListaCuentas(), listas.getListaBancos(), numCedIng, numCuentaIng, (bancoElegidoParaRetiro - 1), fechaTransaccionDate);
                break;
            } catch (Exception e) {

                if (e instanceof ParseException) {
                    JOptionPane.showMessageDialog(null, "formato de fecha debe ser el siguiente 31/05/2020 \n por favor vuelva a ingresarla");
                    datoIng.nextLine();

                } else {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    datoIng.nextLine();

                }

            }

        }

        //
        //
        while (banderaGlobal) {

            try {
                System.out.print("\nPor favor ingrese el monto que desea retirar (puedes ingresar 0 para salir): ");
                montoTransaccion = datoIng.nextDouble();
                excepciones.validarMontoTransaccion(metodosControlador.getListaCuentas(), numCedIng, tipoTransaccionIng, numCuentaIng, montoTransaccion, fechaTransaccionDate);
                if (montoTransaccion == 0) {
                    break;
                } else {
                    metodosControlador.ajustarSaldoCuenta(tipoTransaccionIng, numCedIng, numCuentaIng, (montoTransaccion ), fechaTransaccionDate);
                    break;
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
                datoIng.nextLine();

            }

        }

    }

    //
    //
    public void vistaCrearBanco() {
        int opcion = 0;
        boolean banderaInterna = true;
        String numeroBanco;
        String nombreB = null;
        double montoMax = 0;
        double montoMin = 0;
        double cobroTrans = 0;
        double interesAnual = 0;

        do {
            try {
                System.out.print("\n\n\tUsted creará un banco, por favor llene los siguientes datos,\nsi no está seguro ingrese 0 para salir o 1 para continuar: ");
                opcion = datoIng.nextInt();

                if (opcion == 0) {
                    banderaInterna = false;

                } else if (opcion == 1) {

                    System.out.print("Ingrese un número identificador para el banco: \n");
                    numeroBanco = datoIng.next();
                    //
                    System.out.print("Ingrese un nombre de banco: \n");
                    nombreB = datoIng.next();
                    excepciones.validarEliminarBanco(numeroBanco, nombreB, listas.getListaBancos());
                    //
                    System.out.print("Ingrese un monto máximo de tranferencias: \n");
                    montoMax = datoIng.nextDouble();
                    //
                    System.out.print("Ingrese un monto mínimo de tranferencias: \n");
                    montoMin = datoIng.nextDouble();
                    //
                    System.out.print("Ingrese un porcentaje de cobro por transacción (debe ser expresado en decimal separado por punto(ejemplo: 0.02)): \n");
                    cobroTrans = datoIng.nextDouble();
                    //
                    System.out.print("Ingrese un porcentaje de cobro por interés anual (debe ser expresado en decimal separado por punto(ejemplo: 0.02)): \n");
                    interesAnual = datoIng.nextDouble();

                    //
                    //
                    Banco bancoNuevo = new Banco(numeroBanco, nombreB, montoMax, montoMin, cobroTrans, interesAnual);
                    listas.getListaBancos().add(bancoNuevo);
                    banderaInterna = false;

                }
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
                datoIng.nextLine();
            }
        } while (banderaInterna);

    }

    public void vistaEliminar() {
        int opcion = 0;
        boolean banderaInterna = true;
        String numeroBancoEliminar;
        String nombreB = null;

        do {
            try {
                System.out.print("\n\n\tUsted eliminará un banco, se le mostrará cada banco posible de eliminar,\nsi no está seguro ingrese 0 para salir o 1 para continuar: ");
                opcion = datoIng.nextInt();

                if (opcion == 0) {
                    banderaInterna = false;

                } else if (opcion == 1) {

                    for (Banco banco : listas.getListaBancos()) {

                        System.out.println(banco);

                    }

                    System.out.print("\nIngrese el Número Identificador Banco a eliminar: ");
                    numeroBancoEliminar = datoIng.next();
                    
                    excepciones.validarEliminarBancoDos(numeroBancoEliminar, listas.getListaBancos(), metodosControlador.getListaCuentas());
                    //

                    //
                    //
                    for (Banco banco : listas.getListaBancos()) {

                        if (banco.getNumeroBanco().equals(numeroBancoEliminar)) {

                            listas.getListaBancos().remove(banco);
                            JOptionPane.showMessageDialog(null, "Banco eliminado con éxito");
                            System.out.println(listas.getListaCuentas().get(0).getNumeroBanco()); 
                            
                        }

                    }
                    banderaInterna = false;

                }
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
                datoIng.nextLine();
            }
        } while (banderaInterna);

    }
}
