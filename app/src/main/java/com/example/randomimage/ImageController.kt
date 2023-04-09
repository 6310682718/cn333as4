package com.example.randomimage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun generateImage(width: Int, height: Int, imageCategory: String) {
    Column(
        // in this column we are adding modifier
        // to fill max size, mz height and max width
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
            // on below line we are adding
            // padding from all sides.
            .padding(10.dp),
        // on below line we are adding vertical
        // and horizontal arrangement.
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line we are adding image for our image view.
        Image(
            // on below line we are adding the image url
            // from which we will  be loading our image.
            painter = rememberAsyncImagePainter("https://loremflickr.com/${width}/${height}/${imageCategory}"),

            // on below line we are adding content
            // description for our image.
            contentDescription = "gfg image",

            // on below line we are adding modifier for our
            // image as wrap content for height and width.
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }
}


@Composable
fun FormImageScreen(modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val list = listOf("dog", "cat", "boy", "girl")
    var selectedItem by remember {
        mutableStateOf("")
    }


    var textFiledSize by remember {
        mutableStateOf(Size)
    }

    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            var width by remember { mutableStateOf(8) };
            var widthres by remember { mutableStateOf(8) };
            var height by remember { mutableStateOf(8) };
            var heightres by remember { mutableStateOf(8) };
            var warning by remember { mutableStateOf("") };
        Spacer(Modifier.height(30.dp))
            Text(text = "Random Image", style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
            ))

        Column(Modifier.padding(top = 20.dp)) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = "Select Category") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded})
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                list.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedItem = label
                        expanded = false
                    }) {
                        Text(text = (label))
                    }
                }
            }
        }

        Spacer(Modifier.height(50.dp))
            TextField(
                value = widthres.toString(),
                onValueChange = {
                    var res = it.toIntOrNull() ?: 0;
                    widthres = res;
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
                value = heightres.toString(),
                onValueChange = {
                    var res = it.toIntOrNull() ?: 0;
                    heightres = res;
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
                .data("https://loremflickr.com/${width}/${height}/${selectedItem}")
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
                height = heightres
                width = widthres

                //val painter = rememberAsyncImagePainter("https://loremflickr.com/${width}/${height}/${selectedItem}")
            }) {
            Text(text = "Generate", fontSize = 18.sp)
        }

    }
}