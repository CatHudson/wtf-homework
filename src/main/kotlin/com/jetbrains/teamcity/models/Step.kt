package com.jetbrains.teamcity.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Step(
    val id: Int = 1,
    val name: String = "name",
    val type: String = "simpleRunner",
): BaseModel()
