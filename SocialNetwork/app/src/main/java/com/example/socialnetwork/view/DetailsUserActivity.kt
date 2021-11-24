package com.example.socialnetwork.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.socialnetwork.R
import com.example.socialnetwork.viewmodel.DetailsUserViewModel
import java.io.Serializable

class DetailsUserActivity : AppCompatActivity(), Serializable {
    private lateinit var viewModel: DetailsUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(DetailsUserViewModel::class.java)

        val detailsUserName: TextView = findViewById(R.id.detailsUserName)
        val detailsTextStatus: TextView = findViewById(R.id.detailsUserStatus)
        val detailsHobby: TextView = findViewById(R.id.detailsUserHobby)
        val detailsImage: ImageView = findViewById(R.id.detailsImage)


        viewModel.loadUserDetailsData(getId())

        viewModel.userDetailsLiveData.observe(this, Observer {

            detailsUserName.text = it.name
            detailsHobby.text = it.profession
            detailsTextStatus.text = it.status
            Glide.with(this).load(it.photo).error(R.drawable.ic_generic_avatar).into(detailsImage)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_user, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.editButton -> {
                val intent = Intent(this, EditUserActivity::class.java)
                intent.putExtra("id", getId())
                startActivity(intent)
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun getId() : Int {
        val arguments = intent.extras
        val id : Int = arguments?.getInt("id")!!.toInt()
        return id
    }
}