package com.dam.examencorutinas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// crea toda la interfaz de usuario
@Composable
fun IU(miViewModel:JuegoViewModel){
    Column {
        miViewModel.inicializarJuego()
        cuentaAtras( miViewModel = miViewModel)
        frase(miViewModel = miViewModel)
        fraseVerdaderaFalsa( miViewModel = miViewModel)
        puntuacion(miViewModel = miViewModel)
        botonEmpezar( miViewModel = miViewModel)
    }
}

/**
 * Creación del boton empezar
 */
@Composable
fun botonEmpezar(miViewModel:JuegoViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ){
        Button(
            onClick = {
                if (Datos.state==State.ESPERANDO) {
                    miViewModel.cuentaAtras()
                }
            },
            modifier = Modifier
                .padding(end = 10.dp)
        ) {
            Text(text = "Empezar")
        }
    }
}

/**
 * Creación del texto de la cuenta atras
 */
@Composable
fun cuentaAtras(miViewModel:JuegoViewModel){
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
            text = "${Datos.cuentaAtras.value.toString()}",
            fontSize = 50.sp
        )
    }
}
/**
 * Asignar una frase aleatoria y crear los botones verdadero y falso
 */
@Composable
fun fraseVerdaderaFalsa(miViewModel:JuegoViewModel){
    miViewModel.asignarFraseAleatoria()
    botonesFalsoVerdadero(miViewModel = miViewModel)
}

/**
 * Creacion de la frase
 */
@Composable
fun frase(miViewModel:JuegoViewModel){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ){
        Text(text ="Frase: ${Datos.fraseActual.value.texto}", fontSize = 20.sp)
    }
}

/**
 * Creacion de los botones verdadero y falso
 */
@Composable
fun botonesFalsoVerdadero(miViewModel: JuegoViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ){
        // boton verdadero
        botonFalsoVerdadero(true, miViewModel = miViewModel)
        // boton falso
        botonFalsoVerdadero(false, miViewModel = miViewModel)
    }
}

/**
 * Boton que dependiendo del parametro crea el boton verdadero o falso
 */
@Composable
fun botonFalsoVerdadero(respuesta: Boolean, miViewModel:JuegoViewModel){
    Button(
        onClick = {
            if (Datos.state== State.JUGANDO) {
                miViewModel.comprobarFrase(respuesta)
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
fun puntuacion(miViewModel:JuegoViewModel){
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
            text = "Puntiación: ${Datos.puntuacion.value}",
            fontSize = 20.sp
        )
    }
}

