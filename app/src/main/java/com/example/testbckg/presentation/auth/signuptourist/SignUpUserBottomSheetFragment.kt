package com.example.testbckg.presentation.auth.signuptourist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.testbckg.R
import com.example.testbckg.databinding.BottomSheetDialogSignUpUserBinding
import com.example.testbckg.domain.models.DefaultUserModel
import com.example.testbckg.domain.models.TouristModel
import com.example.testbckg.presentation.activitys.auth.AuthActivity
import com.example.testbckg.presentation.activitys.main.MainActivity
import com.example.testbckg.presentation.auth.AuthViewModel
import com.example.testbckg.utils.State
import com.example.testbckg.utils.replaceActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpUserBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDialogSignUpUserBinding
    override fun getTheme() = R.style.AppBottomSheetDialogTheme
    private val viewModel by viewModels<AuthViewModel>()
    private val countries = listOf("Tourist", "Guide")
    private var userType = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogSignUpUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initSpinner()
        initFlow()
    }

    private fun initListener() {
        binding.fabSignUpUser.setOnClickListener {
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
                    }
                }
            }
        }
    }

    private fun initFlow() {
        viewModel.viewState.onEach {
            when (it) {
                is State.Empty -> {
                    binding.llUserInformation.visibility = View.VISIBLE
                    binding.fabSignUpUser.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                is State.Loading -> {
                    binding.llUserInformation.visibility = View.GONE
                    binding.fabSignUpUser.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is State.Success -> {
                    if (it.data!!)
                        (activity as AuthActivity).replaceActivity(MainActivity())
                }

                is State.Error -> {
                    binding.llUserInformation.visibility = View.VISIBLE
                    binding.fabSignUpUser.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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

        binding.spinnerUserType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
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