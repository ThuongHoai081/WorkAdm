package com.nqv.workadm_app.group.cms.login.tmpl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nqv.workadm_app.R
import com.nqv.workadm_app.group.cms.login.ctrl.LoginState
import com.nqv.workadm_app.group.cms.login.ctrl.LoginViewModel
import com.nqv.workadm_app.group.cms.login.ctrl.handleLogin
import com.nqv.workadm_app.main.route.NavGraph
import com.nqv.workadm_app.main.route.RouteController
import kotlinx.coroutines.launch

@Composable
fun Login_Content(
    navCtrl: NavHostController,
    onLoginSuccess: (Boolean) -> Unit
){

    val coroutineScope = rememberCoroutineScope()
    val viewModel = LoginViewModel()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isShowPassword by remember { mutableStateOf(false) }

    val onLoginClick: () -> Unit = {
        coroutineScope.launch {
            viewModel.setLoginState(LoginState(isLoading = true))
            val result = handleLogin(username, password)
            viewModel.setLoginState(result)
            if (result.isSuccess) {
                navCtrl.navigate(route = RouteController.Home.route)
            }
        }
    }

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
            Text(text = "",color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                label = {
                    Text(text = "Email",color = Color.White)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},

                visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),

                trailingIcon = {
                    IconButton(onClick =
                    {isShowPassword = !isShowPassword }) {
                        Icon(
                            painter = painterResource(
                                if (isShowPassword) R.drawable.eye_visible else R.drawable.eye_invisible
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                label = {
                Text(text = "Password",
                    color = Color.White)
            },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 57.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = { }) {
                Text(text = "Forgot password",
                    color = Color.White,
                    modifier = Modifier.clickable {

                })
            }

        }
    }

}

@Preview
@Composable
fun PreviewLogin() {
    val navController = rememberNavController();
    Login_Content(
        navCtrl = navController,
        onLoginSuccess = { }
    )
}
