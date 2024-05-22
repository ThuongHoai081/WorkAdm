package com.nqv.workadm_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nqv.workadm_app.group.cms.menu.ctrl.BottomNavigationBar

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        ProfileHeader()

        Spacer(modifier = Modifier.height(16.dp))

        WorkspaceSection()

        Spacer(modifier = Modifier.height(16.dp))

        AccountSection()

        Spacer(modifier = Modifier.height(16.dp))

        SupportSection()

        Spacer(modifier = Modifier.height(16.dp))

        FooterSection()
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Đặng Thị Thương Hoài",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .background(Color.White, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "th316942@gmail.com",
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Là thành viên Trello từ tháng 8 năm 2019",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
fun WorkspaceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        SectionItem(
            title = "Các Không gian làm việc của bạn"
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(
            title = "Không gian làm việc Trello", icon = Icons.Default.VerifiedUser
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(
            title = "Các Không gian làm việc khách"
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(
            title = "KUMAI's workspace", icon = Icons.Default.VerifiedUser
        )
    }
}


@Composable
fun AccountSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        SectionItem(title = "Hồ sơ và Hiển thị", icon = Icons.Default.AccountCircle)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Chuyển Đổi Tài Khoản", icon = Icons.Default.SwapHoriz)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Hãy là người kiểm tra bản beta", icon = Icons.Default.School)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Quản lý tài khoản trên trình duyệt", icon = Icons.Default.VerifiedUser)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Đăng Xuất", icon = Icons.Default.ExitToApp)
    }
}
@Composable
fun SupportSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        SectionItem(title = "Liên hệ Hỗ trợ", icon = Icons.Default.Help)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Chính sách Bảo mật", icon = Icons.Default.Security)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Thông báo thu thập thông tin", icon = Icons.Default.Info)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Điều khoản dịch vụ", icon = Icons.Default.Description)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Các thông báo xác nhận dữ liệu", icon = Icons.Default.Verified)
        Divider(color = Color.Gray, thickness = 1.dp)
        SectionItem(title = "Thêm ứng dụng Atlassian", icon = Icons.Default.AddCircle)
    }
}

@Composable
fun FooterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1976D2), RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hãy cùng trò chuyện nào",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Chúng tôi rất muốn lắng nghe từ người dùng và hy vọng bạn sẽ luôn đồng hành cùng chúng tôi.",
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Đánh giá chúng tôi trên App Store", color = Color(0xFF1976D2))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.fb),
                    contentDescription = "Facebook",
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.ig),
                    contentDescription = "IG",
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Phiên bản Trello 2024.9\n(20240503.194848) đã được làm bằng cả tấm huyết và tình yêu từ thành phố New York và khắp nơi trên Trái Đất.",
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun SectionItem(title: String, icon: ImageVector? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = title, color = Color.Black, fontSize = 16.sp)
    }
}

@Composable
fun UsersScreen(navCtrl: NavHostController) {
    MaterialTheme {
        val image = painterResource(R.drawable.auth_side_bg)

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(577.dp)
        )

        LazyColumn {
            item {
                UserProfileScreen()
            }
        }
    }
}