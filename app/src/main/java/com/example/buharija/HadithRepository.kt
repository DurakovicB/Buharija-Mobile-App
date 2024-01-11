package com.example.buharija

import android.content.Context

data class Hadith(val id: Int, val text: String)

data class Chapter(val id: Int, val name: String, val startHadithId: Int, var endHadithId: Int)

class HadithRepository(private val context: Context) {
    private val hadiths: MutableList<Hadith> = mutableListOf()
    private val chapters: List<Chapter> = loadChapters()

    fun getChapters(): List<Chapter> {
        return this.chapters
    }

    fun getChapterById(id: Int): Chapter? {
        return chapters.firstOrNull { it.id == id }
    }

    fun getHadithsForChapter(chapterId: Int): List<Hadith> {
        val chapter = chapters.firstOrNull { it.id == chapterId }
        return chapter?.let { hadiths.subList(it.startHadithId - 1, it.endHadithId) } ?: emptyList()
    }

    fun getHadithById(hadithId: Int): Hadith? {
        return hadiths.firstOrNull { it.id == hadithId }
    }

    // Function to search for an exact phrase in hadiths
    fun searchExactPhrase(phrase: String): List<Hadith> {
        return hadiths.filter { hadith ->
            hadith.text.contains(phrase, ignoreCase = true)
        }
    }

    // Function to search for keywords in hadiths
    fun searchKeywords(phrase: String): List<Hadith> {
        val keywords = phrase.split("\\s+".toRegex()) // Split input string by whitespace
        return hadiths.filter { hadith ->
            keywords.any { keyword -> hadith.text.contains(keyword, ignoreCase = true) }
        }
    }


    fun randomHadith(): Hadith? {
        return hadiths.randomOrNull()
    }

    private fun loadChapters(): List<Chapter> {
        val loadedChapters = mutableListOf<Chapter>()
        var hadithIdCounter = 1 // Counter for unique hadith IDs

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
                currentChapter?.let { loadedChapters.add(it) } // Add previous chapter
                currentChapter = Chapter(
                    loadedChapters.size ,
                    trimmedLine,
                    hadithIdCounter,
                    hadithIdCounter
                )
            } else if (isChapterName && !trimmedLine.isEmpty() && currentChapter != null) {
                // If in a chapter and the line is not empty, add it to the current chapter's hadiths
                hadiths.add(Hadith(hadithIdCounter++, trimmedLine))
                currentChapter.endHadithId = hadithIdCounter-1 // Update endHadithId
            } else if (isChapterName && trimmedLine.isEmpty()) {
                // If an empty line is encountered after a chapter name, consider it as a separator
                isChapterName = false
            }
        }

        currentChapter?.let { loadedChapters.add(it) } // Add the last chapter

        return loadedChapters
    }
}
