package hu.bme.aut.dogspecies

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import hu.bme.aut.dogspecies.datasource.database.BreedsDatabase
import hu.bme.aut.dogspecies.model.BreedEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class DatabaseUnitTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var database: BreedsDatabase

    @ExperimentalCoroutinesApi
    @Test
    fun insertBreedTest() = runTest {
        // Arrange
        val dao = database.breedsDao()

        // Act
        dao.insertBreed(BreedEntity(id = 1, name = "Affenpinscher"))

        // Assert
        assertEquals(1, dao.getAllBreeds().size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteBreedTest() = runTest {
        // Arrange
        val dao = database.breedsDao()

        // Act
        val breed = BreedEntity(id = 1, name = "Affenpinscher")
        dao.insertBreed(breed)
        dao.deleteBreed(breed)

        // Assert
        assertEquals(0, dao.getAllBreeds().size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getBreedTest() = runTest {
        // Arrange
        val dao = database.breedsDao()

        // Act
        val breed = BreedEntity(id = 1, name = "Affenpinscher")
        dao.insertBreed(breed)
        val breed2 = dao.getBreed(breed.id)

        // Assert
        assertEquals(breed, breed2)
    }


    @After
    fun close() {
        database.close()
    }
}