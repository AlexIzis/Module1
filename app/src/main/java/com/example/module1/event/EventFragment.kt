package com.example.module1.event

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
import com.example.module1.FragmentNavigation
import com.example.module1.news.NewsFragment
import com.example.module1.R
import com.example.module1.news.NewsUIModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class EventFragment : Fragment() {
    private lateinit var new: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            new = it.getString("new").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = Gson().fromJson(new, NewsUIModel::class.java)
        val textLabel: TextView = view.findViewById(R.id.eventLabelText)
        textLabel.movementMethod = ScrollingMovementMethod()
        textLabel.setHorizontallyScrolling(true)
        textLabel.text = data.label

        val textHeader: TextView = view.findViewById(R.id.headingEvent)
        textHeader.text = data.label

        val textTime: TextView = view.findViewById(R.id.timeEvent)
        textTime.text = SimpleDateFormat("MMMM dd, yyyy").format(Date(data.time))

        val imgEvent: ImageView = view.findViewById(R.id.imgEvent)
        imgEvent.setImageResource(findID(data.img))

        val imgOptEventUp: ImageView = view.findViewById(R.id.imgOpt_1)
        imgOptEventUp.setImageResource(findID(data.imgOptionally[0]))

        val imgOptEventDown: ImageView = view.findViewById(R.id.imgOpt_2)
        imgOptEventDown.setImageResource(findID(data.imgOptionally[1]))

        val descEvent: TextView = view.findViewById(R.id.descEvent)
        descEvent.text = data.description

        val orgEvent: TextView = view.findViewById(R.id.organisationEvent)
        orgEvent.text = data.organization

        val addEvent: TextView = view.findViewById(R.id.addressEvent)
        addEvent.text = data.address

        val numEvent: TextView = view.findViewById(R.id.numbersEvent)
        numEvent.text = "${data.numberList[0]}\n${data.numberList[1]}"

        val emailView: TextView = view.findViewById(R.id.emailEvent)
        val underlineTextEmail = "<u>${data.email}</u>"
        emailView.text = HtmlCompat.fromHtml(underlineTextEmail, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val siteView: TextView = view.findViewById(R.id.siteEvent)
        val underlineTextSite = "<u>${data.site}</u>"
        siteView.text = HtmlCompat.fromHtml(underlineTextSite, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val backArrow: ImageView = view.findViewById(R.id.backArrowFromEvent)
        backArrow.setOnClickListener {
            FragmentNavigation().addFragment(
                parentFragmentManager,
                R.id.fragmentContainerView,
                NewsFragment()
            )
        }
    }

    private fun findID(name: String): Int {
        return requireContext().resources.getIdentifier(name, "img", requireContext().packageName)
    }
}