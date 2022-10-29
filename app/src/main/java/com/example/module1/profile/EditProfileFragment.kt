package com.example.module1.profile

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.module1.R
import java.util.*

const val REQUEST_CODE = 1

class EditProfileFragment : Fragment() {
    private lateinit var getResultFromCamera: ActivityResultLauncher<Intent>
    private lateinit var getResultFromGallery: ActivityResultLauncher<Intent>
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    private fun takePhoto() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        getResultFromGallery.launch(intent)

    }

    private fun doPhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        getResultFromCamera.launch(cameraIntent)
    }

    private fun deletePhoto() {
        image.setImageResource(R.drawable.ic_photo_camera)
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .apply {
                setItems(
                    R.array.values
                ) { _, i ->
                    when (i) {
                        0 -> {
                            takePhoto()
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

        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CODE
            )
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }

        getResultFromCamera =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val newImage = it.data?.extras?.get("data") as Bitmap
                    image.setImageBitmap(newImage)
                }
            }

        getResultFromGallery =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK ) {
                    val n = it.data?.data
                    image.setImageURI(n)
                }
            }

        val changeImage: CardView = view.findViewById(R.id.cardView)
        changeImage.setOnClickListener {
            showDialog()
        }

        val dateBirthInput: TextView = view.findViewById(R.id.inputDate)
        dateBirthInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val month = mapOf(
                0 to getString(R.string.January),
                1 to getString(R.string.February),
                2 to getString(R.string.March),
                3 to getString(R.string.April),
                4 to getString(R.string.May),
                5 to getString(R.string.June),
                6 to getString(R.string.July),
                7 to getString(R.string.August),
                8 to getString(R.string.September),
                9 to getString(R.string.October),
                10 to getString(R.string.November),
                11 to getString(R.string.December)
            )
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, months, day ->
                val date = "$day ${month[months]} $year"
                dateBirthInput.text = date
            }
            DatePickerDialog(
                requireContext(), dateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val backArrow: ImageView = view.findViewById(R.id.back_arrow)
        backArrow.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, ProfileFragment())
            fragmentTransaction.commit()
        }
    }
}
