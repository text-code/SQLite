package ru.test.applications.sqlite.dto

import java.io.Serializable

data class Note(
    val id: Long,
    val title: String,
    val content: String
): Serializable
