package com.example.calculadora_imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora_imc.ui.theme.Calculadora_IMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculadora_IMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraIMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun CalculadoraIMCScreen(modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }

    var peso by remember {
        mutableStateOf("")
    }

    var imc by remember {
        mutableStateOf(0.0)
    }

    var classificacao = when {
        imc in 0.0 .. 18.4 -> "Abaixo do Peso"
        imc in 18.5 .. 24.9 -> "Peso Ideal"
        imc in 25.0 .. 29.9 -> "Sobrepeso"
        imc in 30.0 .. 34.9 -> "Obesidade I"
        imc in 35.0 .. 39.9 -> "Obesidade II"
        else -> "Obesidade III"

    }

    fun calcularIMC(altura: Double, peso: Double): Double {
        var alturaMetros = altura / 100
        var result = peso / (alturaMetros * alturaMetros)
        return result
    }

    var resultadoNumero by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),

        ) {
        // header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC Logo",
                modifier = Modifier.size(80.dp)
            )

            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            // form
            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .size(300.dp)
                    .offset(y = (-30).dp),

                // .fillMaxWidth()
                // .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),

                // shape = CircleShape,
                // border = BorderStroke(width = 4.dp, Color.Black)

            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,


                    ) {
                    Text(
                        text = "Seus Dados",
                        fontSize = 24.sp,
                        color = colorResource(R.color.cor_app),
                        fontWeight = FontWeight.Bold
                    )


                    OutlinedTextField(
                        value = altura,
                        onValueChange = {
                            altura = it
                        },
                        placeholder = {
                            Text(text = "Altura")
                        },
                        shape = RoundedCornerShape(16.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(R.color.cor_app),
                            unfocusedBorderColor = colorResource(R.color.cor_app)
                        )
                    )
                    OutlinedTextField(
                        value = peso,
                        onValueChange = {
                            peso = it
                        },
                        placeholder = {
                            Text(text = "Peso")
                        },
                        shape = RoundedCornerShape(16.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(R.color.cor_app),
                            unfocusedBorderColor = colorResource(R.color.cor_app)
                        )
                    )


                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            imc = calcularIMC(altura.toDouble(), peso.toDouble())
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.cor_app)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "Calcular",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }


                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 30.dp)
                .height(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Green
            ),


            shape = RoundedCornerShape(16.dp),


            ) {
            Row(
                modifier = Modifier.fillMaxSize(),


                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = "%.1f".format(imc),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = classificacao,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}