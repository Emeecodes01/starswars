package com.example.local.database.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.local.database.StarWarsDatabase
import com.example.local.testutils.LocalInstrumentedFakeDataGenerator
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RecentCharacterDaoTest {
    private lateinit var recentCharacterDao: RecentCharacterDao
    private lateinit var testDb: StarWarsDatabase


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        testDb = Room.inMemoryDatabaseBuilder(
            context, StarWarsDatabase::class.java
        ).build()

        recentCharacterDao = testDb.recentDao()
    }


    @Test
    fun verify_that_saveRecentCharacter_actually_saves_a_character() = runBlocking {
        val character = LocalInstrumentedFakeDataGenerator.getCharacterLocal(randomBuild(String::class.java))
        val id = recentCharacterDao.saveRecentCharacter(character)

        assertThat(id)
            .isGreaterThan(0)
    }


    @Test
    fun verify_that_saving_a_character_with_the_same_name_updated_the_first() = runBlocking {
        val character1 = LocalInstrumentedFakeDataGenerator.getCharacterLocal("name01")
        val character2 = LocalInstrumentedFakeDataGenerator.getCharacterLocal("name01")
        recentCharacterDao.saveRecentCharacter(character1)
        recentCharacterDao.saveRecentCharacter(character2)

        val recents = recentCharacterDao.getAllRecentCharacters().first()

        assertThat(recents)
            .hasSize(1)

        assertThat(recents[0].name)
            .isEqualTo("name01")
    }



    @Test
    fun verify_that_getAllRecentCharacters_gets_all_characters_saved() = runBlocking {
        val character1 = LocalInstrumentedFakeDataGenerator.getCharacterLocal("name01").also { it.timeFetched = 1343 }
        val character2 = LocalInstrumentedFakeDataGenerator.getCharacterLocal("name02").also { it.timeFetched = 2344 }
        recentCharacterDao.saveRecentCharacter(character1)
        recentCharacterDao.saveRecentCharacter(character2)

        val recents = recentCharacterDao.getAllRecentCharacters().first()

        assertThat(recents).hasSize(2)
        assertThat(recents[0]).isEqualTo(character2)
    }



    @Test
    fun verify_that_deleteCharacterWithName_gets_all_characters_saved() = runBlocking {
        val character1 = LocalInstrumentedFakeDataGenerator.getCharacterLocal(randomBuild(String::class.java)).also { it.timeFetched = 1343 }
        recentCharacterDao.saveRecentCharacter(character1)

        recentCharacterDao.deleteCharacterWithName(character1.name)
        val result = recentCharacterDao.getAllRecentCharacters().first()

        assertThat(result)
            .hasSize(0)
    }


}