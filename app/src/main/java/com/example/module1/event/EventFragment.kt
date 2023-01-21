package com.example.module1.event

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        if (viewModel.new == null) {
            new = requireNotNull(arguments?.getParcelable(KEY_NEW))
            viewModel.updateNew(new)
        } else {
            new = viewModel.new!!
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
        emailView.text = makeUnderlineString(new.email)

        val siteView: TextView = view.findViewById(R.id.siteEvent)
        siteView.text = makeUnderlineString(new.site)

        viewModel.emitData(new.id)

        val backArrow: ImageView = view.findViewById(R.id.backArrowFromEvent)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun findImageID(name: String): Int {
        return requireContext().resources.getIdentifier(name, "img", requireContext().packageName)
    }

    private fun makeUnderlineString(inputString: String): SpannableString {
        return SpannableString(inputString).apply {
            setSpan(
                UnderlineSpan(), 0, inputString.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }

    companion object {
        const val KEY_NEW = "new"

        fun getInst(new: NewsUIModel): Fragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_NEW, new)
            val fragment = EventFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}