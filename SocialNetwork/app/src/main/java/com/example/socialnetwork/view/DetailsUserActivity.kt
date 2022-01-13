package com.example.socialnetwork.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.socialnetwork.R
import com.example.socialnetwork.databinding.ActivityDetailsUserBinding
import com.example.socialnetwork.viewmodel.DetailsUserViewModel
import java.io.Serializable

class DetailsUserActivity :
    BaseActivity<ActivityDetailsUserBinding>(ActivityDetailsUserBinding::inflate), Serializable {
    private lateinit var viewModel: DetailsUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[DetailsUserViewModel::class.java]
        viewModel.init(getId())

        viewModel.userDetailsLiveData.observe(this, {
            if (it == null) {
                finish()
                return@observe
            }
            binding.detailsUserName.text = it.name
            binding.detailsUserHobby.text = it.profession
            binding.detailsUserStatus.text = it.status
            Glide.with(this).load(it.photo).error(R.drawable.ic_generic_avatar)
                .into(binding.detailsImage)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_user, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.editButton -> {
                val intent = Intent(this, EditUserActivity::class.java)
                intent.putExtra("id", getId())
                startActivity(intent)
            }
            R.id.deleteButton -> {
                finish()
                viewModel.deleteUser(getId())
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getId(): Int {
        val arguments = intent.extras
        return arguments?.getInt("id")!!.toInt()
    }
}