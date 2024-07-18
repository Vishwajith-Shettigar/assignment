package com.example.netclanexplorer.ui.theme.refine

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netclanexplorer.R
import com.example.netclanexplorer.ui.theme.home.MainActivity


class RefineActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Scaffold(
        topBar = {
          TopAppBar()
        }
      ) { padding ->
        MyApp(padding)
      }
    }
  }

  fun RouteToHomeScreen(){
   finish()
  }

  @OptIn(ExperimentalLayoutApi::class)
  @Composable
  fun MyApp(paddingValues: PaddingValues) {
    var availability by remember { mutableStateOf("Available | Hey Let Us Connect") }
    var status by remember { mutableStateOf("Hi community! I am open to new connections 😃") }
    var distance by remember { mutableStateOf(54f) }
    val purposes =
      listOf("Coffee", "Business", "Hobbies", "Friendship", "Movies", "Dining", "Dating", "Matrimony")
    var selectedPurposes by remember { mutableStateOf(setOf<String>()) }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

      Text("Select Your Availability", style = MaterialTheme.typography.body1)
      DropdownMenu(
        availability,
        onAvailabilityChange = { availability = it }
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text("Add Your Status", style = MaterialTheme.typography.body1)
      OutlinedTextField(
        value = status,
        onValueChange = { status = it },
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.White, shape = MaterialTheme.shapes.small)
          .padding(5.dp),
        singleLine = false,
        maxLines = 5,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text("Select Hyper local Distance", style = MaterialTheme.typography.body1)
      Slider(
        value = distance,
        onValueChange = { distance = it },
        valueRange = 1f..100f,
        modifier = Modifier.fillMaxWidth(),

        colors = SliderDefaults.colors(
          activeTrackColor = colorResource(id = R.color.app_bar_background_color),
          thumbColor = colorResource(id = R.color.app_bar_background_color)
        ),
      )
      Text(text = "${distance.toInt()} Km", fontSize = 14.sp)

      Spacer(modifier = Modifier.height(16.dp))

      Text("Select Purpose", fontSize = 16.sp)
      FlowRow(
        maxItemsInEachRow = 3
      ) {
        purposes.forEach { purpose ->
          Chip(
            text = purpose,
            isSelected = selectedPurposes.contains(purpose),
            onClick = {
              selectedPurposes = if (selectedPurposes.contains(purpose)) {
                selectedPurposes - purpose
              } else {
                selectedPurposes + purpose
              }
            }
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      Button(
        onClick = { },
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .width(200.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = colorResource(id = R.color.app_bar_background_color)
        ),
        shape = RoundedCornerShape(20.dp),

        ) {
        Text("Save & Explore", color = Color.White)
      }
    }
  }

  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun DropdownMenu(selected: String, onAvailabilityChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Available | Hey Let Us Connect", "Busy", "Offline")

    Box {
      OutlinedTextField(
        value = selected,
        onValueChange = { },
        modifier = Modifier
          .width(400.dp)
          .height(50.dp)
          .clickable { expanded = true },
        readOnly = true,
        enabled = false,
        trailingIcon = {
          Icon(Icons.Default.ArrowDropDown, contentDescription = "Drow down", tint = Color.Black)
        },
        shape = RoundedCornerShape(10.dp),
        textStyle = androidx.compose.material3.MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
          unfocusedBorderColor = Color.Black,
          focusedBorderColor = Color.Black,
          focusedTextColor = Color.Black,
          unfocusedTextColor = Color.Black
        ),
      )
      androidx.compose.material3.DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
          .width(355.dp)
          .background(Color.White)
      ) {
        options.forEach { option ->
          androidx.compose.material.DropdownMenuItem(
            onClick = {
              onAvailabilityChange(option)
              expanded = false
            },
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(option, style = MaterialTheme.typography.body1, color = Color.Black)
          }
        }
      }
    }
  }

  @Composable
  fun Chip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
      modifier = Modifier
        .padding(4.dp)
        .clickable { onClick() },
      color = if (isSelected) colorResource(id = R.color.app_bar_background_color) else MaterialTheme.colors.surface,
      shape = RoundedCornerShape(20.dp)
    ) {
      Text(
        text = text,
        modifier = Modifier.padding(8.dp),
        color = if (isSelected) Color.White else Color.Black
      )
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun TopAppBar() {
    androidx.compose.material.TopAppBar(
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
        IconButton(onClick = { RouteToHomeScreen()}) {
          androidx.compose.material3.Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = Color.White,
          )
        }

        androidx.compose.material3.Text(
          text = "Refine",
          style = androidx.compose.material3.MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
          color = Color.White
        )
      }
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun DefaultPreview() {
    MyApp(PaddingValues())
  }

}

