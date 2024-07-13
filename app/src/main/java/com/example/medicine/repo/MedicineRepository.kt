package com.example.medicine.repo

import com.example.medicine.database.Medicine
import com.example.medicine.database.MedicineDao
import com.example.medicine.database.MedicineResponse
import com.example.medicine.models.ErrorResponse
import com.example.medicine.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val medicineDao: MedicineDao,
    private val api: ApiService
) {

    suspend fun getMedicines(): List<Medicine> = withContext(Dispatchers.IO) {

        if (medicineDao.getAll().isNotEmpty()){
            medicineDao.getAll()
        }else{
            callApi()
        }

    }


    private suspend fun callApi() : List<Medicine> {
        val medicines = mutableListOf<Medicine>()
        try {
            val response = api.getList()

            if (response.isSuccessful && response.body() != null) {

                var s = response.body() as MedicineResponse
                s.problems
                for (problem in s.problems) {
                    problem.Diabetes?.let { diabetesList ->
                        diabetesList.forEach { diabetes ->

                            diabetes.medications?.forEach { medication ->

                                medication.medicationsClasses.forEach { medicationClass ->


                                    medicationClass.className.forEach { drugList ->

                                        drugList.associatedDrug.forEach { drug ->

                                            medicines.add(
                                                Medicine(1,
                                                    name = drug.name,
                                                    dose = drug.dose.orEmpty(),
                                                    strength = drug.strength.orEmpty(),
                                                    diseaseName = "Diabetes"
                                                )
                                            )
                                        }
                                        drugList.associatedDrug2.forEach { drug ->

                                            medicines.add(
                                                Medicine(2,
                                                    name = drug.name,
                                                    dose = drug.dose.orEmpty(),
                                                    strength = drug.strength.orEmpty(),
                                                    diseaseName = "Diabetes"
                                                )
                                            )
                                        }
                                    }
                                    medicationClass.className2.forEach { drugList ->

                                        drugList.associatedDrug.forEach { drug ->

                                            medicines.add(
                                                Medicine(3,
                                                    name = drug.name,
                                                    dose = drug.dose.orEmpty(),
                                                    strength = drug.strength.orEmpty(),
                                                    diseaseName = "Diabetes"
                                                )
                                            )
                                        }
                                        drugList.associatedDrug2.forEach { drug ->

                                            medicines.add(
                                                Medicine(4,
                                                    name = drug.name,
                                                    dose = drug.dose.orEmpty(),
                                                    strength = drug.strength.orEmpty(),
                                                    diseaseName = "Diabetes"
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

//                    problem.Asthma?.let {
//                        // Add logic if needed for Asthma
//                    }
                }

                medicineDao.insertAll(medicines)


            } else {
                try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    val gson =
                        Gson().fromJson(jObjError.toString(), ErrorResponse::class.java)


                } catch (e: Exception) {

                }

            }

        } catch (e: HttpException) {

//            "An unexpected error occured"
        } catch (e: IOException) {
//            Couldn't reach server. Check your internet connection."
        }

        return medicines
    }

}