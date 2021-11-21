package com.picpay.desafio.android.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.view.adapter.UserListAdapter
import com.picpay.desafio.android.viewmodel.UserViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

  private lateinit var adapter: UserListAdapter

  @Inject
  lateinit var userViewModel: UserViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    showUsers()
  }

  override fun createViewBinding(): ActivityMainBinding {
    return ActivityMainBinding.inflate(layoutInflater)
  }

  private fun showUsers() {
    adapter = UserListAdapter()
    viewBinding.recyclerView.adapter = adapter
    viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)

    viewBinding.userListProgressBar.visibility = View.VISIBLE

    callAction(userViewModel.getUsers().toObservable(),
      onSucess = Consumer {
        viewBinding.userListProgressBar.visibility = View.GONE

        adapter.users = it!!
      }, onError = Consumer {
        val message = getString(R.string.error)
        val title = getString(R.string.title_error)
        viewBinding.userListProgressBar.visibility = View.GONE
        viewBinding.recyclerView.visibility = View.GONE

        Log.e(title, message, it.cause)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
      })
  }
}
