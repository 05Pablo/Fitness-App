/**
 * Clase que le contiene los variables que puede tener un usuario,es decir,tiene los datos de este,el nombre,el nivel,la edad,el peso y el un array con el número de ejercicios realizados
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario implements Serializable {

    private String nombre; // Nombre del usuario
    private int edad; // Edad del usuario
    private double pesoUsuario; // Peso del usuario
    private Nivel nivel; // Nivel de entrenamiento del usuario
    private ArrayList <Ejercicio> ejerciciosRealizados; // Lista de ejercicios realizados por el usuario

    // Métodos getters y setters para acceder y modificar los atributos privados de la clase

    /**
     *
     * @return funcíón get del nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPesoUsuario() {
        return pesoUsuario;
    }

    public void setPesoUsuario(double pesoUsuario) {
        this.pesoUsuario = pesoUsuario;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }


    public ArrayList<Ejercicio> getEjerciciosRealizados() {
        return ejerciciosRealizados;
    }

    public void setEjerciciosRealizados(ArrayList<Ejercicio> ejerciciosRealizados) {
        this.ejerciciosRealizados = ejerciciosRealizados;
    }

    /**
     * Constructor de la clase Usuario
     * @param nombre Nombre del usuario
     * @param edad Edad del usuario
     * @param pesoUsuario Peso del usuario
     * @param nivel Nivel que tiene el usuario
     */
    public Usuario(String nombre, int edad, double pesoUsuario, Nivel nivel) {
        this.nombre = nombre;
        this.edad = edad;
        this.pesoUsuario = pesoUsuario;
        this.nivel = nivel;
        ejerciciosRealizados = new ArrayList<Ejercicio>();
    }

    /**
     * Método para agregar un ejercicio realizado por el usuario a la lista
     * @param ejercicio parámetro que se le pasa desde el main,el ejercicio que ha realizado el usuario
     */
    public void agregarEjercicioRealizado(Ejercicio ejercicio){
        ejerciciosRealizados.add(ejercicio);
    }

    /**
     * Método para eliminar un ejercicio de la lista de ejercicios realizados por nombre y fecha, se pide la fecha para no eliminar otro ejercicio con el mismo nombre
     * @param nombre Nombre del ejercicio que se quiere eliminar
     * @param fecha Fecha del ejercicio
     */
    public Boolean eliminarEjercicio (String nombre,LocalDate fecha){
        return ejerciciosRealizados.removeIf(e ->
            e.getNombre().equalsIgnoreCase(nombre) && e.getFecha().isEqual(fecha));
    }

    /**
     * Método para calcular el promedio de intensidad de todos los ejercicios realizados por el usuario
     * @return devuelve la intensitad total una vez haber recorrido mediante el bucle todo el array y haber ido sumando todos los valores de intensidades,después lo divide entre la cantidad de ejercicios para realizar la media
     */
    public int calcularPromedioIntensidad(){
        int intensidadTotal = 0;
        for(Ejercicio e : ejerciciosRealizados){
            intensidadTotal += e.getIntesidad();
        }
        return intensidadTotal/ejerciciosRealizados.size();
    }

    /**
     * Método para calcular el consumo calórico total de todos los ejercicios realizados por el usuario
     * @return Se suma el número total de calorías quemadas al recorrer el array y se convierte en un int para que no de error al dividir un double con un int
     */
    public int calcularConsumoCaloricoTotal(){
        int caloriasTotales = 0;
        for (Ejercicio e: ejerciciosRealizados){
            caloriasTotales += (int) e.calcularCalorias(pesoUsuario);
        }
        return caloriasTotales;
    }

    /**
     * Método para devolver una lista de ejercicios realizados por el usuario en un rango de fechas
     * @param fechaI fecha inicial proporcionada por el usuario
     * @param fechaT fecha final proporcionada por el usuario
     * @return devuelve el array creado para almacenar los ejercicios que cumplen la condicion de estar entre las fechas
     */
    public ArrayList<Ejercicio> devolverEjercicios(LocalDate fechaI,LocalDate fechaT){ // otro igual con el tipo de ejercico como parametro de entrada/ calcular calorias de ejercicios con parámetro de entrada
        ArrayList <Ejercicio> listaEjercicios = new ArrayList<>();
        for (Ejercicio e:ejerciciosRealizados){ // se recorre el bucle con un for each
            if (!e.getFecha().isBefore(fechaI) && !e.getFecha().isAfter(fechaT)){ //se comprueba si la fecha del ejercicio esta después de la primera fecha y antes de la fecha final
                listaEjercicios.add(e);
            }
        }
        return listaEjercicios;
    }

    /**
     * Método para calcular el promedio de intensidad de los ejercicios realizados después de una fecha especificada
     * @param fecha fecha proporcionada por el usuario
     * @return mismo funcionamiento que el de calcular calorias
     */
    public double calcularPromedioIntensidad (LocalDate fecha){
        if (ejerciciosRealizados.isEmpty())
            return 0;
        int intensidadTotal = 0;
        for(Ejercicio e : ejerciciosRealizados){
            intensidadTotal += e.getIntesidad();
        }
        return intensidadTotal / ejerciciosRealizados.size();
    }


    /**
     * // Método para devolver una lista de ejercicios realizados por el usuario en un rango de fechas y de un tipo específico
     * @param fechaI fecha proporcionada por el usuario
     * @param fechaT fecha proporcionada por el usuario
     * @param tipoEjercicio tipo de ejercicio proporcionado por el usuario (lo ha escogido mediante un int ya que en el menú le sale de la forma 0-Cardio 1-Fuerza 2-Flexibilidad)
     * @return devuelve la lista de ejercicios que cumplen las características de estar antes de la fecha final, después de la fecha inicial o el mismo día de la fecha
     */

    public ArrayList<Ejercicio> devolverEjercicios (LocalDate fechaI,LocalDate fechaT,int tipoEjercicio){
        ArrayList <Ejercicio> listaEjercicios = new ArrayList<>();
        for (Ejercicio e:ejerciciosRealizados){
                if ((fechaI.isBefore(e.getFecha()) || fechaI.isEqual(e.getFecha())) && (fechaT.isAfter(e.getFecha()) || fechaT.isEqual(e.getFecha())) && tipoEjercicio == 0 && e.getClass() == Cardio.class ){
                    listaEjercicios.add((Cardio)e); // se pone el (Cardio para que se asigne que tipo de ejercicio es ya qué no se ha asignado en ningún momento)
                } else if ((fechaI.isBefore(e.getFecha()) || fechaI.isEqual(e.getFecha())) && (fechaT.isAfter(e.getFecha()) || fechaT.isEqual(e.getFecha())) && tipoEjercicio == 1 && e.getClass() == Fuerza.class) {
                    listaEjercicios.add((Fuerza)e);
                } else if ((fechaI.isBefore(e.getFecha()) || fechaI.isEqual(e.getFecha())) && (fechaT.isAfter(e.getFecha()) || fechaT.isEqual(e.getFecha())) && tipoEjercicio == 2 && e.getClass() == Flexibilidad.class) {
                    listaEjercicios.add((Flexibilidad)e);
                }
        }
        return listaEjercicios;
    }


    /**
     * Funcion complementaria de otras funciones, Método para calcular el consumo calórico total de una lista de ejercicios dados
     * @param ejdado Arraylist de ejercicios que se le pasa como parametro de entrada para recorrer y que se le sumen las calorías totales que hay en ese array
     * @return devuleve las calorías
     */
    public int calcularCaloríasEjercicio (ArrayList<Ejercicio> ejdado){ //calorías por ej
        int calorias = 0;
        for (Ejercicio e : ejdado){
            calorias += e.calcularCalorias(pesoUsuario);
        }
        return calorias;
}

    /**
     * / Método para representar la información del usuario y sus ejercicios realizados como una cadena de texto
     * @return
     */
    @Override
    public String toString() {
        String salida;

        salida = "Nombre del usuario: " + nombre + '\'' + "Edad: " + edad + '\n' +"Peso:" + pesoUsuario +
                "\nCon el nivel de: " + nivel + "\nLos ejercicios realizados son:";
        for (Ejercicio ejercicio : ejerciciosRealizados){
            salida += ejercicio.toString();
        }
        return salida;
    }

}