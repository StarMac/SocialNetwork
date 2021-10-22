package com.example.socialnetwork.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.socialnetwork.R
import com.example.socialnetwork.viewmodel.UserViewModel

class UserListActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val user1: LinearLayout = findViewById(R.id.layout1)
        val user2: LinearLayout = findViewById(R.id.layout2)
        val user3: LinearLayout = findViewById(R.id.layout3)
        val user4: LinearLayout = findViewById(R.id.layout4)
        val user5: LinearLayout = findViewById(R.id.layout5)
        val user6: LinearLayout = findViewById(R.id.layout6)
        val user7: LinearLayout = findViewById(R.id.layout7)

        val userName1: TextView = findViewById(R.id.userName1)
        val userName2: TextView = findViewById(R.id.userName2)
        val userName3: TextView = findViewById(R.id.userName3)
        val userName4: TextView = findViewById(R.id.userName4)
        val userName5: TextView = findViewById(R.id.userName5)
        val userName6: TextView = findViewById(R.id.userName6)
        val userName7: TextView = findViewById(R.id.userName7)

        val userOnline1: TextView = findViewById(R.id.onlineStatus1)
        val userOnline2: TextView = findViewById(R.id.onlineStatus2)
        val userOnline3: TextView = findViewById(R.id.onlineStatus3)
        val userOnline4: TextView = findViewById(R.id.onlineStatus4)
        val userOnline5: TextView = findViewById(R.id.onlineStatus5)
        val userOnline6: TextView = findViewById(R.id.onlineStatus6)
        val userOnline7: TextView = findViewById(R.id.onlineStatus7)

        val userPhoto1: ImageView = findViewById(R.id.profile_image1)
        val userPhoto2: ImageView = findViewById(R.id.profile_image2)
        val userPhoto3: ImageView = findViewById(R.id.profile_image3)
        val userPhoto4: ImageView = findViewById(R.id.profile_image4)
        val userPhoto5: ImageView = findViewById(R.id.profile_image5)
        val userPhoto6: ImageView = findViewById(R.id.profile_image6)
        val userPhoto7: ImageView = findViewById(R.id.profile_image7)

        viewModel.loadUserData()

        viewModel.userLiveData.observe(this, Observer {

            user1.setOnClickListener { onClick(0) }
            userName1.text = it.userList[0].name
            userOnline1.text = it.userList[0].onlineStatus
            Glide.with(this).load(it.userList[0].photo).error(R.drawable.ic_generic_avatar).into(userPhoto1)

            user2.setOnClickListener { onClick(1) }
            userName2.text = it.userList[1].name
            userOnline2.text = it.userList[1].onlineStatus
            Glide.with(this).load(it.userList[1].photo).error(R.drawable.ic_generic_avatar).into(userPhoto2)

            user3.setOnClickListener { onClick(2) }
            userName3.text = it.userList[2].name
            userOnline3.text = it.userList[2].onlineStatus
            Glide.with(this).load(it.userList[2].photo).error(R.drawable.ic_generic_avatar).into(userPhoto3)

            user4.setOnClickListener { onClick(3) }
            userName4.text = it.userList[3].name
            userOnline4.text = it.userList[3].onlineStatus
            Glide.with(this).load(it.userList[3].photo).error(R.drawable.ic_generic_avatar).into(userPhoto4)

            user5.setOnClickListener { onClick(4) }
            userName5.text = it.userList[4].name
            userOnline5.text = it.userList[4].onlineStatus
            Glide.with(this).load(it.userList[4].photo).error(R.drawable.ic_generic_avatar).into(userPhoto5)

            user6.setOnClickListener { onClick(5) }
            userName6.text = it.userList[5].name
            userOnline6.text = it.userList[5].onlineStatus
            Glide.with(this).load(it.userList[5].photo).error(R.drawable.ic_generic_avatar).into(userPhoto6)

            user7.setOnClickListener { onClick(6) }
            userName7.text = it.userList[6].name
            userOnline7.text = it.userList[6].onlineStatus
            Glide.with(this).load(it.userList[6].photo).error(R.drawable.ic_generic_avatar).into(userPhoto7)
        })
    }

    private fun onClick(index: Int) {
        val intent = Intent(this, DetailsUserActivity::class.java)
        intent.putExtra("id", index)
        startActivity(intent)
    }
}