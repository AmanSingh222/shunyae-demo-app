package com.example.shunyaekaiassignmentapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shunyaekaiassignmentapp.Adapter.UserAdapter
import com.example.shunyaekaiassignmentapp.R
import com.example.shunyaekaiassignmentapp.ViewModel.UserViewModel
import com.example.shunyaekaiassignmentapp.databinding.ActivityUserListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserListActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    lateinit var  binding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.userRecyclerView)
        val adapter = UserAdapter { user -> viewModel.delete(user) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.users.observe(this) { users ->
            adapter.submitList(users)
        }

        binding.addUserButton.setOnClickListener {
            startActivity(Intent(this, UserFormActivity::class.java))
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val user = adapter.currentList[viewHolder.adapterPosition]

                AlertDialog.Builder(this@UserListActivity)
                    .setTitle("Delete User")
                    .setMessage("Are you sure you want to delete this user?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.delete(user)
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .setOnCancelListener {
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }).attachToRecyclerView(recyclerView)

    }
}
