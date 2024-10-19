package com.example.projeto1alexandre.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.databinding.FragmentUserDetailsBinding
import com.example.projeto1alexandre.ui.base.BaseFragment
import com.example.projeto1alexandre.ui.adapter.RepoAdapter
import com.example.projeto1alexandre.ui.fragments.MainFragment.Companion.LOGIN
import com.example.projeto1alexandre.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : BaseFragment<FragmentUserDetailsBinding>() {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var repoAdapter: RepoAdapter
    private val props = Props()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewBinding.apply {
            initializeScreenWithParameters()

            configureComponents()

            observerUserDetails()

            startInitializationsAndCalls()
        }

        return view
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)

    private fun initializeScreenWithParameters() {
        props.login = arguments?.getString(LOGIN)
    }

    private fun FragmentUserDetailsBinding.configureComponents() {
        setupAguarde()
        setupRecyclerView()
    }

    private fun startInitializationsAndCalls() {
        props.login?.let {
            viewModel.getAllData(it)
        }
    }

    private fun FragmentUserDetailsBinding.setupAguarde() {
        val blinkAnimation = AlphaAnimation(0.0f, 1.0f) // Transparência de 0% a 100%
        blinkAnimation.apply {
            duration = 500 // Duração de cada fase do piscar (500ms)
            startOffset = 20 // Intervalo antes de iniciar a animação
            repeatMode = Animation.REVERSE // Inverte para criar o efeito de piscar
            repeatCount = Animation.INFINITE // Repetir infinitamente
        }
        txtAguarde.startAnimation(blinkAnimation)
    }

    private fun FragmentUserDetailsBinding.setupRecyclerView() {
        repoAdapter = RepoAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    private fun FragmentUserDetailsBinding.observerUserDetails() {
        viewModel.allDataReceived.observe(viewLifecycleOwner) { data ->
            showUserDetails(data.first)
            repoAdapter.submitList(data.second)
            showScreen()
        }
    }

    private fun FragmentUserDetailsBinding.showUserDetails(user: User) {
        user.run {
            txtName.text = login
            txtBio.text = bio
            imgAvatar.load(user.avatarUrl)
        }
    }

    private fun FragmentUserDetailsBinding.showScreen() {
        txtAguarde.clearAnimation()
        txtAguarde.visibility = View.GONE
        blockView.visibility = View.GONE
    }

    class Props {
        var login: String? = null
    }
}