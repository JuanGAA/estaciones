package com.example.animaciones1

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animaciones1.ui.theme.Animaciones1Theme
import com.example.animaciones1.ui.theme.Textos
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Animaciones1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun VisibilidadAnimacion(
    show: Boolean,
    updateVisibilidad: () -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (show){
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Lugar"
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = { updateVisibilidad() }
        ) {
            Text(text = if (show) "Ocultar" else "Mostrar")
        }
    }
}

fun miRandom(): Textos {
    return when (nextInt(from = 0, until = 3)) {
        0 -> Textos.NORMAL
        1 -> Textos.NEGRITA
        2 -> Textos.CURSIVA
        else -> Textos.ERROR
    }
}


@Composable
fun AnimacionCampana() {
    val value1 by rememberInfiniteTransition().animateFloat(
        initialValue = 25f,
        targetValue = -25f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ) )
    Column(modifier =Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Icon(
        imageVector = Icons.Outlined.Notifications,
        contentDescription = " ",
        modifier = Modifier
            .graphicsLayer(
                transformOrigin = TransformOrigin(
                    pivotFractionX = 0.5f,
                    pivotFractionY = 0.0f,
                ),
                rotationZ = value1
            )
    )
    }
}

@Composable
fun cambioColor() {
    Column {
        var colorinicial by rememberSaveable {
            mutableStateOf(false)
        }
        val color1 by animateColorAsState(
            targetValue = if (colorinicial) Color.Magenta else Color.Green,
            animationSpec = tween(durationMillis = 2000)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color1)
                .clickable { colorinicial = !colorinicial }
        )
    }
}

@Composable
fun AnimacionColor(){
    val color = remember { Animatable(Color.Blue) }
    var ok = nextBoolean()
    LaunchedEffect(ok) {
        color.animateTo(if (ok) Color.Magenta else Color.Red,
            animationSpec = tween(1000))
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(color.value))
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    //VisibilidadAnimacion()
    AnimacionCampana()
    cambioColor()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Animaciones1Theme {
        Greeting("Android")
    }
}