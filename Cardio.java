

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase cardio de que hereda de ejercicio
 */
public class Cardio extends Ejercicio implements Serializable {
    // Atributos específicos de los ejercicios de tipo Cardio
    private double distancia; // Distancia recorrida durante el ejercicio (en kilómetros)
    private double duracion; // Duración del ejercicio (en horas)

    /**
     * Constructor de la clase cardio que contiene el super de Ejercicio
     * @param nombre NOmbre del usuario
     * @param duracion Duración del ejercicio de cardio
     * @param intesidad Intensidad del ejercicio
     * @param fecha Fecha de realización
     * @param distancia Distancia recorrida
     */

    public Cardio(String nombre,double duracion,int intesidad, LocalDate fecha,double distancia) {
        // Llama al constructor de la clase padre (Ejercicio) para inicializar los atributos comunes
        super(nombre, intesidad, fecha);
        // Verifica que la distancia y la duración sean valores válidos y los asigna
        if (distancia > 0 && duracion > 0){
            this.duracion= duracion;
            this.distancia = distancia;
        }
    }

    // Getters y setters para los atributos específicos de los ejercicios de tipo Cardio
    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        if (distancia > 0)
            this.distancia = distancia;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        if (duracion > 0)
            this.duracion = duracion;
    }


    /**
     * // Implementación del método abstracto calcularCalorias() definido en la clase Ejercicio
     * @param pesoUsuario Peso del usuario que se le tiene que pasar ya que las clases que heredarán no lo tendrán
     * @return La cuenta de las calorías
     */
    @Override
    public double calcularCalorias(double pesoUsuario) {
        // Calcula el MET (Metabolic Equivalent of Task) basado en la distancia, la intensidad y la duración del ejercicio
        double MET = distancia * intesidad * duracion;
// Calcula las calorías quemadas usando la fórmula MET * 0.0175 * pesoUsuario
        double calorias = 0.0175 * pesoUsuario * MET;

        return calorias;
    }
}
