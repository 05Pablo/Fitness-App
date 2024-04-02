import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        Clase principal en la cual se le pasan los atributos a las clases pidiéndole al usuario los datos y donde se muestra el menú
         */
        Scanner sc = new Scanner(System.in);
        int opcionI = 0; // Inicializamos opcionI aquí
        int opcion = 0, tipoEjercicio, calorias, dia, mes, anno;
        double peso, ejercicios;
        String nombre, nivelPedido;
        Nivel nivel = null;
        RegistroUsuarios usuarios = new RegistroUsuarios();
        Usuario usuarioActual = null;
        String nombreborrado;

        do {
            try {
                System.out.println("Bienvenido a FitnessApp, ¿Qué desea hacer? \n1. Registrar usuario \n2. Iniciar sesión \n3.Leer datos \n4.Guardar datos \n5.Informe General\n6. Salir");
                opcionI = sc.nextInt();

                switch (opcionI) {
                    case 1:
                        System.out.println("Introduce el nombre: ");
                        nombre = sc.next();
                        System.out.println("Introduzca la edad");
                        int edad = sc.nextInt();
                        System.out.println("Introduce el peso: ");
                        peso = sc.nextDouble();
                        System.out.println("Introduce el nivel (PRINCIPIANTE-INTERMEDIO-AVANZADO)");
                        nivelPedido = sc.next();

                        if (nivelPedido.equalsIgnoreCase("Intermedio")) {
                            nivel = Nivel.INTERMEDIO;
                        } else if (nivelPedido.equalsIgnoreCase("Principiante")) {
                            nivel = Nivel.PRINCIPIANTE;
                        } else if (nivelPedido.equalsIgnoreCase("Avanzado")) {
                            nivel = Nivel.AVANZADO;
                        } else {
                            throw new IllegalArgumentException("Nivel de usuario no válido");
                        }

                        usuarioActual = new Usuario(nombre, edad, peso, nivel);
                        usuarios.agregarUsuario(usuarioActual);
                        break;
                    case 2:
                        System.out.println("Introduzca el nombre: ");
                        nombre = sc.next();
                        usuarioActual = usuarios.buscarUsuario(nombre);
                        if (usuarioActual == null) {
                            System.out.println("Este usuario no existe");
                            break;
                        }

                        do {
                            try{
                                System.out.println("Bienvenido " + nombre + " ¿qué desea hacer? \n1-Añadir ejercicio \n2-Eliminar un ejercicio \n3-Mostrar el número total de ejercicios y el gasto calórico total \n4-Mostrar el número de ejercicios realizados y el gasto calórico total desde una fecha \n5-Ejercicios de un determinado tipo y las calorías gastadas" +
                                        "\n6-Mostrar los ejercicios realizados de determinado tipo desde una fecha con las calorías gastadas \n7-Obtener todos los ejercicios con toda su informacion en un determinado mes y año\n8-Obtener los ejercicios realizados entre dos fechas \n9-Calcular la intensidad media de todos los ejercicios realizados \n10-Intensidad media de los ejercicios realizados desde uan fecha \n11-Informe Específico,\n12-Salir");
                                opcion = sc.nextInt();

                                switch (opcion) {
                                    case 1:
                                        System.out.println("¿Qué desea añadir? 0-Cardio 1-Fuerza 2-Flexibilidad");
                                        int eleccion = sc.nextInt();
                                        System.out.println("¿Cuál es el nombre del ejercicio?");
                                        String nombreEj = sc.next();
                                        System.out.println("¿Cuál fue la intensidad? (Recuerde,la intensidad es un numero entre 1 y 8)");
                                        int intensidad = sc.nextInt();
                                        if (intensidad < 1 || intensidad > 8) {
                                            throw new IllegalArgumentException("Número de intensidad incorrecto");
                                        }
                                        System.out.println(" ¿Cuál fue el día? ");
                                        dia = sc.nextInt();
                                        System.out.println(" ¿El mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();

                                        // Validar la fecha ingresada
                                        try {
                                            LocalDate.of(anno, mes, dia);
                                        } catch (Exception e) {
                                            throw new IllegalArgumentException("Fecha inválida");
                                        }

                                        if (eleccion == 0) {
                                            System.out.println("¿Cuánto fue la distancia recorrida? (en kilómetros) ");
                                            double distancia = sc.nextDouble();
                                            System.out.println(" y finalmente ¿De cuánto fue la duración? (en minutos) ");
                                            int duracion = sc.nextInt();
                                            Cardio ejercicio1 = new Cardio(nombreEj, duracion, intensidad, LocalDate.of(anno, mes, dia), distancia);
                                            usuarioActual.agregarEjercicioRealizado(ejercicio1);
                                        } else if (eleccion == 1) {
                                            System.out.println("¿Cuánto fue el peso levantado?");
                                            double pesoLevantado = sc.nextDouble();
                                            System.out.println("¿Cuántas repeticiones?");
                                            int repeticiones = sc.nextInt();
                                            Fuerza ejercicio1 = new Fuerza(nombreEj, intensidad, LocalDate.of(anno, mes, dia), pesoLevantado, repeticiones);
                                            usuarioActual.agregarEjercicioRealizado(ejercicio1);
                                        } else if (eleccion == 2) {
                                            System.out.println("¿Cuántas fueron las repeticiones?");
                                            int repeticiones = sc.nextInt();
                                            Flexibilidad ejercicio1 = new Flexibilidad(nombreEj, intensidad, LocalDate.of(anno, mes, dia), repeticiones);
                                            usuarioActual.agregarEjercicioRealizado(ejercicio1);
                                        }
                                        System.out.println("Ejercicio creado correctamente");
                                        break;

                                    case 2://Eliminar un ejercicio
                                        System.out.println("¿Qué ejercicio desea eliminar?");
                                        nombreborrado = sc.next();
                                        System.out.println(" ¿Cuál fue el día? ");
                                        dia = sc.nextInt();
                                        System.out.println(" ¿El mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();
                                        if (usuarioActual.eliminarEjercicio(nombreborrado, LocalDate.of(anno, mes, dia)))
                                            System.out.println("Ejercicio eliminado correctamente");
                                        else
                                            System.out.println("No se ha encontrado ese ejercicio");
                                        break;
                                    case 3:
                                        // Mostrar el número total de ejercicios y el gasto calórico total
                                        int numEjercicioes = usuarioActual.getEjerciciosRealizados().size();
                                        System.out.println("El número total de ejercicios es: " + numEjercicioes);
                                        int caloriasTotales = usuarioActual.calcularConsumoCaloricoTotal();
                                        System.out.println("El gasto calórico total es: " + caloriasTotales);
                                        break;

                                    case 4:
                                        //Mostrar en número de ejercicios realizados desde una fecha hasta la actualidad, mostrando el
                                        //gasto calórico total de esos ejercicios
                                        System.out.println(" ¿Cuál es el día? ");
                                        dia = sc.nextInt();
                                        System.out.println(" ¿El mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();
                                        ArrayList<Ejercicio> lista = usuarioActual.devolverEjercicios(LocalDate.of(anno,mes,dia),LocalDate.now());
                                        calorias = usuarioActual.calcularCaloríasEjercicio(lista);
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("El número de ejercicios realizados es " + lista.size() + " y el gasto calórico total es de " + calorias);
                                        break;


                                    case 5:
                                        //Mostrar los ejercicios realizados de un determinado tipo, mostrando las calorías gastadas en la
                                        //realización de cada uno
                                        System.out.println("¿Qué tipo de ejercicio? 0-Cardio 1-Fuerza 2-Flexibilidad ");
                                        tipoEjercicio = sc.nextInt();
                                        if (tipoEjercicio < 0 || tipoEjercicio > 2){
                                            System.out.println("Elección incorrecta");
                                            break;}
                                        lista = usuarioActual.devolverEjercicios(LocalDate.of(1900, 2, 27), LocalDate.now(), tipoEjercicio);//Se pone una fecha antigua para que salgan todos los datos ya que es imposible que un usuario haya sido creado antes de esa fecha
                                        calorias = usuarioActual.calcularCaloríasEjercicio(lista);
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("El número de ejercicios realizados de " + tipoEjercicio + " es "
                                                + lista.size() + " y el gasto calorico total es " + calorias);
                                        break;
                                    case 6:
                                        //Mostrar los ejercicios realizados de un determinado tipo desde una fecha dada hasta la actualidad,
                                        //con el gasto calórico total de ellos
                                        System.out.println(" ¿Cuál fue el día? ");
                                        dia = sc.nextInt();
                                        System.out.println(" ¿El mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();
                                        System.out.println("¿Qué tipo de ejercicio? 0-Cardio 1-Fuerza 2-Flexibilidad ");
                                        tipoEjercicio = sc.nextInt();
                                        lista = usuarioActual.devolverEjercicios(LocalDate.of(anno,mes,dia),LocalDate.now(),tipoEjercicio);
                                        for (int i = 0; i < lista.size(); i++){
                                            System.out.println(lista.get(i));
                                        }
                                        break;
                                    case 7:
                                        //Obtener todos los ejercicios, con toda su información, realizados en un determinado mes y año
                                        System.out.println(" ¿Cuál es el mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();
                                        YearMonth ym = YearMonth.of(anno, mes);
                                        lista = usuarioActual.devolverEjercicios(ym.atDay(1), ym.atEndOfMonth());
                                        for (Ejercicio ejercicio : lista) {
                                            System.out.println(ejercicio.toString());
                                        }
                                        break;
                                    case 8:
                                        //Obtener todos los ejercicios, con toda su información, realizados entre dos fechas
                                        System.out.println(" Empecemos con la fecha inicial,¿cuál fue el día? ");
                                        dia = sc.nextInt();
                                        System.out.println(" ¿El mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();

                                        System.out.println("Ahora,¿cuál fue el dia de la fecha final?");
                                        int diaF = sc.nextInt();
                                        System.out.println(" ¿el mes? ");
                                        int mesF = sc.nextInt();
                                        System.out.println(" ¿el año? ");
                                        int annoF = sc.nextInt();

                                        if (LocalDate.of(anno,mes,dia).isAfter(LocalDate.of(annoF,mesF,diaF))){
                                            System.out.println("El orden de las fechas no es correcto.");
                                            break;

                                        }
                                        lista = usuarioActual.devolverEjercicios(LocalDate.of(anno,mes,dia),LocalDate.of(annoF,mesF,diaF));
                                        for(int i = 0; i < lista.size();i++){
                                            System.out.println(lista.get(i).toString());
                                        }break;
                                    case 9:
                                        //Calcular la intensidad media de los ejercicios totales realizados
                                        ejercicios = usuarioActual.calcularPromedioIntensidad(LocalDate.of(1900,3,31));
                                        System.out.println("La intensidad de los ejercicios media es de: " + ejercicios);
                                        break;

                                    case 10:
                                        //Calcular la intensidad media de los ejercicios realizados desde una fecha
                                        System.out.println(" ¿Cuál es el día? ");
                                        dia = sc.nextInt();
                                        System.out.println(" ¿El mes? ");
                                        mes = sc.nextInt();
                                        System.out.println(" ¿y el año?");
                                        anno = sc.nextInt();
                                        ejercicios = usuarioActual.calcularPromedioIntensidad(LocalDate.of(anno,mes,dia));
                                        System.out.println("El promedio de intensidad es: " + ejercicios);
                                        break;
                                    case 11:
                                        informeEspecífico(usuarioActual);
                                        break;
                                    case 12:
                                        System.out.println("Volviendo al menú principal");
                                        break;
                                    default:
                                        System.out.println("Número introducido incorrecto");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada incorrecta. Introduce un valor válido.");
                                sc.next();
                            } catch (IllegalArgumentException | DateTimeException e) {
                                System.out.println(e.getMessage());
                            }
                        } while (opcion != 12);
                        break;
