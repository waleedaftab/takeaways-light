package com.interview.data.datasource.filesource

interface AssetFileSource {
    fun readAssetsFile(fileName: String): String
}