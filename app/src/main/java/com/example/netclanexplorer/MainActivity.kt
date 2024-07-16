package com.example.netclanexplorer

import android.graphics.drawable.Icon
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.netclanexplorer.ui.theme.NetclanExplorerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      NetclanExplorerTheme {
        Scaffold { padding ->
          Column(modifier = Modifier.padding(vertical = padding.calculateTopPadding())) {
            TopAppBar()
            ViewPagerPreview()
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun TopAppBar() {
  TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = Color.Blue,
    contentColor = Color.White,
  ) {
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 4.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {
      // Left icon
      IconButton(onClick = { }) {
        Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = "Menu",
          tint = Color.White,
        )
      }

      // Middle texts
      Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 4.dp)
      ) {
        Text(
          text = "Howdy Vishwajith Shettigar !!",
          style = MaterialTheme.typography.headlineSmall.copy(fontSize = 15.sp),
          color = Color.White
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "Menu",
            tint = Color.White,
          )
          Text(
            text = "Trasi",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
          )
        }
      }

      Spacer(modifier = Modifier.weight(1f))

      // Right icon
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
          onClick = { },
          modifier = Modifier.size(29.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Refine",
            tint = Color.White

          )
        }
        Text(
          text = "Refine", style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
          color = Color.White
        )
      }

    }
  }
}

@Composable
fun ViewPagerPreview() {
  ViewPagerSample()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerSample() {
  val pagerState = rememberPagerState(
    pageCount = { 3 }
  )
  Column {
    // Page Indicator
    PagerIndicator(pagerState)
    HorizontalPager(
      state = pagerState,
      modifier = Modifier
          .weight(1f)
          .background(Color.Cyan)
    ) { page ->
      Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
        ShowUserProfileList()
      }
    }
  }
}

@Composable
fun ShowUserProfileList() {
}

@Preview(showBackground = true)
@Composable
fun UserProfileCard(
  name: String = "Vishwajith",
  location: String = "Udupi",
  distance: String = "7.7",
  profileScore: Int = 19,
  interests: List<String> = listOf("Coffee", "Business", "Friendship"),
  message: String = "Hi community ! i am open to new connections"
) {
  Card(
    elevation = 4.dp,
    shape = RoundedCornerShape(8.dp),
    backgroundColor = Color.LightGray
  ) {
    Column(
      modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
    ) {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Box(
          modifier = Modifier
              .size(32.dp)
              .background(Color.Gray, RoundedCornerShape(8.dp))
              .align(Alignment.Top)
        ) {
          Text(
            text = "NP",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
          )
        }
        Text(
          text = "+ INVITE",
          color = Color.Blue,
          style = MaterialTheme.typography.bodySmall
        )
      }

      Text(
        text = "$name | $location",
        style = MaterialTheme.typography.bodySmall
      )

      Text(
        text = "Within $distance",
        style = MaterialTheme.typography.bodySmall
      )

      LinearProgressIndicator(
        progress = profileScore.toFloat() / 100,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Gray
      )

      Text(
        text = "Profile Score - $profileScore%",
        style = MaterialTheme.typography.bodyMedium
      )

      Row {
        // ... Interest icons with labels
      }

      Text(
        text = message,
        style = MaterialTheme.typography.bodyMedium
      )
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PagerIndicator(
  pagerState: PagerState = rememberPagerState(
    pageCount = { 3 } // Number of pages
  )
) {
  val coroutineScope = rememberCoroutineScope()
  val tabTitles = listOf("PerSonal", "Services", "Businesses")
  val context = LocalContext.current

  TabRow(
    selectedTabIndex = pagerState.currentPage,
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 0.dp),
    containerColor = Color.Black,
    indicator = { tabPositions ->
      Box(
          Modifier
              .tabIndicatorOffset(tabPositions[pagerState.currentPage])
              .height(3.dp)
              .background(Color.White) // Your desired color
      )
    }
  ) {
    tabTitles.forEachIndexed { index, title ->
      Tab(
        text = { Text(title) },
        selected = pagerState.currentPage == index,
        onClick = {
          Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
          coroutineScope.launch {
            pagerState.animateScrollToPage(index)
          }
        },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.Gray
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
  val shape = RoundedCornerShape(10.dp)
  var text by rememberSaveable {
    mutableStateOf("")
  }
  Surface(
    modifier = Modifier
        .height(80.dp)
        .fillMaxWidth(), color = Color.White
  ) {
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutlinedTextField(
        value = text,
        onValueChange = {
          text = it
        },
        shape = shape,
        leadingIcon = {
          Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
          Text(
            "Search",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
          ) // Placeholder for hint
        },
        textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(horizontal = 4.dp)
            .weight(6f)
      )

      IconButton(
        onClick = { },
        modifier = Modifier
            .size(29.dp)
            .weight(1f)
      ) {
        Icon(
          imageVector = Icons.Default.Menu, // Replace with your desired icon
          contentDescription = "Refine",
        )
      }
    }
  }
}
