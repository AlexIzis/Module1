package com.example.module1.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.module1.FragmentNavigation
import com.example.module1.R
import com.example.module1.categories.CategoriesFragment
import com.example.module1.databinding.FragmentLoginBinding
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Observable
import rx.Observer
import rx.Subscription

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var subscription: Subscription? = null
    lateinit var emailInputObservable: Observable<CharSequence>
    lateinit var passwordInputObservable: Observable<CharSequence>
    private var currentEmail: String = ""
    private var currentPassword: String = ""
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailInputObservable = RxTextView.textChanges(binding.inputLoginEdit)
        passwordInputObservable = RxTextView.textChanges(binding.inputLoginPasswordEdit)
        binding.loginButton.setOnClickListener {
            FragmentNavigation().replaceFragment(
                parentFragmentManager, R.id.fragmentContainerView,
                CategoriesFragment()
            )
        }
        combineEvent()
    }

    private fun combineEvent() {
        subscription = Observable.combineLatest(
            emailInputObservable,
            passwordInputObservable
        ) { newEmail, newPassword ->
            currentEmail = newEmail.toString()
            currentPassword = newPassword.toString()
            val emailValid = (!TextUtils.isEmpty(newEmail) && newEmail.length > 6)
            val passValid = (!TextUtils.isEmpty(newPassword) && newPassword.length > 6)
            emailValid && passValid
        }.subscribe(object : Observer<Boolean?> {
            override fun onCompleted() {}
            override fun onError(e: Throwable?) {}
            override fun onNext(t: Boolean?) {
                binding.loginButton.isEnabled = t == true
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}