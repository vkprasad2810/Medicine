package com.example.medicine.screens

import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicine.database.Medicine
import com.example.medicine.viewModels.MedicineViewModel
import com.google.gson.Gson


@Composable
fun GreetingScreen(
    email: String,
    viewModel: MedicineViewModel = hiltViewModel(),
    onClick: (Medicine) -> Unit
) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greeting = when (currentHour) {
        in 0..11 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        else -> "Good Evening"
    }

    val medicines by viewModel.medicines.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("$greeting, $email!")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(medicines) { medicine ->
                MedicineCard(medicine, onClick = {
                    onClick(medicine) }
                )
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(medicine.name, style = MaterialTheme.typography.bodySmall)
            Text("Dose: ${medicine.dose}")
            Text("Strength: ${medicine.strength}")
        }
    }
}