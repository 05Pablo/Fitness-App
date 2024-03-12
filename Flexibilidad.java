/**
 * Clase que heredará de Ejercicio,se utilizará para calcular las calorías cuando el usuario realiza un ejercicio de este tipo
 */
import java.io.Serializable;
import java.time.LocalDate;

public class Flexibilidad extends Ejercicio implements Serializable {


    private int repeticiones; // variable exclusiva de la clase Flexibilidad

    public int getRepeticiones() {
        if(repeticiones < 0)
            System.out.println("No puedes poner una cantidad de repeticiones negativa");
            return repeticiones;
    }

    /**
     * Constructor de un ejercicio de Flexiblidad
     * @param nombre Nombre del usuario
     * @param intesidad Intensidad del ejercicio
     * @param fecha Fecha en la que se realizó
     * @param repeticiones Número de repeticiones totales
     */
    public Flexibilidad(String nombre, int intesidad, LocalDate fecha,int repeticiones) {
        // Llama al constructor de la superclase Ejercicio
        super(nombre, intesidad, fecha);
        // Inicializa el atributo específico de Flexibilidad
        if (repeticiones < 0)
            System.out.println("No puedes introducir un valor negativo");
        this.repeticiones = repeticiones;
    }

    /**
     * Método para calcular las calorías quemadas durante el ejercicio de flexibilidad
     * @param pesoUsuario Peso del usuario que se le tiene que pasar ya qué las clases que heredarán no lo tendrán
     * @return Devuelve el valor de las calorías quemadas
     */
    @Override
    public double calcularCalorias(double pesoUsuario) {
        // Fórmula para calcular el gasto calórico utilizando el MET (Metabolic Equivalent of Task)
       double MET =  repeticiones * 0.1;
       return 0.0175 * pesoUsuario * MET;
    }
}
