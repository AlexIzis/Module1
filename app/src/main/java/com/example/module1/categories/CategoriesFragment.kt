package com.example.module1.categories

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.ItemMarginDecoration
import com.example.module1.R
import com.google.android.flexbox.*
import java.util.ArrayList

const val CATEGORY_LIST = "list_of_categories"

class CategoriesFragment : Fragment() {
    private var listFromJson: ArrayList<CategoryUiModel> = ArrayList()
    private var mBound: Boolean = false
    private /*lateinit*/ var mService/*: LoadCategoriesService*/ = LoadCategoriesService().LocalBinder().getService()
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as LoadCategoriesService.LocalBinder
            mService = binder.getService()
            mBound = true
        }
        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Intent(requireContext(), LoadCategoriesService::class.java).also { intent ->
            activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
        mBound = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHelp)
        val adapter = CategoriesAdapter()
        val layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.SPACE_BETWEEN
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())

        //val executor = Executors.newSingleThreadExecutor()
        val loading: ProgressBar = view.findViewById(R.id.progressBarCategories)
        if (savedInstanceState != null) {
            listFromJson =
                savedInstanceState.getParcelableArrayList<CategoryUiModel>(CATEGORY_LIST) as ArrayList<CategoryUiModel>
            adapter.setCategories(listFromJson)
            loading.visibility = View.GONE
        } else {
            listFromJson = mService.loadData()
            adapter.setCategories(listFromJson)
            loading.visibility = View.GONE
            /*executor.execute {
                Thread.sleep(5000)
                listFromJson =
                    JsonParser(
                        getString(R.string.path_to_categories),
                        CategoryUiModel::class.java,
                        requireContext()
                    ).parseJson() as ArrayList<CategoryUiModel>
                activity?.runOnUiThread {
                    adapter.setCategories(listFromJson)
                    loading.visibility = View.GONE
                }
            }*/
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            CATEGORY_LIST,
            listFromJson as ArrayList<out Parcelable>
        )
    }
}
