package com.example.socialnetwork.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.socialnetwork.R
import com.example.socialnetwork.databinding.ActivityEditUserBinding
import com.example.socialnetwork.model.User
import com.example.socialnetwork.viewmodel.EditUserViewModel

class EditUserActivity : BaseActivity<ActivityEditUserBinding>(ActivityEditUserBinding::inflate) {

    private lateinit var viewModel: EditUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[EditUserViewModel::class.java]
        viewModel.init(getId())

        viewModel.userEditLiveData.observe(this, {
            binding.editName.setText(it.name)
            binding.editPhoto.setText(it.photo)
            binding.editHobby.setText(it.profession)
            binding.editStatus.setText(it.status)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.confirmButton -> {

                val usersEditTextList = listOf(
                    binding.editName, binding.editPhoto,
                    binding.editHobby, binding.editStatus
                )
                for (userEditText in usersEditTextList) {
                    if (userEditText.text.isEmpty() && userEditText != usersEditTextList[1]) {
                        Toast.makeText(
                            applicationContext,
                            "All field need to be filled in",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                }
                val user = User(
                    getId(),
                    usersEditTextList[0].text.toString(),
                    usersEditTextList[1].text.toString(),
                    viewModel.userEditLiveData.value!!.onlineStatus,
                    usersEditTextList[2].text.toString(),
                    usersEditTextList[3].text.toString()
                )
                viewModel.onUpdateUser(user)
                finish()
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