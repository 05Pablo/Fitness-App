/**
 * Clase abstracta para representar un ejercicio genérico con los parámetros del nombre,la intensidad y la feccha
 */

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Ejercicio implements Serializable {
    protected String nombre; // Nombre del ejercicio
    protected int intesidad; // Nivel de intensidad del ejercicio (1-8)
    protected LocalDate fecha; // Fecha en que se realizó el ejercicio

    /**
     * Constructor del ejercicio
     * @param nombre nombre del usuario
     * @param intesidad valor de la intensidad
     * @param fecha fecha en la que se realizó el ejercicio
     */
    public Ejercicio(String nombre, int intesidad, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        // Verifica que la intensidad esté en el rango correcto (1-8) y la asigna
        if (intesidad >= 1 && intesidad <= 8)
        this.intesidad = intesidad;
    }

    // Getters y setters para los atributos del ejercicio
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntesidad() {
        return intesidad;
    }

    public void setIntesidad(int intesidad) {
        if (intesidad >= 1 && intesidad <= 8)
            this.intesidad = intesidad;
    }




    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Método abstracto para calcular las calorías quemadas durante el ejercicio
     * @param peso peso del usuario que se le tiene que pasar ya que las clases que heredarán no lo tendrán
     * @return devolverá un double en las clases hijas
     */
    public abstract double calcularCalorias(double peso);

    /**
     *   Método toString para representar el ejercicio como una cadena de texto
     * @return devolverá la cadena de texto
     */


    @Override
    public String toString() {
        return "El usuario ha hecho el ejercicio " + nombre + " con una intensidad de " + intesidad +
                " en la fecha de " + fecha;
    }
}
