import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import java.lang.Exception

data class Lyrics(
    val originalText: String,
    val translatedText: String
)

class LyricsTranslator {

    private val translate: Translate

    init {
        // Set up the Google Translate API client
        translate = TranslateOptions.getDefaultInstance().service
    }

    fun translateLyrics(text: String, sourceLanguage: String): Lyrics {
        try {
            // Translate the text to English
            val translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(sourceLanguage), Translate.TranslateOption.targetLanguage("en"))
            return Lyrics(text, translation.translatedText)
        } catch (e: Exception) {
            // Handle translation errors
            e.printStackTrace()
            // Return original text if translation fails
            return Lyrics(text, text)
        }
    }
}
