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

        val usersLayoutList = listOf<LinearLayout>(
            findViewById(R.id.layout1), findViewById(R.id.layout2),
            findViewById(R.id.layout3), findViewById(R.id.layout4),
            findViewById(R.id.layout5), findViewById(R.id.layout6),
            findViewById(R.id.layout7)
        )

        val usersTextList = listOf<TextView>(
            findViewById(R.id.userName1), findViewById(R.id.userName2),
            findViewById(R.id.userName3), findViewById(R.id.userName4),
            findViewById(R.id.userName5), findViewById(R.id.userName6),
            findViewById(R.id.userName7)
        )

        val usersOnlineList = listOf<TextView>(
            findViewById(R.id.onlineStatus1), findViewById(R.id.onlineStatus2),
            findViewById(R.id.onlineStatus3), findViewById(R.id.onlineStatus4),
            findViewById(R.id.onlineStatus5), findViewById(R.id.onlineStatus6),
            findViewById(R.id.onlineStatus7)
        )

        val usersPhotoList = listOf<ImageView>(
            findViewById(R.id.profile_image1), findViewById(R.id.profile_image2),
            findViewById(R.id.profile_image3), findViewById(R.id.profile_image4),
            findViewById(R.id.profile_image5), findViewById(R.id.profile_image6),
            findViewById(R.id.profile_image7)
        )

        viewModel.loadUserData()

        viewModel.userLiveData.observe(this, Observer {
            for (id in usersLayoutList.indices){
                usersLayoutList[id].setOnClickListener{onClick(id)}
                usersTextList[id].text = it[id].name
                usersOnlineList[id].text = it[id].onlineStatus
                Glide.with(this).load(it[id].photo).error(R.drawable.ic_generic_avatar).into(usersPhotoList[id])
            }
        })
    }

    private fun onClick(index: Int) {
        val intent = Intent(this, DetailsUserActivity::class.java)
        intent.putExtra("id", index)
        startActivity(intent)
    }
}