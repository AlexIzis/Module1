package com.example.module1

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val newImage = it.data?.extras?.get("data") as Bitmap
                val image: ImageView = findViewById(R.id.imageEdit)
                image.setImageBitmap(newImage)
            }
        }

        fun doPhoto() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            getResult.launch(cameraIntent)
        }

        fun deletePhoto() {
            val image: ImageView = findViewById(R.id.imageEdit)
            image.setImageResource(R.drawable.ic_photo_camera)
        }

        fun showDialog() {
            val builder = AlertDialog.Builder(this)
            builder.setItems(
                R.array.values,
                DialogInterface.OnClickListener { _, i ->
                    when (i) {
                        0 -> { Toast.makeText(this, "Ничего не происходит", Toast.LENGTH_SHORT).show() }
                        1 -> { doPhoto() }
                        2 -> { deletePhoto() }
                    }
                }
            )
            val dialog = builder.create()
            dialog.show()
        }

        val changeImage: CardView = findViewById(R.id.cardView)
        changeImage.setOnClickListener {
            showDialog()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
