package com.interview.data.datasource.filesource

import android.content.res.AssetManager
import java.io.FileNotFoundException
import java.lang.Exception

class AssetFileSourceImpl(private val assetManager: AssetManager) : AssetFileSource {
    override fun readAssetsFile(fileName: String): String = try {
        assetManager.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        throw FileNotFoundException("Asset file not found")
    }
}