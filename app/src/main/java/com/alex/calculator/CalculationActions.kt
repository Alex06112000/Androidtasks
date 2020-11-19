package com.alex.calculator

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class CalculationActions: AppCompatActivity() {

    fun clearResult(){
        val math_result = findViewById<TextView>(R.id.math_result)
        math_result.text = ""
    }
    fun setTextFields(unit1:String, unit2:String, str: String) {
        val math_operation = findViewById<TextView>(R.id.math_operation)
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
        val math_operation = findViewById<TextView>(R.id.math_result)
        math_operation?.append(result.toString())
    }

}