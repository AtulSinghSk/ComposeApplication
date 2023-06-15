package com.example.composeapplication.tools

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var name: String? = null, var mobile: String? = null, var password: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
    override fun toString(): String {
        return "User(name=$name, mobile=$mobile, password=$password)"
    }
}