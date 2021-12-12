package com.example.socialnetwork.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.R
import com.example.socialnetwork.adapter.OnItemClick
import com.example.socialnetwork.adapter.UserAdapter
import com.example.socialnetwork.model.User
import com.example.socialnetwork.model.UserDataBase
import com.example.socialnetwork.viewmodel.UserViewModel

class UserListActivity : AppCompatActivity(), OnItemClick {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

        val recyclerView: RecyclerView = findViewById(R.id.userList)
        val adapter = UserAdapter(this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.users.observe(this, {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addButton -> {
                val intent = Intent(this, AddUserActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onClick(userId: Int) {
        val intent = Intent(this, DetailsUserActivity::class.java)
        intent.putExtra("id", userId)
        startActivity(intent)
    }

}