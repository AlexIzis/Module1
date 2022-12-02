package com.example.module1.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.os.bundleOf
import com.example.module1.JsonParser
import com.example.module1.R
import com.example.module1.categories.CategoryUiModel

class FilterFragment : Fragment() {

    private val categories = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listFromJson =
            JsonParser(
                getString(R.string.path_to_categories),
                CategoryUiModel::class.java,
                requireContext()
            ).parseJson()

        val checkBoxChildren: CheckBox = view.findViewById(R.id.checkChildren)
        checkBoxChildren.text = listFromJson[0].text
        checkBoxChildren.setOnClickListener {
            categories.add("children")
        }

        val checkBoxAdults: CheckBox = view.findViewById(R.id.checkAdults)
        checkBoxAdults.text = listFromJson[1].text
        checkBoxAdults.setOnClickListener {
            categories.add("adults")
        }

        val checkBoxElderly: CheckBox = view.findViewById(R.id.checkElderly)
        checkBoxElderly.text = listFromJson[2].text
        checkBoxElderly.setOnClickListener {
            categories.add("elderly")
        }

        val checkBoxAnimals: CheckBox = view.findViewById(R.id.checkAnimals)
        checkBoxAnimals.text = listFromJson[3].text
        checkBoxAnimals.setOnClickListener {
            categories.add("animals")
        }

        val checkBoxEvents: CheckBox = view.findViewById(R.id.checkEvents)
        checkBoxEvents.text = listFromJson[4].text
        checkBoxEvents.setOnClickListener {
            categories.add("events")
        }

        val okButton: ImageView = view.findViewById(R.id.sentToNews)
        okButton.setOnClickListener {
            activity?.supportFragmentManager?.setFragmentResult(
                REQUEST_KEY_FILTER,
                bundleOf(KEY_FROM_FILTER to categories)
            )
            parentFragmentManager.popBackStack()
        }

        val backArrow: ImageView = view.findViewById(R.id.backArrowToNews)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        const val KEY_FROM_FILTER = "category"
        const val REQUEST_KEY_FILTER = "result"
    }
}