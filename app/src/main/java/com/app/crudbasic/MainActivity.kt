package com.app.crudbasic

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.crudbasic.adapter.MyRecyclerViewAdapter
import com.app.crudbasic.databinding.ActivityMainBinding
import com.app.crudbasic.db.Subscriber
import com.app.crudbasic.db.SubscriberDatabase
import com.app.crudbasic.repository.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

//        displaySubscriberList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }

    private fun displaySubscriberList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("tag", "displaySubscriberList: $it")
            binding.subscriberRecyclerView.adapter =
                MyRecyclerViewAdapter(it) { selectedItem: Subscriber ->
                    listItemClicked(
                        selectedItem
                    )
                }
        })
    }

    private fun listItemClicked(subscriber: Subscriber) {
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}