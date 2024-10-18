package com.example.mytabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytabata.ui.theme.MytabataTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MytabataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    var theCounter by remember { mutableStateOf(60L) }
    // Esta variable guarda el número del contador, empezando en 60.
    var miCounterDown by remember { mutableStateOf(CounterDown(61) { newValue -> theCounter = newValue })}
    // Aquí se crea un contador, Cuando baja, actualiza 'theCounter' con el número.



    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = theCounter.toString(),
                color = Color.White,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = { miCounterDown.start() },
                modifier = Modifier.padding(6.dp)
            ) {
                Text(text = "Iniciar", color = Color.White)
            }
            Button(
                onClick = { miCounterDown.pause() },
                modifier = Modifier.padding(6.dp)
            ) {
                Text(text = "Pausar", color = Color.White)
            }
            Button(
                onClick = {
                    miCounterDown.cancel()
                    miCounterDown.reset()
                    theCounter = 60
                },
                modifier = Modifier.padding(6.dp)


            ) {
                Text(text = "Reiniciar")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MytabataTheme {
        Counter()
    }
}