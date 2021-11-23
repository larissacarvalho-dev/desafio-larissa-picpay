package com.picpay.desafio.android.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.multistatelayout.MultiStateLayout
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.databinding.EmptyLayoutBinding
import com.picpay.desafio.android.databinding.ErrorLayoutBinding
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
    viewBinding.multiStateLayout.setState(MultiStateLayout.State.LOADING)
    adapter = UserListAdapter()
    viewBinding.recyclerView.adapter = adapter
    viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)

    callAction(userViewModel.getUserPresentation().toObservable(),
      onSucess = Consumer {
        if (it.itemUserPresentation.isEmpty()) {
          buttonBackErrorState()
          viewBinding.multiStateLayout.setState(MultiStateLayout.State.ERROR)
        } else {
          viewBinding.title.text = getString(it.txtTitle)
          adapter.users = it.itemUserPresentation
          viewBinding.multiStateLayout.setState(MultiStateLayout.State.CONTENT)
        }
      }, onError = Consumer {
        val message = getString(R.string.error)
        val title = getString(R.string.title_error)
        buttonBackEmptyState()
        viewBinding.multiStateLayout.setState(MultiStateLayout.State.EMPTY)
        Log.e(title, message, it.cause)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
      })
  }

  private fun buttonBackEmptyState() {
    val view: View? = viewBinding.multiStateLayout.getView(MultiStateLayout.State.EMPTY)

    if (view != null) {
      val emptyLayout: EmptyLayoutBinding = EmptyLayoutBinding.bind(view)
      emptyLayout.btnBackEmpty.setOnClickListener {
        onBackPressed()
      }
    }
  }

  private fun buttonBackErrorState() {
    val view: View? = viewBinding.multiStateLayout.getView(MultiStateLayout.State.ERROR)

    if (view != null) {
      val errorLayout: ErrorLayoutBinding = ErrorLayoutBinding.bind(view)
      errorLayout.btnBackError.setOnClickListener {
        onBackPressed()
      }
    }
  }
}
