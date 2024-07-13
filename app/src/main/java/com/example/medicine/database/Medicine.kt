package com.example.medicine.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val dose: String,
    val strength: String,
    val diseaseName: String

):Serializable


data class MedicineResponse(
    val problems: List<Problem>
)

data class Problem(
    val Diabetes: List<DiseaseData>?,
    val Asthma: List<DiseaseData>?
)

data class DiseaseData(
    val medications: List<Medication>?,
    val labs: List<Lab>?
)

data class Medication(
    val medicationsClasses: List<MedicationClass>
)

data class MedicationClass(
    val className: List<AssociatedDrugList>,
    val className2: List<AssociatedDrugList>
)

data class AssociatedDrugList(
    val associatedDrug: List<Drug>,
    val associatedDrug2: List<Drug>
)


data class Drug(
    val name: String,
    val dose: String?,
    val strength: String?
)

data class Lab(
    val missing_field: String?
)



