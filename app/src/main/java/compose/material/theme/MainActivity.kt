package compose.material.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import compose.material.theme.ui.theme.Material3ComposeTheme

/*
* Ref : https://www.boltuix.com/2022/07/no-internet-connection-ui-design.html
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {
                val openFullDialogCustom = remember { mutableStateOf(false) }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        //...................................................................
                        // * full screen custom dialog
                        Button(
                            onClick = {
                                openFullDialogCustom.value = true
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "No internet",style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }

                //...............................................................................
                //Full screen Custom Dialog Sample
                NoInternetScreen(openFullDialogCustom)

            }
        }
    }


    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun NoInternetScreen(openFullDialogCustom: MutableState<Boolean>) {

        if (openFullDialogCustom.value) {

            // Dialog function
            Dialog(
                onDismissRequest = {
                    openFullDialogCustom.value = false
                },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false // experimental
                )
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {



                        Image(
                            painter = painterResource(id = R.drawable.no_intrenet),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),

                            )

                        Spacer(modifier = Modifier.height(20.dp))
                        //.........................Text: title
                        Text(
                            text = "Whoops!!",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth(),
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        //.........................Text : description
                        Text(
                            text = "No Internet connection was found. Check your connection or try again.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                        val cornerRadius = 16.dp
                        val gradientColor = listOf(Color(0xFFff669f), Color(0xFFff8961))
                        GradientButton(
                            gradientColors = gradientColor,
                            cornerRadius = cornerRadius,
                            nameButton = "Try again",
                            roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp),
                            openFullDialogCustom
                        )

                    }

                }
            }

        }
    }


}

//...........................................................................
@Composable
fun GradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    openFullDialogCustom: MutableState<Boolean>
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick = {
            openFullDialogCustom.value = false
        },

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                /*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nameButton,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}
