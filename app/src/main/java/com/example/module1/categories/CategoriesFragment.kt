package com.example.module1.categories

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.ItemMarginDecoration
import com.example.module1.JsonParser
import com.example.module1.R
import com.google.android.flexbox.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

private const val CATEGORY_LIST = "list_of_categories"

class CategoriesFragment : Fragment(), OnCategoriesCallback {

    private var listCategories = arrayListOf<CategoryUiModel>()
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, ibinder: IBinder) {
            service = (ibinder as LoadCategoriesService.LocalBinder).getService()
        }

        override fun onServiceDisconnected(arg0: ComponentName) = Unit
    }
    private lateinit var loading: ProgressBar
    private val adapter = CategoriesAdapter()
    private lateinit var service: LoadCategoriesService

    override fun onStart() {
        super.onStart()
        Intent(requireContext(), LoadCategoriesService::class.java).also { intent ->
            activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
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
        val layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.SPACE_BETWEEN
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())

        if (savedInstanceState != null) {
            listCategories = savedInstanceState.getParcelableArrayList<CategoryUiModel>(
                CATEGORY_LIST
            ) as ArrayList<CategoryUiModel>
            loading = view.findViewById(R.id.progressBarCategories)
            loading.visibility = View.GONE
            adapter.setCategories(listCategories)
        } else {
            Observable.fromCallable {
                JsonParser(
                    getString(R.string.path_to_categories),
                    CategoryUiModel::class.java,
                    requireContext()
                ).parseJson()
            }
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    Log.d("tag", Thread.currentThread().name)
                }
                .delay(5000, TimeUnit.MILLISECONDS)
                .map { it as ArrayList<CategoryUiModel> }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("tag", Thread.currentThread().name)
                    listCategories = it
                    loading = view.findViewById(R.id.progressBarCategories)
                    loading.visibility = View.GONE
                    adapter.setCategories(listCategories)
                }
        }
    }

    override fun onLoaded(categories: List<CategoryUiModel>) {
        loading = view?.findViewById(R.id.progressBarCategories) ?: return
        loading.visibility = View.GONE
        listCategories = categories as ArrayList<CategoryUiModel>
        adapter.setCategories(categories)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            CATEGORY_LIST,
            listCategories as ArrayList<out Parcelable>
        )
    }
}
