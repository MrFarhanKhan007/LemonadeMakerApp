package com.example.lemonademakerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonademakerapp.ui.theme.LemonadeMakerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeMakerAppTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var squeezeCount by remember { mutableStateOf(0) }
    var stepCount by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff0c1559)
    ) {
        when (stepCount) {
            1 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_select,
                    ImageLabelResourceId = R.drawable.lemon_tree,
                    onImageClick = {
                        stepCount = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_squeeze,
                    ImageLabelResourceId = R.drawable.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            stepCount = 3
                        }
                    })
            }
            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_drink,
                    ImageLabelResourceId = R.drawable.lemon_drink,
                    onImageClick = { stepCount = 4 })
            }
            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_empty_glass,
                    ImageLabelResourceId = R.drawable.lemon_restart,
                    onImageClick = { stepCount = 1 })
            }
        }

    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    ImageLabelResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    val customFontFamily = FontFamily(
        Font(R.font.poppins)
    )
    Text(
        text = "A Lemonade a day,keeps a doctor away \n - some random guy",
        fontStyle = FontStyle.Italic,
        modifier = Modifier.wrapContentSize(BottomCenter).padding(bottom = 10.dp),
        color = Color.White,
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = "Lemonade Maker", modifier = Modifier.wrapContentSize(TopCenter).padding(top = 20.dp),
        color = Color.White, fontSize = 36.sp, fontFamily = customFontFamily
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(CenterHorizontally)
    ) {
        Text(
            text = stringResource(id = textLabelResourceId),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = customFontFamily,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = ImageLabelResourceId), contentDescription = "null",
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(BorderStroke(2.dp, Color(105, 205, 216)), shape = RoundedCornerShape(4.dp))
                .padding(16.dp)
        )


    }

}


@Preview
@Composable
fun LemonadeMakerAppPreview() {
    LemonApp()
}