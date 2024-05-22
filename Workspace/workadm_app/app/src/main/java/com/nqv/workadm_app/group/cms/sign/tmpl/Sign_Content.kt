package com.nqv.workadm_app.group.cms.login.tmpl

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nqv.workadm_app.R

//@Preview(showBackground = true)
@Composable
fun Sign_Content (
    navCtrl: NavHostController
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.auth_side_bg), // Replace background_image with your image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Login")
            Text(text = "Welcome back",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Login to your account",color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = "", onValueChange = {},
                label = {
                    Text(text = "Email",color = Color.White)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White, // Màu viền khi trường văn bản được focus
                    unfocusedBorderColor = Color.White // Màu viền khi trường văn bản không được focus
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 57.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )) {
                Text(text = "Sign in")
            }

            Spacer(modifier = Modifier.height(32.dp))

        }
    }

}


@Preview
@Composable
fun PreviewSign() {
    val navController = rememberNavController();
    Sign_Content(navCtrl = navController)
}