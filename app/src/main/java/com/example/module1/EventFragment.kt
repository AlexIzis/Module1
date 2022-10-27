package com.example.module1

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventFragment : Fragment() {
    private lateinit var label: String
    private lateinit var desc: String
    private lateinit var time: String
    private var img: Int = 0
    private var identifier: Int = 0
    private lateinit var organization: String
    private lateinit var address: String
    private lateinit var numList: Array<String>
    private lateinit var email: String
    private lateinit var imgOpt: Array<Int>
    private lateinit var site: String
    private lateinit var categories: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            identifier = it.getInt("id")
            label = it.getString("label").toString()
            desc = it.getString("desc").toString()
            time = it.getString("time").toString()
            img = it.getInt("img")
            organization = it.getString("org").toString()
            address = it.getString("address").toString()
            numList = it.getStringArray("numList") as Array<String>
            email = it.getString("email").toString()
            //imgOpt = it.getIntArray("imgOpt") as Array<Int>
            site = it.getString("site").toString()
            categories = it.getStringArray("categories") as Array<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textLabel: TextView = view.findViewById(R.id.EventLabelText)
        textLabel.movementMethod = ScrollingMovementMethod()
        textLabel.setHorizontallyScrolling(true)
        textLabel.text = label

        val textHeader: TextView = view.findViewById(R.id.headingEvent)
        textHeader.text = label

        val textTime: TextView = view.findViewById(R.id.timeEvent)
        textTime.text = time

        val imgEvent: ImageView = view.findViewById(R.id.imgEvent)
        imgEvent.setImageResource(img)

        val descEvent: TextView = view.findViewById(R.id.descEvent)
        descEvent.text = desc

        val orgEvent: TextView = view.findViewById(R.id.organisationEvent)
        orgEvent.text = organization

        val addEvent: TextView = view.findViewById(R.id.addressEvent)
        addEvent.text = address

        val numEvent: TextView = view.findViewById(R.id.numbersEvent)
        numEvent.text = "${numList[0]}\n${numList[1]}"

        val emailView: TextView = view.findViewById(R.id.emailEvent)
        val underlineTextEmail = "<u>$email</u>"
        emailView.text = HtmlCompat.fromHtml(underlineTextEmail, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val siteView: TextView = view.findViewById(R.id.siteEvent)
        val underlineTextSite = "<u>$site</u>"
        siteView.text = HtmlCompat.fromHtml(underlineTextSite, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val backArrow: ImageView = view.findViewById(R.id.back_arrow_from_event)
        backArrow.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, NewsFragment())
            fragmentTransaction.commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}