package uz.shokirov.iqtisod

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.github.florent37.runtimepermission.kotlin.askPermission
import uz.shokirov.iqtisod.databinding.ActivityMainBinding
import uz.shokirov.iqtisod.databinding.ResultDialogBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        myMethod()

        codeScanner = CodeScanner(this, binding.scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                checkCode(it.text)
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                        this, "Camera initialization error: ${it.message}",
                        Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    fun myMethod() {
        askPermission(Manifest.permission.CAMERA) {
            //all permissions already granted or just granted

        }.onDeclined { e ->
            if (e.hasDenied()) {

                AlertDialog.Builder(this)
                        .setMessage("Please accept our permissions")
                        .setPositiveButton("yes") { dialog, which ->
                            e.askAgain();
                        } //ask again
                        .setNegativeButton("no") { dialog, which ->
                            dialog.dismiss();
                        }
                        .show();
            }

            if (e.hasForeverDenied()) {
                //the list of forever denied permissions, user has check 'never ask again'

                // you need to open setting manually if you really need it
                e.goToSettings();
            }
        }

    }


    @SuppressLint("SetTextI18n")
    private fun checkCode(code: String) {
        var list: ArrayList<String> = ArrayList()
        if (code.length == 13) {
            for (c in code) {
                list.add(c.toString())
            }
            var first = list[1].toInt() + list[3].toInt() + list[5].toInt() + list[7].toInt() + list[9].toInt() + list[11].toInt()
            var second = first * 3
            var third = list[0].toInt() + list[2].toInt() + list[4].toInt() + list[6].toInt() + list[8].toInt() + list[10].toInt()
            var fourth = second + third
            var listFour = ArrayList<String>()
            for (c in fourth.toString()) {
                listFour.add(c.toString())
            }
            var fiveth = 10 - listFour[listFour.lastIndex].toInt()
            Log.d("LOG TEKSHIRISH", "checkCode: 1-$first   2-$second   3-$third   4-$fourth  oxirgi-${listFour[listFour.lastIndex].toInt()}  5-$fiveth  12-${list[12]}")
            if (list[12].toString() == fiveth.toString()) {
                val alertDialog = AlertDialog.Builder(this)
                val dialog = alertDialog.create()
                val dialogView = ResultDialogBinding.inflate(layoutInflater)
                dialog.setView(dialogView.root)
                dialogView.animFalse.visibility = View.INVISIBLE
                dialogView.animTrue.visibility = View.VISIBLE
                dialogView.tvResult.text = "Mahsulot haqiqiy\nShtrix kod: $code"
                dialogView.imgCancel.setOnClickListener {
                    dialog.cancel()
                    codeScanner.startPreview()
                }
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.show()
            } else {
                val alertDialog = AlertDialog.Builder(this)
                val dialog = alertDialog.create()
                val dialogView = ResultDialogBinding.inflate(layoutInflater)
                dialog.setView(dialogView.root)
                dialogView.animFalse.visibility = View.VISIBLE
                dialogView.animTrue.visibility = View.INVISIBLE
                dialogView.tvResult.text = "Mahsulot Soxta\nShtrix kod: $code"
                dialogView.imgCancel.setOnClickListener {
                    dialog.cancel()
                    codeScanner.startPreview()
                }
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.show()
            }
        }
    }
}