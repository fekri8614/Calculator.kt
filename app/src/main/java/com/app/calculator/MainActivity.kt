package com.app.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.app.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.reflect.Executable
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()

    }

    private fun onNumberClicked() {

        binding.button0.setOnClickListener {
            if (binding.textExpression.text.isNotEmpty()) {
                appendText("0")
            }

        }

        binding.button1.setOnClickListener {
            appendText("1")
        }

        binding.button2.setOnClickListener {
            appendText("2")
        }

        binding.button3.setOnClickListener {
            appendText("3")
        }

        binding.button4.setOnClickListener {
            appendText("4")
        }

        binding.button5.setOnClickListener {
            appendText("5")
        }

        binding.button6.setOnClickListener {
            appendText("6")
        }

        binding.button7.setOnClickListener {
            appendText("7")
        }

        binding.button8.setOnClickListener {
            appendText("8")
        }

        binding.button9.setOnClickListener {
            appendText("9")
        }

        binding.buttonDot.setOnClickListener {

            if (binding.textExpression.text.isEmpty() || binding.textAnswer.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.textExpression.text.contains(".")) {

                appendText(".")

            }
        }

    }

    private fun onOperatorClicked() {

        binding.buttonAdd.setOnClickListener {

            if (binding.textExpression.text.isNotEmpty()) {
                val myChar = binding.textExpression.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("+")
                }
            }

        }

        binding.buttonSubtract.setOnClickListener {
            if (binding.textExpression.text.isNotEmpty()) {
                val myChar = binding.textExpression.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("-")
                }
            }
        }

        binding.buttonMultiply.setOnClickListener {
            if (binding.textExpression.text.isNotEmpty()) {
                val myChar = binding.textExpression.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("*")
                }
            }
        }

        binding.buttonDivision.setOnClickListener {
            if (binding.textExpression.text.isNotEmpty()) {
                val myChar = binding.textExpression.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("/")
                }
            }
        }

        binding.buttonOpenParenthesis.setOnClickListener {
            appendText("(")
        }

        binding.buttonCloseParenthesis.setOnClickListener {
            appendText(")")
        }

        binding.buttonAc.setOnClickListener {
            binding.textExpression.text = ""
            binding.textAnswer.text = ""
        }

        binding.buttonEqual.setOnClickListener {

            try {

                val expression = ExpressionBuilder(binding.textExpression.text.toString()).build()

                val answer = expression.evaluate()

                val lonResult = answer.toLong()

                //135.0 = 135

                if (answer == lonResult.toDouble()) {
                    binding.textAnswer.text = lonResult.toString()
                } else {
                    binding.textAnswer.text = answer.toString()
                }

            } catch (e: Exception) {
                binding.textExpression.text = ""
                binding.textAnswer.text = ""
                Toast.makeText(this, "Give numbers for calculate", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonDelete.setOnClickListener {
            val oldText = binding.textExpression.text.toString()

            if (oldText.isNotEmpty()) {

                binding.textExpression.text = oldText.substring(0, oldText.length - 1)

            }

        }

    }

    private fun appendText(newText: String) {

        if (binding.textAnswer.text.isNotEmpty()) {
            binding.textExpression.text = ""
        }

        binding.textAnswer.text = ""
        binding.textExpression.append(newText)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTextExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTextExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTextExpression.scrollTo(binding.textExpression.width, 0)
            }
        })

    }

}