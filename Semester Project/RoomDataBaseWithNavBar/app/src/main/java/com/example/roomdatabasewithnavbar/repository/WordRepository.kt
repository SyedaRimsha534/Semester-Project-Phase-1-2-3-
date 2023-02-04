/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.roomdatabasewithnavbar.repository

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Fruit>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Fruit) {
        wordDao.insert(word)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(word: Fruit) {

        try {
            wordDao.update(word)
//            wordDao.delete(word.FoodName)
        } catch (e: Exception) {
            Log.d("FavouriteIssue", "Repository Exception :$e")
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteFruit(word: Fruit) {

        try {
            wordDao.delete(word.FoodName)
        } catch (e: Exception) {
            Log.d("FavouriteIssue", "Repository Exception :$e")
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWithQuery(word: Fruit) {
        wordDao.updateWithQuery(word.Favourite, word.FoodName)
    }
}
