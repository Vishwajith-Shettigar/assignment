package com.example.netclanexplorer.ui.theme.homepage

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
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
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.widget.ContentLoadingProgressBar
import com.example.model.Dummydata
import com.example.netclanexplorer.R

class MainActivity : ComponentActivity() {

  private lateinit var viewModel: HomeViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = HomeViewModel()

    enableEdgeToEdge()
    setContent {
      NetclanExplorerTheme {
        val listState = rememberLazyListState()
        // To controll to visibility of floating action button.
        var isFabVisible by remember { mutableStateOf(true) }

        LaunchedEffect(listState) {
          snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
              isFabVisible = index == 0
            }
        }

        Scaffold(
          bottomBar = {
            BottomNavigationBar()
          },
          floatingActionButton = {
            AnimatedVisibility(visible = isFabVisible) {
              FloatingActionBtn()
            }
          }
        ) { padding ->
          Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
          ) {
            TopAppBar()
            ViewPagerSample(
              modifier = Modifier
                  .weight(1f)
                  .fillMaxSize(),
              listState
            )
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
      backgroundColor = colorResource(id = R.color.app_bar_background_color),
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
            painter = painterResource(R.drawable.round_notes_24),
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
              painter = painterResource(R.drawable.outline_checklist_24),
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

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  fun ViewPagerSample(modifier: Modifier = Modifier, listState: LazyListState) {
    val pagerState = rememberPagerState(
      pageCount = { 3 }
    )
    Box(modifier = modifier) {
      Column {
        // Page Indicator
        PagerIndicator(pagerState)
        HorizontalPager(
          state = pagerState,
          modifier = Modifier
              .weight(1f)
              .background(Color.White)
        ) { page ->
          Column(modifier = Modifier.fillMaxSize()) {

            val rememberedDummyDataList = rememberSaveable {
              viewModel.dummydataList
            }

            if (rememberedDummyDataList.value.isEmpty()) {
              showLoading()
            } else {
              SearchBar()
              when (page) {
                0 -> {
                  ShowUserProfileList(listState, rememberedDummyDataList.value)
                }
              }
            }
          }
        }
      }
    }
  }

  @Preview
  @Composable
  fun showLoading() {
    CircularProgressIndicator()
  }

  @Composable
  fun ShowUserProfileList(listState: LazyListState, dummydata: MutableList<Dummydata>) {
    LazyColumn(
      state = listState
    ) {
      items(dummydata.size) { index ->
        ProfileCard(dummydata.get(index))
        Spacer(modifier = Modifier.height(10.dp))
      }
    }
  }

  @Composable
  fun ProfileCard(dummydata: Dummydata) {
    Box(
      modifier = Modifier.padding(start = 15.dp, end = 10.dp)
    ) {
      Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 24.dp, start = 25.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
      ) {
        Column(
          modifier = Modifier
            .padding(16.dp)
        ) {
          Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Spacer(modifier = Modifier.width(44.dp))
            Column {
              Text(
                text = dummydata.fullName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
              )
              Text(
                text = dummydata.placeAndOccupation,
                color = Color.Gray
              )
              Text(
                text = "Within ${dummydata.distance} KM",
                color = Color.Gray
              )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
              text = "+ INVITE",
              color = Color.Black,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.height(16.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
          ) {
            Spacer(modifier = Modifier.width(44.dp))
            LinearProgressIndicator(
              progress = dummydata.profileScore.toFloat() / 100,
              color = Color.Gray,
              modifier = Modifier
                  .width(80.dp)
                  .height(5.dp)
                  .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
            )
            Text(
              text = "Profile Score - ${dummydata.profileScore}%",
              color = Color.Gray,
              textAlign = TextAlign.Center,
              modifier = Modifier.padding(horizontal = 10.dp),
              style = MaterialTheme.typography.bodySmall
            )
          }

          Spacer(modifier = Modifier.height(16.dp))
          Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            dummydata.hobbyList.hobyList.mapIndexed { index, text ->
              TextWithIcon(text = text, index)
              if (index != dummydata.hobbyList.hobyList.size - 1)
                VerticalDivider()
            }
          }
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = "Hi community! I am open to new connections \"🙂\"",
            fontSize = 16.sp, modifier = Modifier.padding(start = 5.dp),
            color = Color.Black
          )
        }
      }
      Box(
        modifier = Modifier
            .size(88.dp)
            .offset(x = -10.dp, y = (10).dp)
            .background(
                colorResource(id = R.color.no_profile_picture_background_color),
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center,
      ) {
        Text(
          text = "NP",
          color = Color.Black,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }

  @Composable
  fun VerticalDivider() {
    Box(
      modifier = Modifier
          .height(14.dp)
          .width(1.dp)
          .background(Color.Gray)
    )
  }

  @Composable
  fun TextWithIcon(text: String, id: Int) {
    var paintDrawable = painterResource(id = R.drawable.baseline_coffee_24)

    when (id) {
      0 -> {
        paintDrawable = painterResource(id = R.drawable.baseline_coffee_24)
      }

      1 -> {
        paintDrawable = painterResource(id = R.drawable.baseline_business_center_24)
      }

      2 -> {
        paintDrawable = painterResource(id = R.drawable.baseline_people_outline_24)
      }
    }

    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(end = 5.dp, start = 5.dp)
    ) {
      IconButton(
        onClick = { },
        modifier = Modifier
          .size(10.dp)
      ) {
        Icon(
          painter = paintDrawable,
          contentDescription = "Refine",
          tint = Color.Black
        )
      }
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = text, style = MaterialTheme.typography.bodySmall, color = Color.Black

      )
    }
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Preview(showBackground = true)
  @Composable
  fun PagerIndicator(
    pagerState: PagerState = rememberPagerState(
      pageCount = { 3 }
    )
  ) {
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = listOf("PerSonal", "Services", "Businesses")

    TabRow(
      selectedTabIndex = pagerState.currentPage,
      modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 0.dp),
      containerColor = colorResource(id = R.color.pager_indicato_background_color),
      indicator = { tabPositions ->
        Box(
            Modifier
                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                .height(3.dp)
                .background(Color.White)
        )
      }
    ) {
      tabTitles.forEachIndexed { index, title ->
        Tab(
          text = { Text(title) },
          selected = pagerState.currentPage == index,
          onClick = {
            coroutineScope.launch {
              pagerState.animateScrollToPage(index)
            }
          },
          selectedContentColor = Color.White,
          unselectedContentColor = colorResource(id = R.color.pager_indicato_text_color)
        )
      }
    }
  }

  @OptIn(ExperimentalMaterial3Api::class)
  @Preview(showBackground = true)
  @Composable
  fun SearchBar(modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(10.dp)
    var text by rememberSaveable {
      mutableStateOf("")
    }
    Surface(
      modifier = Modifier
        .fillMaxWidth(), color = Color.White
    ) {
      Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 3.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        OutlinedTextField(
          value = text,
          onValueChange = {
            text = it
          },
          colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = Color.Black
          ),
          shape = shape,
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.Search, contentDescription = null,
              tint = Color.Black
            )
          },
          placeholder = {
            Text(
              "Search",
              color = Color.Gray,
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
            )
          },
          textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
          modifier = modifier
              .fillMaxWidth()
              .height(44.dp)
              .padding(horizontal = 4.dp)
              .weight(6f),
        )

        IconButton(
          onClick = { },
          modifier = Modifier
              .size(29.dp)
              .weight(1f)
        ) {
          Icon(
            painter = painterResource(id = R.drawable.baseline_tune_24),
            contentDescription = "Refine",
            tint = Color.Black
          )
        }
      }
    }
  }

  @Preview
  @Composable
  fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf("Explore", "Connection", "Chat", "Contacts", "Groups")
    val icons = listOf(
      painterResource(id = R.drawable.explore_icon),
      painterResource(id = R.drawable.baseline_people_outline_24),
      painterResource(id = R.drawable.rounded_chat_24),
      painterResource(id = R.drawable.baseline_perm_contact_calendar_24),
      painterResource(id = R.drawable.baseline_tag_24),
    )

    BottomNavigation(backgroundColor = Color.White, modifier = Modifier.height(100.dp)) {
      items.forEachIndexed { index, item ->
        BottomNavigationItem(
          icon = {
            Icon(
              painter = icons[index],
              contentDescription = item,
              tint = if (selectedItem == index) Color.Black else Color.Gray
            )
          },
          label = {
            Text(
              text = item,
              color = if (selectedItem == index) Color.Black else Color.Gray,
              fontSize = 10.sp,
              maxLines = 1
            )
          },
          selected = selectedItem == index,
          onClick = { selectedItem = index }
        )
      }
    }
  }
}

@Preview
@Composable
fun FloatingActionBtn() {
  FloatingActionButton(
    onClick = { },
    shape = CircleShape,
  ) {
    Icon(
      imageVector = Icons.Default.Add, contentDescription = "Add",
      tint = Color.Black
    )
  }
}
