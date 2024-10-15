package com.example.projeto1alexandre.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeto1alexandre.data.model.User
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
            navigateToUserDetails(selectedUser)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun navigateToUserDetails(user: User) {
        //val action = MainFragmentDirections.actionMainFragmentToUserDetailFragment(user.login)
        //findNavController().navigate(action)
    }
}