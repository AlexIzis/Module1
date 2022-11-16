package com.example.module1.auth

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.module1.FragmentNavigation
import com.example.module1.R
import com.example.module1.categories.CategoriesFragment
import com.example.module1.databinding.FragmentLoginBinding
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Observable
import rx.Observer

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private var inputEmail: String = ""
    private var inputPassword: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emailObservable = RxTextView.textChanges(binding.inputLoginEdit)
        val passwordObservable = RxTextView.textChanges(binding.inputLoginPasswordEdit)
        binding.loginButton.setOnClickListener {
            FragmentNavigation().replaceFragment(
                parentFragmentManager, R.id.fragmentContainerView, CategoriesFragment()
            )
        }

        if (savedInstanceState !== null){
            inputEmail = savedInstanceState.getString("mail").toString()
            inputPassword = savedInstanceState.getString("pass").toString()
            binding.inputLoginEdit.setText(inputEmail)
            binding.inputLoginPasswordEdit.setText(inputPassword)
        }

        Observable.combineLatest(
            emailObservable,
            passwordObservable
        ) { newEmail, newPassword ->
            inputEmail = newEmail.toString()
            inputPassword = newPassword.toString()
            (!TextUtils.isEmpty(newEmail) && newEmail.length >= 6) &&
                    (!TextUtils.isEmpty(newPassword) && newPassword.length >= 6)
        }.subscribe(object : Observer<Boolean?> {
            override fun onCompleted() {}
            override fun onError(e: Throwable?) {}
            override fun onNext(t: Boolean?) {
                binding.loginButton.isEnabled = t == true
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("mail", inputEmail)
        outState.putString("pass", inputPassword)
    }
}