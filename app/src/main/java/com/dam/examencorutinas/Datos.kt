package com.dam.examencorutinas

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

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