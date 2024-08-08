package com.example.mob21firabase.data.model

data class Extra(
    val location: String = "Location",
    val time: String = "time"
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "location" to location,
            "time" to time
        )
    }
    companion object  {
        fun fromMap(map: Map<*, *>?): Extra? {
            return map?.let {
                Extra(
                    location = it["location"].toString(),
                    time = it["time"].toString()
                )
            }
        }
    }
}
