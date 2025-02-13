package com.jetbrains.teamcity.configuration

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.util.Properties

object Configuration {

    const val CONFIG_FILE = "configuration.properties"
    private val properties = Properties()

    init {
        loadProperties()
    }

    fun getProperty(key: String): Any? {
        return properties[key]
    }

    private fun loadProperties() {
        try {
            properties.load(this::class.java.classLoader.getResourceAsStream(CONFIG_FILE))
        }
        catch(e: FileNotFoundException) {
            println("File not found: $CONFIG_FILE; ${e.printStackTrace()}")
        }
        catch (e: IOException) {
            println("Error while reading properties file: $CONFIG_FILE; ${e.printStackTrace()}")
        }
    }
}