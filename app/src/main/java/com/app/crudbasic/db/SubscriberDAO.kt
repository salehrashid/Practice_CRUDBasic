package com.app.crudbasic.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {
    //here we will access database with room

    //because it might lock database access on the main thread
    //suspend can be paused and resumed at a later time
    //Insert untuk memasukan data
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    //cause we don't need to execute this function in background thread using coroutine
    //since this function return LiveData, Room library it's work from a background thread
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscriber(): LiveData<List<Subscriber>>
}