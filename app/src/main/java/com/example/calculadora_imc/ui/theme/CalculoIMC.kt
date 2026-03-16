package com.example.calculadora_imc.ui.theme

import androidx.compose.ui.graphics.Color

fun calcularIMC(altura: Double, peso: Double): Double {
    var alturaMetros = altura / 100
    var result = peso / (alturaMetros * alturaMetros)
    return result
}

fun definirCategoria(imc: Double): String {
    return when {
        imc in 0.0 .. 18.4 -> "Abaixo do Peso"
        imc in 18.5 .. 24.9 -> "Peso Ideal"
        imc in 25.0 .. 29.9 -> "Sobrepeso"
        imc in 30.0 .. 34.9 -> "Obesidade I"
        imc in 35.0 .. 39.9 -> "Obesidade II"
        else -> "Obesidade III"

    }
}

fun definirCorCategoria(imc: Double): Color {
    return when {
        imc in 0.0 .. 18.4 -> Color.Red
        imc in 18.5 .. 24.9 -> Color.Green
        imc in 25.0 .. 29.9 -> Color.LightGray
        imc in 30.0 .. 34.9 -> Color.Red
        imc in 35.0 .. 39.9 -> Color.Red
        else -> Color.Red
    }
}