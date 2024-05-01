# FitnessApp

Aplicación de consola en Java para registrar usuarios y los ejercicios que realizan, calcular el gasto calórico y generar informes.

**Autor:** Pablo Gallego García

## Funcionalidades

- Registro e inicio de sesión de usuarios (nombre, edad, peso y nivel: `PRINCIPIANTE`, `INTERMEDIO` o `AVANZADO`).
- Tres tipos de ejercicio: **Cardio**, **Fuerza** y **Flexibilidad**.
- Cálculo de calorías por ejercicio y del gasto calórico total.
- Consultas por fecha, por tipo de ejercicio y por mes y año.
- Intensidad media de los ejercicios (total o desde una fecha).
- Persistencia de los datos en un archivo (`fitnessapp.dat`).
- Informes en texto: uno general y otro específico por usuario.

## Estructura del proyecto

| Archivo | Descripción |
|---|---|
| `Main.java` | Clase principal: menús, lectura de datos por teclado, guardado/carga e informes. |
| `Usuario.java` | Datos del usuario y su lista de ejercicios, con los métodos de consulta y cálculo. |
| `RegistroUsuarios.java` | Lista de usuarios registrados; añadir y buscar por nombre. |
| `Ejercicio.java` | Clase abstracta base (nombre, intensidad, fecha). |
| `Cardio.java` | Ejercicio con distancia y duración. |
| `Fuerza.java` | Ejercicio con peso levantado y repeticiones. |
| `Flexibilidad.java` | Ejercicio con repeticiones. |
| `Nivel.java` | Enumeración con los niveles de usuario. |
| `IntensidadIncorrectaException.java` | Excepción para intensidades fuera de rango. |

Todas las clases de datos implementan `Serializable` para poder guardarse en disco.

## Requisitos

- JDK 8 o superior (se usa `java.time`).

## Compilar y ejecutar

Desde la carpeta que contiene los `.java`:

```bash
javac *.java
java Main
```

## Uso

### Menú principal

1. Registrar usuario
2. Iniciar sesión
3. Leer datos (carga `fitnessapp.dat`)
4. Guardar datos (escribe `fitnessapp.dat`)
5. Informe general
6. Salir

> Los datos solo se conservan entre ejecuciones si se guardan con la opción 4 y se cargan con la 3 al volver a abrir el programa. Cargar sustituye los usuarios que haya en memoria.

### Menú de usuario (tras iniciar sesión)

1. Añadir ejercicio
2. Eliminar un ejercicio (por nombre y fecha)
3. Número total de ejercicios y gasto calórico total
4. Número de ejercicios y gasto calórico desde una fecha
5. Ejercicios de un tipo y calorías gastadas
6. Ejercicios de un tipo desde una fecha
7. Ejercicios de un mes y año
8. Ejercicios entre dos fechas
9. Intensidad media de todos los ejercicios
10. Intensidad media desde una fecha
11. Informe específico
12. Salir

Las fechas se introducen en tres pasos: día, mes y año. La intensidad es un número entre 1 y 8. Al añadir un ejercicio, el tipo se elige con `0` (Cardio), `1` (Fuerza) o `2` (Flexibilidad).

## Cálculo de calorías

En todos los casos:

```
calorías = 0.0175 × pesoUsuario × MET
```

Y el valor `MET` depende del tipo de ejercicio:

| Tipo | MET |
|---|---|
| Cardio | `distancia × intensidad × duración` |
| Fuerza | `pesoLevantado × 0.1 × intensidad × repeticiones` |
| Flexibilidad | `repeticiones × 0.1` |

Se trata de una aproximación propia de la aplicación, no de la fórmula real de MET.

## Archivos generados

El programa crea estos archivos en la carpeta desde la que se ejecuta:

- `fitnessapp.dat`: datos guardados (opción 4 del menú principal).
- `Informe_general_AAAA_M_D.txt`: tabla con todos los usuarios, su número de ejercicios, nivel e intensidad media.
- `informe_<usuario>_AAAA_MES_D_.txt`: listado de ejercicios de un usuario con calorías totales e intensidad media.

Estos archivos no se suben al repositorio (ver `.gitignore`).

## Capturas de funcionamiento
# Creacion de usuario
<img width="940" height="317" alt="image" src="https://github.com/user-attachments/assets/ad3baf99-c3c8-4e4a-aa7f-594086aebffb" />

# Logarse con un usuario
<img width="940" height="467" alt="image" src="https://github.com/user-attachments/assets/d403428b-66f1-411e-8b16-22486ff8eb49" />

