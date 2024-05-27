package com.nqv.workadm_app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nqv.workadm_app.group.cms.menu.ctrl.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navCtrl: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navCtrl = navCtrl)
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Trello",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                modifier = Modifier.background(Color(0xFF0079BF)),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0079BF)
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                SearchBar()
                Spacer(modifier = Modifier.height(16.dp))
                WorkspaceTitle(
                    title = "CÁC KHÔNG GIAN LÀM VIỆC CỦA BẠN",
                )
                WorkspaceItem(name = "Không gian làm việc của trello")
                Divider(color = Color.LightGray, thickness = 1.dp)
                WorkspaceItemChild(name = "Không có tiêu đề")
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))
                WorkspaceTitle(
                    title = "CÁC KHÔNG GIAN LÀM VIỆC KHÁCH",
                )
                WorkspaceItem(name = "Kumail's workspace")
                Divider(color = Color.LightGray, thickness = 1.dp)
                WorkspaceItemChild("Our space")
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    )
}

@Composable
fun SearchBar() {
    val searchQuery = remember { mutableStateOf("") }
    BasicTextField(
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = MaterialTheme.shapes.small)
            .padding(12.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = Color.Gray,
            fontSize = 16.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (searchQuery.value.isEmpty()) {
                    Text("Bảng", color = Color.Gray)
                }
                Spacer(modifier = Modifier.width(8.dp))
                innerTextField()
            }
        }
    )
}

@Composable
fun WorkspaceTitle(title: String) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun WorkspaceItem(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = name,
            fontSize = 15.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Bảng",
            fontSize = 14.sp,
            color = Color(0xFF0079BF)
        )
    }
}

@Composable
fun WorkspaceItemChild(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Blue)
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = name,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}