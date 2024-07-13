package com.example.medicine.models

data class ErrorResponse(
	val message: String? = null,
	val error: String? = null,
	val statusCode: Int? = null
)

