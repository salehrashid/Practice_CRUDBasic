package com.app.crudbasic.repository

import com.app.crudbasic.db.Subscriber
import com.app.crudbasic.db.SubscriberDAO

//repo ini digunakan untuk menghubungkan data dengan view
class SubscriberRepository(private val dao: SubscriberDAO) {
    val subscribers = dao.getAllSubscriber()

    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}