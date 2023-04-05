package com.example.randomimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


fun generateImage(width: Int, height: Int, imageCategory: String): String {
    if(width < 8 || height < 8){
        return "Width & Height must greater than 8 !!";
    }
    return "";
}


@Composable
fun FormImageScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            var width by remember { mutableStateOf(8) };
            var height by remember { mutableStateOf(8) };
            var warning by remember { mutableStateOf("") };
        Spacer(Modifier.height(30.dp))
            Text(text = "Random Image", style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
            ))
        Spacer(Modifier.height(50.dp))
            TextField(
                value = width.toString(),
                onValueChange = {
                    var res = it.toIntOrNull() ?: 0;
                    width = res;
                },
                label = {
                    Text("Width : ")
                },
                modifier = Modifier.fillMaxWidth( ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(R.color.white)
                )
            );
        Spacer(Modifier.height(20.dp))
            TextField(
                value = height.toString(),
                onValueChange = {
                    var res = it.toIntOrNull() ?: 0;
                    height = res;
                },
                label = {
                    Text("Height : ")
                },
                modifier = Modifier.fillMaxWidth( ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(R.color.white)
                )
            );
        Spacer(Modifier.height(20.dp))
        Text(text = warning, style = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        ));
        Spacer(Modifier.height(20.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://api.lorem.space/image/movie?w=${width}&h=${height}")
                .crossfade(true)
                .build(),
//            model = "https://api.lorem.space/image/movie?w=150&h=220",
            //        placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "",
//            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RectangleShape)
//                .size(200.dp, 200.dp)
        );
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                warning = generateImage(width, height, "image");
//                    numGuess = "";
            }) {
            Text(text = "Generate", fontSize = 18.sp)
        }

    }
}