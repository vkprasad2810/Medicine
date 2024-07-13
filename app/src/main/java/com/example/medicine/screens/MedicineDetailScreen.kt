package com.example.medicine.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicine.database.Medicine

@Composable
fun MedicineDetailScreen(medicine: Medicine) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(medicine.name, style = MaterialTheme.typography.bodyMedium)
        Text("Dose: ${medicine.dose}")
        Text("Strength: ${medicine.strength}")
    }
}