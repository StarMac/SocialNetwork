package com.example.socialnetwork.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.socialnetwork.R
import com.example.socialnetwork.viewmodel.UserViewModel
import java.io.Serializable

class DetailsUserActivity : AppCompatActivity(), Serializable {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_user)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val detailsUserName: TextView = findViewById(R.id.detailsUserName)
        val detailsTextStatus: TextView = findViewById(R.id.detailsUserStatus)
        val detailsHobby: TextView = findViewById(R.id.detailsUserHobby)
        val detailsImage: ImageView = findViewById(R.id.detailsImage)
        val arguments = intent.extras
        val id : Int = arguments?.getInt("id")!!.toInt()



        viewModel.loadUserDetailsData(id)

        viewModel.userDetailsLiveData.observe(this, Observer {

            detailsUserName.text = it.name
            detailsHobby.text = it.profession
            detailsTextStatus.text = it.status
            Glide.with(this).load(it.photo).error(R.drawable.ic_generic_avatar).into(detailsImage)
        })
    }
}