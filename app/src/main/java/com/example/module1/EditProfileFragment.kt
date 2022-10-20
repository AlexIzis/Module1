package com.example.module1

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val image: ImageView = getView()?.findViewById(R.id.imageEdit) ?: return

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val newImage = it.data?.extras?.get("data") as Bitmap
                // val image: ImageView = getView()?.findViewById(R.id.imageEdit) ?: return@registerForActivityResult
                image.setImageBitmap(newImage)
            }
        }

        fun doPhoto() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            getResult.launch(cameraIntent)
        }

        fun deletePhoto() {
            // val image: ImageView = getView()?.findViewById(R.id.imageEdit) ?: return
            image.setImageResource(R.drawable.ic_photo_camera)
        }

        fun showDialog() {
            val builder = context?.let { AlertDialog.Builder(it) }
            builder?.setItems(
                R.array.values,
                DialogInterface.OnClickListener { _, i ->
                    when (i) {
                        0 -> { Toast.makeText(context, "Ничего не происходит", Toast.LENGTH_SHORT).show() }
                        1 -> { doPhoto() }
                        2 -> { deletePhoto() }
                    }
                }
            )
            val dialog = builder?.create()
            dialog?.show()
        }

        val changeImage: CardView = getView()?.findViewById(R.id.cardView) ?: return
        changeImage.setOnClickListener {
            showDialog()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditProfileFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}