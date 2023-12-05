package com.dam.examencorutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel del juego cuenta atrás y frases.
 */
class JuegoViewModel: ViewModel() {

    /**
     * Inicializo el juego con coroutine.
     */
    fun inicializarJuego() {
        Datos.state = State.ESPERANDO
        Datos.cuentaAtras.value = 20
        Datos.puntuacion.value = 0
        Datos.frases.clear()
        Datos.aux()
    }

    /**
     * Comprueba si la frase es verdadera o falsa.
     */
    fun comprobarFrase(respuesta: Boolean) {
        if (respuesta == Datos.fraseActual.value.verdadero) {
            Datos.puntuacion.value += 20
        } else {
            // fallo
            Datos.puntuacion.value -= 10
            if (Datos.puntuacion.value < 0) {
                Datos.puntuacion.value = 0
            }
        }
        Datos.aux()
    }

    /**
     * Cuenta atrás.
     */
    fun cuentaAtras() {
        Datos.state = State.JUGANDO
        viewModelScope.launch {
            while (Datos.cuentaAtras.value > 0 && Datos.state == State.JUGANDO) {
                delay(1000)
                Datos.cuentaAtras.value--
            }
            Datos.state = State.ESPERANDO
        }
    }

    /**
     * Asigna una frase aleatoria.
     */
    fun asignarFraseAleatoria() {
        Datos.fraseActual.value = Datos.frases.random()
    }
}