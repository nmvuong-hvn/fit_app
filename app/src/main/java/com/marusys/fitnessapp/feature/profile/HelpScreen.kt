package com.marusys.fitnessapp.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HelpScreen(onBackClick: () -> Unit = {}) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
            Text(
                text = "Help & Support",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = """
Chào mừng bạn đến với Fitness App! 
Nếu bạn gặp vấn đề trong quá trình sử dụng, hãy tham khảo thông tin dưới đây:

1. **Bắt đầu bài tập:** 
   - Vào màn Home, chọn bài tập mong muốn và nhấn "Start".

2. **Ứng dụng không nhận diện tư thế chính xác:** 
   - Đảm bảo bạn đứng cách camera 1.5–2m, ánh sáng đầy đủ.
   - Kiểm tra rằng toàn bộ cơ thể bạn nằm trong khung hình.

3. **Dữ liệu và tiến trình tập:** 
   - Ứng dụng lưu tiến trình cục bộ. Nếu đăng nhập bằng tài khoản, dữ liệu sẽ được đồng bộ online.

4. **Liên hệ hỗ trợ:** 
   - Email: support@fitnessapp.com
   - Chat: Nhấn nút "Chat with us" trong màn chính (nếu có).

Chúng tôi luôn sẵn sàng hỗ trợ bạn để trải nghiệm tập luyện trở nên dễ dàng và hiệu quả hơn.
""".trimIndent(),
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 22.sp
            )
        }
    }
}