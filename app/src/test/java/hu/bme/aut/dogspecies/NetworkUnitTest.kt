package hu.bme.aut.dogspecies

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.dogspecies.datasource.network.BreedApi
import hu.bme.aut.dogspecies.model.BreedDto
import hu.bme.aut.dogspecies.model.Height
import hu.bme.aut.dogspecies.model.Image
import hu.bme.aut.dogspecies.model.Weight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class NetworkUnitTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var api: BreedApi

    @Inject
    lateinit var mockWebServer: MockWebServer

    @ApplicationContext
    @Inject
    lateinit var context: Context

    private fun readJsonFromAssets(context: Context, filePath: String): String? {
        return try {
            val source = context.assets.open(filePath).source().buffer()
            source.readByteString().string(Charset.forName("utf-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertBreedTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets(context, "getBreed1.json").orEmpty()))

        // Act
        val actualDto = api.getBreed(1)

        // Assert
        val expectedDto = BreedDto(
            bredFor = " Small rodent hunting, lapdog",
        breedGroup = "Toy",
        height = Height("9 - 11.5", "23 - 29"),
        id =  1,
        image = Image(1199, "BJa4kxc4X","https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",1600),
        lifeSpan = "10 - 12 years",
        name = "Affenpinscher",
            origin = "Germany, France",
        referenceImageId =  "BJa4kxc4X",
        temperament =  "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        weight = Weight("6 - 13", "3 - 6"),
        )
        assertEquals(expectedDto.name, actualDto.name)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}