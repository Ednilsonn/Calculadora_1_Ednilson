package com.example.calculadora15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.calculadora15.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private  lateinit var expressao: TextView
    private  lateinit var resultado1: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        expressao=binding.expressao
        resultado1=binding.resultado1


        binding.numeroZero.setOnClickListener{AcrescentarUmaExpressao("0",true)}
        binding.numeroUm.setOnClickListener{AcrescentarUmaExpressao("1",true)}
        binding.numeroDois.setOnClickListener{AcrescentarUmaExpressao("2",true)}
        binding.numeroTres.setOnClickListener{AcrescentarUmaExpressao("3",true)}
        binding.numeroQuatro.setOnClickListener{AcrescentarUmaExpressao("4",true)}
        binding.numeroCinco.setOnClickListener{AcrescentarUmaExpressao("5",true)}
        binding.numeroSeis.setOnClickListener{AcrescentarUmaExpressao("6",true)}
        binding.numeroSete.setOnClickListener{AcrescentarUmaExpressao("7",true)}
        binding.numeroOito.setOnClickListener{AcrescentarUmaExpressao("8",true)}
        binding.numeroNove.setOnClickListener{AcrescentarUmaExpressao("9",true)}
        binding.ponto.setOnClickListener{AcrescentarUmaExpressao(".",true)}


        //Operadores

        binding.soma.setOnClickListener{AcrescentarUmaExpressao("+",false)}
        binding.subtracao.setOnClickListener{AcrescentarUmaExpressao("-",false)}
        binding.multiplicacao.setOnClickListener{AcrescentarUmaExpressao("*",false)}
        binding.divisao.setOnClickListener{AcrescentarUmaExpressao("/",false)}


        binding.limpar.setOnClickListener{
            expressao.text=""
            resultado1.text=""
        }

        binding.backspace.setOnClickListener{
            val string= expressao.text.toString()
            if (string.isNotBlank()){
                expressao.text= string.substring(0,string.length-1)
            }
            resultado1.text=""
        }
        binding.igual.setOnClickListener {
            try {
                val expressao= ExpressionBuilder(expressao.text.toString()).build()
                val resultado=expressao.evaluate()
                val longResult =resultado.toLong()

                if (resultado==longResult.toDouble())
                    resultado1.text=longResult.toString()
                else
                    resultado1.text=resultado.toString()
            }catch (e: Exception){

        }
    }



    }
    fun AcrescentarUmaExpressao(string: String, limpar_dados: Boolean) {
        if (resultado1.text.isNotEmpty()) {
            expressao.text = ""
        }

        if (limpar_dados) {
            if (string == ".") {
                // Verifique se o último caractere na expressão atual é um ponto (.)
                // Se for, não adicione um novo ponto.
                val currentExpression = expressao.text.toString()
                if (currentExpression.isNotEmpty() && currentExpression.last() == '.') {
                    return
                }
            }
            resultado1.text = ""
            expressao.append(string)
        } else {
            // Verifique se o último caractere na expressão atual é um operador ou ponto (.)
            // Se for, substitua o último caractere pela operação desejada.
            val currentExpression = expressao.text.toString()
            if (currentExpression.isNotEmpty() && (currentExpression.last() == '+' ||
                        currentExpression.last() == '-' ||
                        currentExpression.last() == '*' ||
                        currentExpression.last() == '/' ||
                        currentExpression.last() == '.'
                        )
            ) {
                expressao.text = currentExpression.substring(0, currentExpression.length - 1)
            }
            expressao.append(string)
            resultado1.text = ""
        }
    }

}