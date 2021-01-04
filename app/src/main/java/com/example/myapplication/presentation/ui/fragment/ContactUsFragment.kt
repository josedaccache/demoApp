package com.example.myapplication.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.presentation.viewmodel.ContactUsViewModel
import com.example.myapplication.utils.isEmailValid
import com.example.myapplication.utils.isPhoneValid
import com.example.myapplication.utils.isValidName
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_contact_us.*


class ContactUsFragment : Fragment() {

    private lateinit var contactUsViewModel: ContactUsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactUsViewModel =
            ViewModelProvider(this).get(ContactUsViewModel::class.java)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        btnSendSuccess.setOnClickListener {
            onSendClicked(true)
        }
        btnSendFailure.setOnClickListener {
            onSendClicked(false)
        }
        addFocusChangeListener(etName, tilName)
        addFocusChangeListener(etEmail, tilEmail)
        addFocusChangeListener(etPhone, tilPhone)
    }

    private fun onSendClicked(isSuccess: Boolean) {
        val error = when {
            !etName.text.isNullOrEmpty()
                    && !isValidName(etName.text.toString()) -> true
            !etEmail.text.isNullOrEmpty()
                    && !isEmailValid(etEmail.text.toString()) -> true
            !etPhone.text.isNullOrEmpty()
                    && !isPhoneValid(etPhone.text.toString()) -> true
            etMessage.text.isNullOrEmpty() -> true
            else -> false
        }
        when (error) {
            true -> {
                handleErrors()
                handleMessageError()
            }
            false -> {
                contactUsViewModel.fetchContactUsResponse(isSuccess)
            }
        }

    }

    private fun clearAllFields() {
        etName.setText("")
        etEmail.setText("")
        etPhone.setText("")
        etMessage.setText("")
    }

    private fun addFocusChangeListener(editText: EditText, textInputLayout: TextInputLayout) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                handleErrors()
            else
                textInputLayout.isErrorEnabled = false
        }
    }

    private fun handleErrors() {
        handleNameError()
        handleEmailError()
        handlePhoneError()
    }

    private fun handleNameError() {
        if (!etName.text.isNullOrEmpty()
            && !isValidName(etName.text.toString())
        ) {
            tilName.isErrorEnabled = true
            tilName.error = getString(R.string.label_name_error)
        } else {
            tilName.isErrorEnabled = false
            tilName.error = ""
        }
    }

    private fun handleEmailError() {
        if (!etEmail.text.isNullOrEmpty()
            && !isEmailValid(etEmail.text.toString())
        ) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = getString(R.string.label_email_error)
        } else {
            tilEmail.isErrorEnabled = false
            tilEmail.error = ""
        }
    }

    private fun handlePhoneError() {
        if (!etPhone.text.isNullOrEmpty()
            && !isPhoneValid(etPhone.text.toString())
        ) {
            tilPhone.isErrorEnabled = true
            tilPhone.error = getString(R.string.label_phone_error)
        } else {
            tilPhone.isErrorEnabled = false
            tilPhone.error = ""
        }
    }

    private fun handleMessageError() {
        if (etMessage.text.isNullOrEmpty()) {
            tilMessage.isErrorEnabled = true
            tilMessage.error = getString(R.string.label_message_error)
        } else {
            tilMessage.isErrorEnabled = false
            tilMessage.error = ""
        }
    }

    private fun observeViewModel() {
        contactUsViewModel.response.observe(viewLifecycleOwner, { response ->
            response?.let {
                showResponse(it.message, it.code)
            }
        })
        contactUsViewModel.responseLoadError.observe(viewLifecycleOwner, { isError ->
            isError?.let {
                if (it)
                    showResponse(getString(R.string.label_failure_response), -1)
            }
        })
        contactUsViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                pbLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun showResponse(message: String, code: Int) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        if (code == 1)
            clearAllFields()
    }

}