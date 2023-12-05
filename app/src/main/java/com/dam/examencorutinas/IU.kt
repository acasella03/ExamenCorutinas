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

// crea toda la interfaz de usuario
@Composable
fun IU(miViewModel:JuegoViewModel){
    Column {
        cuentaAtras( miViewModel = JuegoViewModel())
        fraseVerdaderaFalsa( miViewModel = JuegoViewModel())
        puntuacion(miViewModel = JuegoViewModel())
        botonEmpezar( miViewModel = JuegoViewModel())
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
            text = "Cuenta atrás: ${miViewModel.cuentaAtras()}",
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
    botonesFalsoVerdadero()
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
        Text(text ="Frase: ${miViewModel.asignarFraseAleatoria()}", fontSize = 20.sp)
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
        botonFalsoVerdadero(true, miViewModel = JuegoViewModel())
        // boton falso
        botonFalsoVerdadero(false, miViewModel = JuegoViewModel())
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

