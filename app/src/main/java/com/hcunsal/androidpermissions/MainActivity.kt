package com.hcunsal.androidpermissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hcunsal.androidpermissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityLauncher : ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedImage : Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerLauncher()

    }

    fun selectClick(view : View){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
           if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
               Snackbar.make(view, "You need to give permission for the gallery.", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission"){
                   // Permission give
                   permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
               }.show()
           }else{
                  // Permission give
                  permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
           }


        }else{
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // Start activity
            activityLauncher.launch(intentToGallery)

        }

    }

    private fun registerLauncher() {

        activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intentFromGallery = result.data
                    if (intentFromGallery != null) {
                        selectedImage = intentFromGallery.data

                        selectedImage?.let {
                            binding.imageView.setImageURI(selectedImage)
                        }
                    }
                }

            }


        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ result ->
            if(result){
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityLauncher.launch(intentToGallery)
            }else{
                Toast.makeText(this, "You need to give permission for the gallery.", Toast.LENGTH_LONG).show()
            }


        }


    }
}