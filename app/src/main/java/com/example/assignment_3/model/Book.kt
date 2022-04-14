package com.example.assignment_3.model

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Book(
    var id: String?,
    var name: String = "",
    var author: String = "",
    var year: Int = 0,
    var rates: Float = 0.0f,
    var price: Int = 0,
) : Serializable {

    @Exclude
    fun toMap(): HashMap<String, Any?> {
        return hashMapOf(
            "id" to this.id,
            "name" to this.name,
            "author" to this.author,
            "year" to this.year,
            "rates" to this.rates,
            "price" to this.price
        )
    }

}