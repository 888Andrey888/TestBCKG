package com.example.testbckg.presentation.auth.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.testbckg.R
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentLogInBinding
import com.example.testbckg.presentation.auth.AuthViewModel
import com.example.testbckg.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLogInBinding.inflate(inflater, container, false)

    override fun initFlow() {
        viewModel.viewState.onEach {
            when (it) {
                is State.Empty -> {
                    binding.llFields.visibility = View.VISIBLE
                    binding.llButtons.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                is State.Loading -> {
                    binding.llFields.visibility = View.GONE
                    binding.llButtons.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is State.Success -> {
                    if (it.data!!)
                        findNavController().navigate(R.id.mainFragment)
                }

                is State.Error -> {
                    binding.llFields.visibility = View.VISIBLE
                    binding.llButtons.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun initListener() {
        binding.tvRegistration.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_logInFragment_to_signUpUserBottomSheetFragment)
        }

        binding.btnSigning.setOnClickListener {
            if (binding.etUserEmail.text.isNullOrEmpty())
                binding.tltUserEmail.error = getString(R.string.the_field_must_not_be_empty)
            else if (binding.etUserPassword.text.isNullOrEmpty())
                binding.tltUserPassword.error = getString(R.string.the_field_must_not_be_empty)
            else {
                lifecycleScope.launch {
                    viewModel.loginUser(
                        binding.etUserEmail.text.toString(),
                        binding.etUserPassword.text.toString()
                    )
                }
            }
        }
    }

}