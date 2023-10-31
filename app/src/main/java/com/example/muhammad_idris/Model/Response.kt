package com.example.muhammad_idris.Model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class DataItem(

	@field:SerializedName("nim")
	val nim: Int? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("idprodi")
	val idprodi: String? = null,

	@field:SerializedName("namalengkap")
	val namalengkap: String? = null
)
