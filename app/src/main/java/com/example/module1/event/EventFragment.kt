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
import androidx.fragment.app.activityViewModels
import com.example.module1.R
import com.example.module1.VMNewsFlow
import com.example.module1.news.NewsUIModel
import java.text.SimpleDateFormat
import java.util.*

class EventFragment : Fragment() {
    private lateinit var new: NewsUIModel
    private val viewModel: VMNewsFlow by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            new = requireNotNull(it.getParcelable(KEY_NEW))
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
        val textLabel: TextView = view.findViewById(R.id.eventLabelText)
        textLabel.movementMethod = ScrollingMovementMethod()
        textLabel.setHorizontallyScrolling(true)
        textLabel.text = new.label

        val textHeader: TextView = view.findViewById(R.id.headingEvent)
        textHeader.text = new.label

        val textTime: TextView = view.findViewById(R.id.timeEvent)
        textTime.text = SimpleDateFormat("MMMM dd, yyyy").format(Date(new.time))

        val imgEvent: ImageView = view.findViewById(R.id.imgEvent)
        imgEvent.setImageResource(findImageID(new.img))

        val imgOptEventUp: ImageView = view.findViewById(R.id.imgOpt_1)
        imgOptEventUp.setImageResource(findImageID(new.imgOptionally[0]))

        val imgOptEventDown: ImageView = view.findViewById(R.id.imgOpt_2)
        imgOptEventDown.setImageResource(findImageID(new.imgOptionally[1]))

        val descEvent: TextView = view.findViewById(R.id.descEvent)
        descEvent.text = new.description

        val orgEvent: TextView = view.findViewById(R.id.organisationEvent)
        orgEvent.text = new.organization

        val addEvent: TextView = view.findViewById(R.id.addressEvent)
        addEvent.text = new.address

        val numEvent: TextView = view.findViewById(R.id.numbersEvent)
        numEvent.text = "${new.numberList[0]}\n${new.numberList[1]}"

        val emailView: TextView = view.findViewById(R.id.emailEvent)
        val underlineTextEmail = "<u>${new.email}</u>"
        /*val underlineTextEmail = SpannableString(new.email).apply {
            setSpan(
                UnderlineSpan(), 0, new.email.length - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }*/
        emailView.text = HtmlCompat.fromHtml(underlineTextEmail, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val siteView: TextView = view.findViewById(R.id.siteEvent)
        val underlineTextSite = "<u>${new.site}</u>"
        siteView.text = HtmlCompat.fromHtml(underlineTextSite, HtmlCompat.FROM_HTML_MODE_LEGACY)

        viewModel.emitData(new.id)

        val backArrow: ImageView = view.findViewById(R.id.backArrowFromEvent)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun findImageID(name: String): Int {
        return requireContext().resources.getIdentifier(name, "img", requireContext().packageName)
    }

    companion object {
        const val KEY_NEW = "new"
    }
}