/**
 *Clase fuerza que hereda de Ejercicio en la cual se van a calcular las calorías, el peso levantado y el gasto calórico del usuario al hacer ejercicios de fuerza
 */

import java.io.Serializable;
import java.time.LocalDate;

public class Fuerza extends  Ejercicio implements Serializable {

    // Atributos específicos para ejercicios de fuerza
    private double pesoL;
    private int repeticiones;

    /**
     *  Constructor de la clase Fuerza
     * @param nombre nombre del usuario
     * @param intesidad intensidad del ejercicio
     * @param fecha fecha en la que se realizó el ejercicio
     * @param peso peso levantado
     * @param repeticiones número de repeticiones
     */
    public Fuerza(String nombre, int intesidad, LocalDate fecha,double peso,int repeticiones) {
        // Llama al constructor de la superclase Ejercicio
        super(nombre, intesidad, fecha);
        // Inicializa los atributos específicos de Fuerza
        this.pesoL = peso;
        // Verifica si el número de repeticiones es válido (mayor que 0)
        if(repeticiones > 0){
            this.repeticiones = repeticiones;
        }
    }
    // Métodos getter y setter para el peso levantado
    public double getPeso() {
        return pesoL;
    }

    public void setPeso(double peso) {
        this.pesoL = peso;
    }
    // Métodos getter y setter para el número de repeticiones
    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    /**
     *   Método para calcular las calorías quemadas durante el ejercicio de fuerza
     * @param pesoUsuario Parametro que se le pasa de entrada ya que esta clase no lo tiene, contiene el peso del usuario
     * @return
     */
    @Override
    public double calcularCalorias(double pesoUsuario) {
        // Método para calcular el gasto calórico utilizando el MET (Metabolic Equivalent of Task)
        double MET = pesoL * 0.1 * intesidad * repeticiones;
        double calorias = 0.0175 * pesoUsuario * MET;
        return calorias;
    }


    /**
     *  Método para calcular el peso total levantado durante el ejercicio de fuerza
     * @return devuelve el peso total levantado
     */
    public double calcularPesoTotal(){
        return pesoL * repeticiones;
    }
}
