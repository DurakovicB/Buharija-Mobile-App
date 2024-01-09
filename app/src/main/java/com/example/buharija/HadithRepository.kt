package com.example.buharija

import android.content.Context

class HadithRepository(private val context: Context) {
    // Initialize chapters when the class is created
    private val chapters: List<Chapter> = loadHadiths()

    fun getHadithsForChapter(index: Int): List<String> {
        return chapters.getOrNull(index)!!.hadiths
    }

    fun getChapterNames(): List<String> {
        return chapters.map { it.name }
    }

    private fun loadHadiths(): List<Chapter> {
        val loadedChapters = mutableListOf<Chapter>()

        // Read the text file from the raw resources
        val inputStream = context.resources.openRawResource(R.raw.hadiths)
        val lines = inputStream.bufferedReader().readLines()

        var isChapterName = false
        var currentChapter: Chapter? = null

        for (line in lines) {
            val trimmedLine = line.trim()

            // Check if the line is a chapter name
            if (trimmedLine == trimmedLine.uppercase()) {
                isChapterName = true
                currentChapter = Chapter(trimmedLine, mutableListOf())
                loadedChapters.add(currentChapter)
            } else if (isChapterName && !trimmedLine.isEmpty() && currentChapter != null) {
                // If in a chapter and the line is not empty, add it to the current chapter's hadiths
                currentChapter.hadiths.add(trimmedLine)
            } else if (isChapterName && trimmedLine.isEmpty()) {
                // If an empty line is encountered after a chapter name, consider it as a separator
                isChapterName = false
            }
        }

        return loadedChapters
    }



}

data class Chapter(val name: String, val hadiths: MutableList<String>)
