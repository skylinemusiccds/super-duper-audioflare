package com.universe.audioflare.data.model.mediaService

import android.graphics.Bitmap
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import java.lang.Exception

data class Song (
    val title: String?,
    val artists: List<Artist>?,
    val duration: Long,
    val lyrics: String,
    val album: Album,
    val videoId: String,
    val thumbnail: Thumbnail?,
    val thumbnailBitmap: Bitmap?,
    val isLocal: Boolean
) {
    companion object {
        private val translate: Translate = TranslateOptions.getDefaultInstance().service

        fun translateLyrics(lyrics: String, sourceLanguage: String): String {
            return try {
                // Translate the lyrics to English
                val translation = translate.translate(lyrics, Translate.TranslateOption.sourceLanguage(sourceLanguage), Translate.TranslateOption.targetLanguage("en"))
                translation.translatedText
            } catch (e: Exception) {
                // Handle translation errors
                e.printStackTrace()
                // Return original lyrics if translation fails
                lyrics
            }
        }
    }

    fun getTranslatedLyrics(sourceLanguage: String): String {
        // Check if lyrics need translation (assuming lyrics are in string format)
        return if (lyrics.isNotBlank()) {
            translateLyrics(lyrics, sourceLanguage)
        } else {
            // If lyrics are not available, return an empty string
            ""
        }
    }
}
