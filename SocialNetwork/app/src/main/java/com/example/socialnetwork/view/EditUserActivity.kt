package com.example.socialnetwork.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.socialnetwork.R
import com.example.socialnetwork.model.User
import com.example.socialnetwork.viewmodel.EditUserViewModel

class EditUserActivity : AppCompatActivity() {

    private lateinit var viewModel: EditUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(EditUserViewModel::class.java)

        val editUserName: EditText = findViewById(R.id.editName)
        val editPhoto: EditText = findViewById(R.id.editPhoto)
        val editStatus: EditText = findViewById(R.id.editStatus)
        val editHobby: EditText = findViewById(R.id.editHobby)

        viewModel.userEditLiveData.observe(this, {
            editUserName.setText(it.name)
            editPhoto.setText(it.photo)
            editHobby.setText(it.profession)
            editStatus.setText(it.status)
        })

        viewModel.loadUserDetailsData(getId())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){

            R.id.confirmButton -> {

                val usersEditTextList = listOf<EditText>(
                    findViewById(R.id.editName), findViewById(R.id.editPhoto),
                    findViewById(R.id.editHobby), findViewById(R.id.editStatus)
                )
                    for (userEditText in usersEditTextList){
                        if (userEditText.text.isEmpty() && userEditText != usersEditTextList[1])
                        {
                            Toast.makeText(applicationContext, "All field need to be filled in", Toast.LENGTH_SHORT).show()
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
                viewModel.updateUser(user)
                finish()
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