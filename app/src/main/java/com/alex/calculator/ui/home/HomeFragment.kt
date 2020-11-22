package com.alex.calculator.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alex.calculator.R

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_home, container, false)

    val btn0 = root.findViewById<TextView>(R.id.btn_0)
    val btn1 = root.findViewById<TextView>(R.id.btn_1)
    val btn2 = root.findViewById<TextView>(R.id.btn_2)
    val btn3 = root.findViewById<TextView>(R.id.btn_3)
    val btn4 = root.findViewById<TextView>(R.id.btn_4)
    val btn5 = root.findViewById<TextView>(R.id.btn_5)
    val btn6 = root.findViewById<TextView>(R.id.btn_6)
    val btn7 = root.findViewById<TextView>(R.id.btn_7)
    val btn8 = root.findViewById<TextView>(R.id.btn_8)
    val btn9 = root.findViewById<TextView>(R.id.btn_9)

    val btnDot = root.findViewById<TextView>(R.id.btn_dot)
    val btnClear = root.findViewById<TextView>(R.id.btn_clear)
    val btnBack = root.findViewById<TextView>(R.id.btn_back)

    fun getTextFieldValue(value:String):Double{
      var valueOfUnit:Double = 0.0
      val unit:String = value.substringAfter('(').substringBefore(')')
      if (unit == "mm") {
        valueOfUnit = 1000.0
      } else if(unit == "cm"){
        valueOfUnit = 100.0
      } else if(unit == "m"){
        valueOfUnit = 1.0
      } else if(unit == "km"){
        valueOfUnit = 0.001
      } else if(unit == "in"){
        valueOfUnit = 39.3700787402
      } else if(unit == "ft"){
        valueOfUnit = 3.280839895
      } else if(unit == "yd"){
        valueOfUnit =  1.0936132983
      } else if(unit == "mi"){
        valueOfUnit = 0.0006213712
      } else if(unit == "NM"){
        valueOfUnit = 0.0005399568
      }

      return valueOfUnit
    }
    fun getTextFieldsResult(value1:String, value2:String,  digit2: String) {
      val unit1:Double = getTextFieldValue(value1)
      val unit2:Double = getTextFieldValue(value2)
      val digit = digit2.toDouble();
      val result:Double = (digit / unit2) * unit1
      val math_operation = root.findViewById<TextView>(R.id.math_result)
      math_operation?.append(result.toString())
    }
    fun clearResult(){
      val math_result = root.findViewById<TextView>(R.id.math_result)
      math_result.text = ""
    }
    fun setTextFields(unit1:String, unit2:String, str: String) {
      val math_operation = root.findViewById<TextView>(R.id.math_operation)
      if(str == ".") {
        if (!math_operation.text.contains(".", ignoreCase = true)) {
          math_operation.append(str)
          clearResult()
          getTextFieldsResult(unit1, unit2, math_operation.text.toString())
        }
      }else{
        math_operation.append(str)
        clearResult()
        getTextFieldsResult(unit1, unit2, math_operation.text.toString())
      }
    }

    btnClear.setOnClickListener {
      val math_operation = root.findViewById<TextView>(R.id.math_operation)
      math_operation.text = ""
      val math_result = root.findViewById<TextView>(R.id.math_result)
      math_result.text = ""
    }

    btnBack.setOnClickListener {
      val math_operation = root.findViewById<TextView>(R.id.math_operation)
      val str = math_operation.text.toString()
      if(str.isNotEmpty())
        math_operation.text = str.substring(0, str.length - 1)
      clearResult()
    }
    val spinner: Spinner = root.findViewById(R.id.spinner)

    activity?.let {
      ArrayAdapter.createFromResource(
              it,
              R.array.Languages,
              android.R.layout.simple_spinner_item
      ).also { adapter ->

        adapter.setDropDownViewResource(R.layout.spinner_item)

        spinner.adapter = adapter
      }
    }

    val spinner2: Spinner = root.findViewById(R.id.spinner2)

    activity?.let {
      ArrayAdapter.createFromResource(
              it,
              R.array.Languages,
              android.R.layout.simple_spinner_item
      ).also { adapter ->
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spinner2.adapter = adapter
      }
    }

    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("Unit: ", "notSelected!!")
      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val math_operation:TextView? = root.findViewById(R.id.math_operation)
        if(math_operation?.text.toString()!="") {
          clearResult()
          getTextFieldsResult(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), math_operation?.text.toString())
        }
      }

    }

    spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("Unit: ", "notSelected!!")
      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val math_operation:TextView? = root.findViewById(R.id.math_operation)
        if(math_operation?.text.toString()!="") {
          clearResult()
          getTextFieldsResult(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), math_operation?.text.toString())
        }
      }

    }

    btn0.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "0") }
    btn1.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "1") }
    btn2.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "2") }
    btn3.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "3") }
    btn4.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "4") }
    btn5.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "5") }
    btn6.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "6") }
    btn7.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "7") }
    btn8.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "8") }
    btn9.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), "9") }
    btnDot.setOnClickListener { setTextFields(spinner.selectedItem.toString(), spinner2.selectedItem.toString(), ".") }
    homeViewModel.text.observe(viewLifecycleOwner, Observer {

    })
    return root
  }

}