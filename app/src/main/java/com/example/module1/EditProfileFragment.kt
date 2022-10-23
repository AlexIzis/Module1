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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class EditProfileFragment : Fragment() {
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    private fun doPhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        getResult.launch(cameraIntent)
    }

    private fun deletePhoto() {
        image.setImageResource(R.drawable.ic_photo_camera)
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .apply {
                setItems(
                    R.array.values
                ) {_, i ->
                    when (i) {
                        0 -> {
                            Toast.makeText(context, getString(R.string.nothing), Toast.LENGTH_SHORT)
                                .show()
                        }
                        1 -> {
                            doPhoto()
                        }
                        2 -> {
                            deletePhoto()
                        }
                    }
                }
            }
            .create()
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        image = view.findViewById(R.id.imageEdit)

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val newImage = it.data?.extras?.get("data") as Bitmap
                image.setImageBitmap(newImage)
            }
        }

        val changeImage: CardView = view.findViewById(R.id.cardView)
        changeImage.setOnClickListener {
            showDialog()
        }
    }
}
