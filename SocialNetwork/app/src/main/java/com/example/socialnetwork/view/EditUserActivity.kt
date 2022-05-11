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

        viewModel.userEditLiveData.observe(this) {
            binding.editName.setText(it.name)
            binding.editPhoto.setText(it.photo)
            binding.editHobby.setText(it.profession)
            binding.editStatus.setText(it.status)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.confirmButton -> {

                val userEditTextList = listOf(
                    binding.editName.text.toString(), binding.editPhoto.text.toString(),
                    binding.editHobby.text.toString(), binding.editStatus.text.toString()
                )
                if (!viewModel.fieldsIsEmpty(userEditTextList)) {
                    if (!viewModel.fieldHasSpecialCharacters(binding.editName.text.toString())) {
                        viewModel.onUpdateUser(
                            User(
                                getId(),
                                userEditTextList[0],
                                userEditTextList[1],
                                viewModel.userEditLiveData.value!!.onlineStatus,
                                userEditTextList[2],
                                userEditTextList[3]
                            )
                        )
                        finish()
                        return true
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "The name must not contain special characters",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "All field need to be filled in",
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
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