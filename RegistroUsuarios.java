/**
 * Clase encargada de registrar nuevos usuarios
 */

import java.io.Serializable;
import java.util.ArrayList;

public class RegistroUsuarios implements Serializable {

    ArrayList <Usuario> usuarios; // Lista de usuarios

    // Constructor de la clase

    /**
     * usuarios inicializa el array
     */
    public RegistroUsuarios() {
        usuarios = new ArrayList<Usuario>(); // Inicializa la lista de usuarios
    }
    // Método para agregar un usuario al registro


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuario Valor que se le pasa desde el main de tipo Usuario que tiene toda la información que le ha dado el usuario
     */
    public void agregarUsuario(Usuario usuario){
        // Verifica si el usuario ya existe en el registro
        if (usuarios.contains(usuario)) // Imprime un mensaje si el usuario ya existe
            System.out.println("El usuario ya existe");
        else
            usuarios.add(usuario); // Agrega el usuario a la lista si no existe
    }
    // Método para buscar un usuario por su nombre en el registro

    /**
     *
     * @param nombre String que hace referencia al nombre del usuario con el que se va a buscar en la lista
     * @return en caso de que una vez recorrido el Array si encuentra a un usuario con el mismo nombre que el del parámetro de entrada devuelve los datos de ese mismo usuario,en caso de que no lo encuentre devuelve null
     */
    public Usuario buscarUsuario(String nombre){
        // Itera sobre la lista de usuarios
        for (Usuario u: usuarios){
            // Compara el nombre del usuario actual con el nombre buscado
            if (u.getNombre().equals(nombre))
                return u; // Devuelve el usuario si se encuentra
        }
        return null; // Devuelve null si el usuario no se encuentra en el registro
    }
}
