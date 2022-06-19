package com.IvanManzaba.app3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun requestAPI(view:View) {

        val txtheader = findViewById<EditText>(R.id.txtHeader)
        val txtdatos = findViewById<TextView>(R.id.txvDatos)

        if (txtheader.text.isNotEmpty()) {
            val api = Volley.newRequestQueue(this)
            val url = "https://postman-echo.com/headers"
            try {
                val peticion =
                    object : JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        Response.Listener { response ->

                            txtdatos.text = response.toString(8)

                        },
                        Response.ErrorListener { txtdatos.text = "error" }) {
                        @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers = HashMap<String, String>()
                            headers.put("Header-ingresado", txtheader.text.toString())
                            return headers
                        }
                    }
                api.add(peticion)
            } catch (e: Exception) {
                println(e.message)
            }
        }

    }
}