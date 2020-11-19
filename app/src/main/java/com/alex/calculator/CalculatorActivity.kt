package com.alex.calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView


class CalculatorActivity : CalculationActions() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val btn0 = findViewById<TextView>(R.id.btn_0)
        val btn1 = findViewById<TextView>(R.id.btn_1)
        val btn2 = findViewById<TextView>(R.id.btn_2)
        val btn3 = findViewById<TextView>(R.id.btn_3)
        val btn4 = findViewById<TextView>(R.id.btn_4)
        val btn5 = findViewById<TextView>(R.id.btn_5)
        val btn6 = findViewById<TextView>(R.id.btn_6)
        val btn7 = findViewById<TextView>(R.id.btn_7)
        val btn8 = findViewById<TextView>(R.id.btn_8)
        val btn9 = findViewById<TextView>(R.id.btn_9)

        val btnDot = findViewById<TextView>(R.id.btn_dot)
        val btnClear = findViewById<TextView>(R.id.btn_clear)
        val btnBack = findViewById<TextView>(R.id.btn_back)

        btnClear.setOnClickListener {
            val math_operation = findViewById<TextView>(R.id.math_operation)
            math_operation.text = ""
            val math_result = findViewById<TextView>(R.id.math_result)
            math_result.text = ""
        }
        btnBack.setOnClickListener {
            val math_operation = findViewById<TextView>(R.id.math_operation)
            val str = math_operation.text.toString()
            if(str.isNotEmpty())
                math_operation.text = str.substring(0, str.length - 1)
            clearResult()
        }


        val spinner: Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
                this,
                R.array.Languages,
                android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }

        val spinner2: Spinner = findViewById(R.id.spinner2)

        ArrayAdapter.createFromResource(
                this,
                R.array.Languages,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("Unit: ", "notSelected!!")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val math_operation:TextView? = findViewById(R.id.math_operation)
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
                val math_operation:TextView? = findViewById(R.id.math_operation)
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
    }
}