package com.okuuyghur.tamaklistesi

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import com.okuuyghur.tamaklistesi.Model.Yemek_Model
import com.okuuyghur.tamaklistesi.databinding.FragmentTarifBinding
import java.io.ByteArrayOutputStream
import java.lang.Exception

class TarifFragment : Fragment() {
    private var _binding : FragmentTarifBinding? = null
    private val binding get() = _binding
    private var secilengorsel : Uri? = null
    private var secilenBitmap : Bitmap? = null
   private lateinit var yemekler : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTarifBinding.inflate(layoutInflater,container,false)
        binding?.kaydet?.setOnClickListener {
        kunupka(it)
        }


        val yemek = YemekHelper(requireContext())
        Log.i("yemek",yemek.Read_Yemek().size.toString())


        binding?.imageView?.setOnClickListener {
            resim(it)
        }

        arguments?.let {


        }
        return binding?.root
    }


    fun kunupka(view: View){
        val name = binding?.yemekIsim?.text.toString()
        val tarif = binding?.yemekTarif?.text.toString()


        // bitmapi kimmatka aylandurux
        val outputStream = ByteArrayOutputStream()
        secilenBitmap?.compress(Bitmap.CompressFormat.PNG,50,outputStream)
        val byteArray = outputStream.toByteArray()

        // databasege veri koymak
        val yemekler = Yemek_Model(null,name,tarif,byteArray.toString() )
        val helper = YemekHelper(requireContext())
        helper.yemekler(yemekler)
    }


    //Galeri,ye izin almak
    fun  resim (view: View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (PermissionChecker.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PermissionChecker.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }else{
                val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)

            }

        }
    }
    // aldigimiz izni kontrol etmek
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1 ){
            // izin aldik
            if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)

            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    // izni kontrol ettikten sonra galeriye gidip resmi getirmek
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if ( requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){

            secilengorsel = data.data

            try {
                context?.let {
                    if (secilengorsel != null){
                        if (Build.VERSION.SDK_INT >= 28 ){
                            val source = ImageDecoder.createSource(it.contentResolver,secilengorsel!!)
                            // resmi Bitmap yapmak
                            secilenBitmap = ImageDecoder.decodeBitmap(source)
                            binding?.imageView?.setImageBitmap(secilenBitmap)

                        }else {
                            secilenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver,secilengorsel)
                            binding?.imageView?.setImageBitmap(secilenBitmap)
                        }
                    }
                }

            }catch (e : Exception){
                e.printStackTrace()
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}