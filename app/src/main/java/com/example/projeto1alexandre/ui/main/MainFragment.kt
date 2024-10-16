package com.example.projeto1alexandre.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeto1alexandre.R
import com.example.projeto1alexandre.databinding.FragmentMainBinding
import com.example.projeto1alexandre.ui.BaseFragment
import com.example.projeto1alexandre.ui.adapter.UserAdapter
import com.example.projeto1alexandre.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewBinding.apply {
            configureComponents()

            observerUsers()

            startInitializationsAndCalls()
        }

        return view
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentMainBinding.inflate(inflater, container, false)

    private fun FragmentMainBinding.configureComponents() {
        setupRecyclerView()
    }

    private fun observerUsers() {
        viewModel.users.observe(viewLifecycleOwner) { users ->
            userAdapter.submitList(users)
        }
    }

    private fun startInitializationsAndCalls() {
        viewModel.getUsers()
    }

    private fun FragmentMainBinding.setupRecyclerView() {
        userAdapter = UserAdapter { selectedUser ->
            navigateToUserDetails(selectedUser.login)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun navigateToUserDetails(username: String) {
        findNavController().navigate(
            R.id.action_mainFragment_to_userDetailFragment,
            bundleOf(LOGIN to username)
        )
    }

    companion object {
        const val LOGIN = "username"
    }
}