package com.dam.examencorutinas

import androidx.lifecycle.ViewModel

/**
 * ViewModel del juego cuenta atrás y frases.
 */
class JuegoViewModel: ViewModel() {

    /**
     * Inicializo el juego.
     */
    fun inicializarJuego() {
        Datos.state = State.ESPERANDO
        Datos.cuentaAtras.value = 20
        Datos.puntuacion.value = 0
        Datos.frases.clear()
        Datos.aux()
    }



}