package com.example.socialnetwork.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.socialnetwork.R
import com.example.socialnetwork.databinding.ActivityAddUserBinding
import com.example.socialnetwork.model.User
import com.example.socialnetwork.viewmodel.AddUserViewModel
import kotlin.random.Random

class AddUserActivity : BaseActivity<ActivityAddUserBinding>(ActivityAddUserBinding::inflate) {
    private lateinit var viewModel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[AddUserViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.confirmButton -> {

                val onlineStatusDays = Random.nextInt(2, 59)
                val onlineStatus = "$onlineStatusDays minutes ago"

                val userAddList = listOf(
                    binding.addName, binding.addPhoto,
                    binding.addHobby, binding.addStatus
                )
                for (userAddText in userAddList) {
                    if (userAddText.text.isEmpty() && userAddText != userAddList[1]) {
                        Toast.makeText(
                            applicationContext,
                            "All field need to be filled in",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                }
                val user = User(
                    viewModel.count,
                    userAddList[0].text.toString(),
                    userAddList[1].text.toString(),
                    onlineStatus,
                    userAddList[2].text.toString(),
                    userAddList[3].text.toString()
                )
                viewModel.insertUser(user)
                finish()
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}