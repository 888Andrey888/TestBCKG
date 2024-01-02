package com.example.testbckg.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testbckg.R
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentAuthPhoneBinding
import com.example.testbckg.domain.models.DefaultUserModel
import com.example.testbckg.domain.models.TouristModel
import com.example.testbckg.utils.Constants
import com.example.testbckg.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthPhoneFragment : BaseFragment<FragmentAuthPhoneBinding>() {

    private val viewModel by viewModels<AuthViewModel>()

    private val countries = listOf("Tourist", "Guide")
    private var userType = 0

    override fun inflaterViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentAuthPhoneBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
    }

    override fun initFlow() {
        viewModel.viewState.onEach {
            when (it) {
                is State.Empty -> {
                    binding.llUserInformation.visibility = View.VISIBLE
                    binding.llNextButton.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                is State.Loading -> {
                    binding.llUserInformation.visibility = View.GONE
                    binding.llNextButton.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is State.Success -> {
                    if (it.data!!)
                        findNavController().navigate(R.id.mainFragment)
                }

                is State.Error -> {
                    binding.llUserInformation.visibility = View.VISIBLE
                    binding.llNextButton.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun initListener() {
        binding.btnNext.setOnClickListener {
            when (userType) {
                0 -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        if (checkFields())
                            viewModel.registerNewTourist(makeTouristModel())
                    }
                }

                1 -> {
                    if (checkFields()) {
                        val defaultUserModel = DefaultUserModel(
                            firstName = binding.etFirstName.text.toString(),
                            lastName = binding.etUserLastName.text.toString(),
                            email = binding.etUserEmail.text.toString(),
                            password = binding.etUserPassword.text.toString()
                        )
                        findNavController().navigate(
                            R.id.authGuideFragment,
                            bundleOf(Constants.AUTH_GUIDE to defaultUserModel)
                        )
                    }
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        var isSuccess = false

        if (binding.etFirstName.text.isNullOrEmpty()) {
            binding.tltUserFirstName.error = getString(R.string.the_field_must_not_be_empty)
            isSuccess = false
        } else if (binding.etUserLastName.text.isNullOrEmpty()) {
            binding.tltUserLastName.error = getString(R.string.the_field_must_not_be_empty)
            isSuccess = false
        } else if (binding.etUserEmail.text.isNullOrEmpty()) {
            binding.tltUserEmail.error = getString(R.string.the_field_must_not_be_empty)
            isSuccess = false
        } else if (binding.etUserPassword.text.isNullOrEmpty()) {
            binding.tltUserPassword.error = getString(R.string.the_field_must_not_be_empty)
        } else {
            isSuccess = true
        }

        return isSuccess
    }

    private fun makeTouristModel() = TouristModel(
        id = null,
        firstName = binding.etFirstName.text.toString(),
        lastName = binding.etUserLastName.text.toString(),
        email = binding.etUserEmail.text.toString(),
        password = binding.etUserPassword.text.toString()
    )

    private fun initSpinner() {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerUserType.adapter = adapter
        binding.spinnerUserType.setSelection(0)

        binding.spinnerUserType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                userType = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

}