# Añadir un ejercicio
<img width="940" height="665" alt="image" src="https://github.com/user-attachments/assets/0e583c94-55de-46e0-ad03-5aa5236664ee" />

# Eliminar ejercicio
<img width="940" height="465" alt="image" src="https://github.com/user-attachments/assets/2554ed7c-515f-4e82-8082-ad8ab14a1e81" />

# Numero total de ejercicios
<img width="940" height="369" alt="image" src="https://github.com/user-attachments/assets/1a13502d-423d-457e-994b-fd0e6415d75a" />

# Numero de ejercicios desde una fecha
<img width="940" height="463" alt="image" src="https://github.com/user-attachments/assets/91bf64dc-654c-48e4-8c6d-94e18ade8aae" />

# Numero de ejercicios segun el tipo
<img width="940" height="417" alt="image" src="https://github.com/user-attachments/assets/de2142fe-4e26-4feb-a6b8-044d6142f1f9" />
<img width="940" height="385" alt="image" src="https://github.com/user-attachments/assets/a40b7d77-acdb-4c61-b751-079aaa5b3a08" />

# Numero de ejercicios segun el tipo desde una fecha
<img width="940" height="475" alt="image" src="https://github.com/user-attachments/assets/87a9dd72-d398-4169-8227-0d125370da0c" />

# Obtener todos los ejercicios en un mes
<img width="940" height="394" alt="image" src="https://github.com/user-attachments/assets/7a3a20a4-a023-40a9-b0db-0c4de8f66617" />

# Todos los ejercicios entre 2 fechas
<img width="940" height="548" alt="image" src="https://github.com/user-attachments/assets/3af2c7de-a187-4572-ba33-77c9565f6e48" />

# Media de ejercicios realizados
<img width="940" height="456" alt="image" src="https://github.com/user-attachments/assets/9064437a-13a1-4e0b-9c93-d8636df5a847" />
<img width="940" height="688" alt="image" src="https://github.com/user-attachments/assets/2c8613f2-bba2-4198-8fcd-f3cdb172609e" />
<img width="940" height="329" alt="image" src="https://github.com/user-attachments/assets/d9a7be2c-dca1-49e7-8b13-668971e2130f" />
 
# Intensidad media de los ejercicios desde una fecha
<img width="940" height="425" alt="image" src="https://github.com/user-attachments/assets/e0cdc7ab-ed8d-4b96-b3f2-c83d72ecad45" />

# Informe especifico
<img width="940" height="323" alt="image" src="https://github.com/user-attachments/assets/ec20b1b4-1bc0-4b91-8b27-e78c20ef3986" />
<img width="940" height="381" alt="image" src="https://github.com/user-attachments/assets/0a039c76-e234-4331-93cd-41664e8e0a75" />

# Salir de ese submenu
<img width="940" height="506" alt="image" src="https://github.com/user-attachments/assets/423ccb37-c57f-40de-b5d3-f56fcdcb925b" />

# Guardar los datos de los usuarios
<img width="886" height="277" alt="image" src="https://github.com/user-attachments/assets/d8354c98-173c-405a-8efc-0137f8b41df2" />
<img width="940" height="154" alt="image" src="https://github.com/user-attachments/assets/072b8ecd-2753-4502-a5e0-ff0f48d32f70" />

# Informe general
<img width="753" height="308" alt="image" src="https://github.com/user-attachments/assets/2904a85d-fd69-4154-a619-49804cc19d96" />
<img width="940" height="238" alt="image" src="https://github.com/user-attachments/assets/6105d6fa-cc54-40a1-883f-9ee7c7923a7f" />

# Leemos los datos guardados de la opcion 4, pero si falta algun ejercicio nos genera el informe mal
<img width="789" height="584" alt="image" src="https://github.com/user-attachments/assets/6ff5a482-292a-4bce-814d-11c6a2c02d12" />

# Una vez añadimos todos los datos nos lo genera bien
<img width="695" height="269" alt="image" src="https://github.com/user-attachments/assets/b7d24d43-09c0-4e0d-a46f-83af49159fcc" />
<img width="940" height="221" alt="image" src="https://github.com/user-attachments/assets/5085c5be-0d13-48c5-94cb-c73ae07cab6f" />

# Nos salimos
<img width="667" height="300" alt="image" src="https://github.com/user-attachments/assets/55acfb7e-c564-48da-a94b-dab042aa5d50" />


