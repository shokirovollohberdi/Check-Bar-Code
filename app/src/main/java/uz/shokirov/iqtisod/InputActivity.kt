package uz.shokirov.iqtisod

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import uz.shokirov.iqtisod.databinding.ActivityInputBinding
import uz.shokirov.iqtisod.databinding.ResultDialogBinding

class InputActivity : AppCompatActivity() {
    lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValue()

        binding.cardCheck.setOnClickListener {
            setValueAndGetValueNumberPicker()
        }
    }

    private fun setValueAndGetValueNumberPicker() {
        var value0 = binding.index0.value.toString()
        var value1 = binding.index1.value.toString()
        var value2 = binding.index2.value.toString()
        var value3 = binding.index3.value.toString()
        var value4 = binding.index4.value.toString()
        var value5 = binding.index5.value.toString()
        var value6 = binding.index6.value.toString()
        var value7 = binding.index7.value.toString()
        var value8 = binding.index8.value.toString()
        var value9 = binding.index9.value.toString()
        var value10 = binding.index10.value.toString()
        var value11 = binding.index11.value.toString()
        var value12 = binding.index12.value.toString()
        var list = ArrayList<String>()
        list.add(value0)
        list.add(value1)
        list.add(value2)
        list.add(value3)
        list.add(value4)
        list.add(value5)
        list.add(value6)
        list.add(value7)
        list.add(value8)
        list.add(value9)
        list.add(value10)
        list.add(value11)
        list.add(value12)
        if (list.size == 13) {
            checkCode(list)
        } else {
            Toast.makeText(this, "Ma'lumot to'liq emas ${list.size}", Toast.LENGTH_SHORT).show()
        }


    }


    private fun setValue() {
        binding.index0.minValue = 0
        binding.index1.minValue = 0
        binding.index2.minValue = 0
        binding.index3.minValue = 0
        binding.index4.minValue = 0
        binding.index5.minValue = 0
        binding.index6.minValue = 0
        binding.index7.minValue = 0
        binding.index8.minValue = 0
        binding.index9.minValue = 0
        binding.index10.minValue = 0
        binding.index11.minValue = 0
        binding.index12.minValue = 0
        binding.index0.maxValue = 9
        binding.index1.maxValue = 9
        binding.index2.maxValue = 9
        binding.index3.maxValue = 9
        binding.index4.maxValue = 9
        binding.index5.maxValue = 9
        binding.index6.maxValue = 9
        binding.index7.maxValue = 9
        binding.index8.maxValue = 9
        binding.index9.maxValue = 9
        binding.index10.maxValue = 9
        binding.index11.maxValue = 9
        binding.index12.maxValue = 9
    }

    @SuppressLint("SetTextI18n")
    private fun checkCode(list: ArrayList<String>) {
        var first =
                list[1].toInt() + list[3].toInt() + list[5].toInt() + list[7].toInt() + list[9].toInt() + list[11].toInt()
        var second = first * 3
        var third =
                list[0].toInt() + list[2].toInt() + list[4].toInt() + list[6].toInt() + list[8].toInt() + list[10].toInt()
        var fourth = second + third
        var listFour = ArrayList<String>()
        for (c in fourth.toString()) {
            listFour.add(c.toString())
        }
        var fiveth = 10 - listFour[listFour.lastIndex].toInt()
        Log.d(
                "LOG TEKSHIRISH",
                "checkCode: 1-$first   2-$second   3-$third   4-$fourth  oxirgi-${listFour[listFour.lastIndex].toInt()}  5-$fiveth  12-${list[12]}"
        )
        if (list[12].toString() == fiveth.toString()) {
            val alertDialog = AlertDialog.Builder(this)
            val dialog = alertDialog.create()
            dialog.setTitle("Title Custom dialog")
            val dialogView = ResultDialogBinding.inflate(layoutInflater)
            dialog.setView(dialogView.root)
            dialogView.animFalse.visibility = View.INVISIBLE
            dialogView.animTrue.visibility = View.VISIBLE
            dialogView.tvResult.text = "Mahsulot haqiqiy"
            dialogView.imgCancel.setOnClickListener {
                dialog.cancel()
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
            dialogView.tvResult.text = "Mahsulot Soxta"
            dialogView.imgCancel.setOnClickListener {
                dialog.cancel()
            }
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
        }
    }

}