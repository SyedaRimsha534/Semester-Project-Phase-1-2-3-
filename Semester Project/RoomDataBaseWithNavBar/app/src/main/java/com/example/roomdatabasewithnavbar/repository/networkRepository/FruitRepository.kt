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
package com.example.roomdatabasewithnavbar.repository.networkRepository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.roomdatabasewithnavbar.fragments.retrofitFruit.FruitDataModel
import com.example.roomdatabasewithnavbar.fragments.retrofitFruit.FruitModel
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class FruitRepository(private val fruitDao: APIService) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allFruits: Flow<FruitModel> = fruitDao.getFruitsList()

}
