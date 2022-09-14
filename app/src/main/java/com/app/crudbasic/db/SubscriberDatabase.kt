package com.app.crudbasic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//to represent the actual database
@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    //we should only use interface of room
    //database for entire app
    //to avoid unexpected error

    //we create singleton as companion object
    companion object {
        //this reference to the SubscriberDatabase
        @Volatile
        //make field immediately made visible to other thread
        private var INSTANCE: SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
            //in this block, we add synchronized block
            //lock to represent SubscriberDatabase
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_table"
                    ).build()
                }
                return instance
            }
        }
    }
}