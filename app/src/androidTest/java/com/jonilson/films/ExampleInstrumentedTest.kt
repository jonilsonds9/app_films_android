package com.jonilson.films

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jonilson.films.repository.AppDatabase
import com.jonilson.films.repository.Film
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getFilmDao()

        runBlocking {
            val filmUnderTest = Film("id", "http://imagem",
                "Bady Boys", "Francisco",
                "Ação", "2019-01-01", "01:30:00",
                "Filme bom"
            )
            dao.save(filmUnderTest)
            Log.d("JSS", "Inserted")
            val films = dao.allFavorites().first()
            films.forEach { film ->
                Log.d("JSS", film.title)
            }
            val result = dao.delete(filmUnderTest)
            Log.d("JSS", "Rows affected: $result")
        }
    }
}
