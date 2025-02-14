package com.jetbrains.teamcity.requests

import com.jetbrains.teamcity.enums.Endpoint
import io.restassured.specification.RequestSpecification

/**
 * A class to describe mutable parameters of a request like an endpoint, a specification, etc.
 */
open class Request(
    private val requestSpecification: RequestSpecification,
    private val endpoint: Endpoint
)
