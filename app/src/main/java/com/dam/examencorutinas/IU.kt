@file:OptIn(ExperimentalMaterial3Api::class)
package com.dam.examencorutinas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * variable global
 */
data class Frase(var texto: String, var verdadero: Boolean)
var frases: MutableList<Frase> = mutableListOf()
var fraseActual: MutableState<Frase> = mutableStateOf(Frase("-",true))
var cuentaAtras: MutableState<Int> = mutableStateOf(20)
var puntuacion: MutableState<Int> = mutableStateOf(0)
var state: State = State.ESPERANDO

/**
 * estados del juego
 */
enum class State {
    JUGANDO, ESPERANDO
}

/**
 * utilización dentro de un función composable cualquiera
 */
fun aux(){
    // introducir frases en la lista
    frases.add(Frase("el torneo de rugby cinco naciones, ahora es seis naciones",true))
    frases.add(Frase("en el cielo hay cinco estrellas",false))
    frases.add(Frase("el dia cinco de diciembre del 2023 es martes",true))
    frases.add(Frase("cinco más cinco son diez",true))
    frases.add(Frase("dos mas dos son cinco",false))
    frases.add(Frase("los elefantes tienen cinco patas",false))
    frases.add(Frase("las estaciones climáticas son cinco",false))
    frases.add(Frase("tenemos cinco dedos los humanos",true))
    frases.add(Frase("cinco días tiene la semana sin el Domingo y el Sábado",true))
    frases.add(Frase("una gallina pesa menos que cinco toneladas",true))

    // asignar una frase aleatoria
    fraseActual.value = frases.random()

}

// crea toda la interfaz de usuario
@Composable
fun IU(){
    Column {
        cuentaAtras()
        fraseVerdaderaFalsa()
        puntuacion()
        botonEmpezar()
    }
}

// crea el boton start
@Composable
fun botonEmpezar(){
    // para crear el objeto que nos permite hacer una corrutina
    val iuScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 20.dp, 0.dp, 50.dp)
    ) {
        Button(
            onClick = {
                // para que solo se pueda dar al boton una vez
                if(state == State.ESPERANDO){
                    // reiniciar la cuenta atras y la puntuacion
                    cuentaAtras.value = 20
                    puntuacion.value = 0
                    // iniciar la cuenta atras
                    iuScope.launch {
                        state = State.JUGANDO
                        while (cuentaAtras.value > 0){
                            delay(1000)
                            cuentaAtras.value -= 1
                        }
                        state = State.ESPERANDO
                    }
                    aux()
                }
            }
        ) {
            Text(text = "EMPEZAR")
        }
    }
}
// Crea el texto de la cuenta atras y el numero de la cuenta atras
@Composable
fun cuentaAtras(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 30.dp, 0.dp, 50.dp)
    ) {
        Text(text = "Cuenta atras:")
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ){
        Text(
            text = cuentaAtras.value.toString(),
            fontSize = 50.sp
        )
    }
}

// crea la frase y los botones verdadero y falso
@Composable
fun fraseVerdaderaFalsa(){
    frase()
    botonesFalsoVerdadero()
}

/**
 * Creacion de la frase
 */
@Composable
fun frase(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ){
        Text(text = fraseActual.value.texto)
    }
}

/**
 * Creacion de los botones verdadero y falso
 */
@Composable
fun botonesFalsoVerdadero(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ){
        // boton verdadero
        botonFalsoVerdadero(true)
        // boton falso
        botonFalsoVerdadero(false)
    }
}

/**
 * Boton que dependiendo del parametro crea el boton verdadero o falso
 */
@Composable
fun botonFalsoVerdadero(respuesta: Boolean){
    Button(
        onClick = {
            if (state == State.JUGANDO) {
                comprobarFrase(respuesta)
            }
        },
        modifier = Modifier
            .padding(end = 10.dp)
    ) {
        Text(text = if (respuesta==true) "Verdadero" else "Falso")
    }
}

/**
 * Creación del texto de la puntuacion y el numero de la puntuacion
 */
@Composable
fun puntuacion(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text = "Puntuacion: ",
            fontSize = 20.sp
        )
        Text(
            text = puntuacion.value.toString(),
            fontSize = 20.sp
        )
    }
}

/**
 * Comprobacion de si la frase es verdadera o falsa
 */
fun comprobarFrase(respuesta: Boolean){
    if (respuesta == fraseActual.value.verdadero){
        puntuacion.value += 20
    } else {
        // fallo
        puntuacion.value -= 10
        if (puntuacion.value < 0){
            puntuacion.value = 0
        }
    }
    aux()
}