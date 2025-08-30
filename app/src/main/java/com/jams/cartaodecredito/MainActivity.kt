package com.jams.cartaodecredito

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txtNome = findViewById<EditText>(R.id.txtNome)
        val txtNumeros = findViewById<EditText>(R.id.txtNumeros)
        val txtData = findViewById<EditText>(R.id.txtData)
        val txtCode = findViewById<EditText>(R.id.txtCode)

        txtNumeros.formatAsCardNumber()

        txtNumeros.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.post {
                    val editText = view as EditText
                    editText.setSelection(editText.text.length)
                }
            }
        }

        txtData.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.post {
                    val editText = view as EditText
                    editText.setSelection(editText.text.length)
                }
            }
        }

        txtCode.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.post {
                    val editText = view as EditText
                    editText.setSelection(editText.text.length)
                }
            }
        }

    }
}

fun EditText.formatAsCardNumber(): Unit {
    val textWatcher = object : TextWatcher {
        private var isFormatting = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if (isFormatting) {
                return
            }

            isFormatting = true

            // 1. Pega o texto atual e remove todos os espaços
            val digitsOnly = s.toString().replace(" ", "")

            // 2. Usa um StringBuilder para reconstruir a string com os espaços
            val formatted = StringBuilder()
            var count = 0
            for (char in digitsOnly) {
                // Adiciona um espaço a cada 4 caracteres
                if (count > 0 && count % 4 == 0) {
                    formatted.append(" ")
                }
                formatted.append(char)
                count++
            }

            // 3. Atualiza o texto do EditText com a string formatada
            // this@formatAsCardNumber se refere ao EditText em que a função foi chamada
            this@formatAsCardNumber.setText(formatted.toString())

            // 4. Move o cursor para o final do texto. Isso é importante para a UX.
            try {
                this@formatAsCardNumber.setSelection(formatted.length)
            } catch (e: Exception) {
                // Ignora exceções que podem ocorrer se o texto for alterado muito rapidamente
            }


            isFormatting = false
        }
    }

    // Adiciona o TextWatcher que criamos ao EditText
    this.addTextChangedListener(textWatcher)
}