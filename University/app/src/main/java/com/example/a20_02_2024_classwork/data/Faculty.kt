package com.example.a20_02_2024_classwork.data

import java.util.UUID

data class Faculty(
    val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var universityID : UUID? = null
)
