# Juego de Verdadero o Falso

Este código implementa un juego simple de Verdadero o Falso utilizando Jetpack Compose en Kotlin. El juego presenta frases y el jugador debe decidir si son verdaderas o falsas. Aquí hay una explicación del código y cómo usarlo:

## Estructura del Código
El código consta de varias partes, incluyendo la definición de datos, la lógica del juego y la interfaz de usuario.

```Datos
data class Frase(var texto: String, var verdadero: Boolean)
var frases: MutableList<Frase> = mutableListOf()
var fraseActual: MutableState<Frase> = mutableStateOf(Frase("-",true))
var cuentaAtras: MutableState<Int> = mutableStateOf(20)
var puntuacion: MutableState<Int> = mutableStateOf(0)
var state: State = State.ESPERANDO
```

**Frase**: Una clase de datos que representa una frase con un texto y una propiedad booleana que indica si es verdadera o falsa.

**frases**: Una lista mutable que contiene varias instancias de Frase.

**fraseActual**: Una variable de estado que representa la frase actual que se muestra en el juego.
cuentaAtras: Una variable de estado que cuenta hacia atrás el tiempo de juego.

**puntuacion**: Una variable de estado que almacena la puntuación del jugador.

**state**: Una enumeración que define los estados del juego (JUGANDO y ESPERANDO).

Estados del Juego
```
enum class State {
    JUGANDO, ESPERANDO
}
```
**State**: Enumeración que define los estados del juego.

Lógica del Juego
```
fun aux(){
    // ... (Introducción de frases en la lista y asignación aleatoria de una frase)
}

fun comprobarFrase(respuesta: Boolean){
    // ... (Comprobación de si la respuesta es correcta y actualización de la puntuación)
}
```
**aux()**: Función que introduce frases en la lista y asigna una frase aleatoria.

**comprobarFrase(respuesta: Boolean)**: Función que verifica si la respuesta del jugador es correcta y actualiza la puntuación.

Interfaz de Usuario
```
@Composable
fun IU(){
    // ... (Creación de la interfaz de usuario, incluyendo la cuenta atrás, la frase, la puntuación y el botón de inicio)
}
```
**IU()**: Función componible que crea la interfaz de usuario del juego.

## Uso del Juego
1. **Inicialización de Frases**: Antes de comenzar el juego, llama a la función aux() para introducir frases en la lista.

2. **Interfaz de Usuario**: Utiliza la función IU() para mostrar la interfaz de usuario del juego, que incluye la cuenta atrás, la frase actual, la puntuación y el botón de inicio.

3. **Inicio del Juego**: Haz clic en el botón "Start" para iniciar el juego. Solo puedes hacer clic en el botón cuando el juego está en el estado ESPERANDO. La cuenta atrás comenzará, y debes seleccionar "Verdadero" o "Falso" para cada frase mostrada.

4. **Puntuación**: La puntuación se actualiza según tus respuestas. Cada respuesta correcta suma 10 puntos, y cada respuesta incorrecta resta 5 puntos. La puntuación mínima es 0.

5. **Final del Juego**: Una vez que la cuenta atrás llega a cero, el juego vuelve al estado ESPERANDO y puedes reiniciarlo presionando nuevamente el botón "Start".

#### ¡Disfruta jugando al Verdadero o Falso!