package com.jetbrains.teamcity.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.common.ContentTypes.APPLICATION_JSON
import com.github.tomakehurst.wiremock.common.ContentTypes.CONTENT_TYPE
import com.jetbrains.teamcity.models.BaseModel

object WireMock {

    private var wireMockServer: WireMockServer? = null
    private val mapper = ObjectMapper()

    fun setupServer(mappingBuilder: MappingBuilder, status: Int, model: BaseModel) {
        wireMockServer ?: WireMockServer(8081).also {
            wireMockServer = it
            wireMockServer!!.start()
        }

        val jsonModel: String = mapper.writeValueAsString(model)

        wireMockServer!!.stubFor(
            mappingBuilder
                .willReturn(
                    aResponse()
                        .withStatus(status)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(jsonModel)
                )
        )
    }

    fun stopServer() {
        wireMockServer?.apply {
            resetMappings()
            stop()
            wireMockServer = null
        }
    }
}
