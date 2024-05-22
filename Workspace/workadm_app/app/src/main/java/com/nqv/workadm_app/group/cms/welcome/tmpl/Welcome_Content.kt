package com.nqv.workadm_app.group.cms.login.tmpl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nqv.workadm_app.R

//@Preview(showBackground = true)
@Composable
fun Welcome_Content (
    navCtrl: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.auth_side_bg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Login")
            Text(text = "Đưa tinh thần đồng đội tiến về phía trước, ngay cả khi đang di chuyển.",
                fontSize = 20.sp,
               // fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                        navCtrl.navigate("Login_screen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navCtrl.navigate("Sign_screen")
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(text = "Sign in")
            }
            Spacer(modifier = Modifier.height(150.dp))

            Text(text = "Or",
                fontSize = 20.sp,
                // fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                Image(painter = painterResource(id = R.drawable.fb),
                    contentDescription = "facebook",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {

                        }
                )

                Image(painter = painterResource(id = R.drawable.gg),
                    contentDescription = "google",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {

                        }
                )

                Image(painter = painterResource(id = R.drawable.ig),
                    contentDescription = "instagram",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {

                        }
                )

            }

        }

    }
}

@Preview
@Composable
fun PreviewApp() {
    val navController = rememberNavController();
    Welcome_Content(navCtrl = navController)
}