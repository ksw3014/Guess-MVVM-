package com.ian.guess

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ian.guess.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var secret = 0
    var times = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)
        viewModel.message.observe(this, Observer{
            binding.message.setText(it)
        })
        viewModel.times.observe(this, Observer{
            binding.counter.setText(it.toString())
        })
        binding.button.setOnClickListener {
            viewModel.guess(binding.number.text.toString().toInt())
        }
        /*secret = Random.nextInt(10) + 1
        binding.message.setText("")
        binding.button.setOnClickListener {
            val num = binding.number.text.toString().toInt()
            val text = when(num - secret){
                0 -> "答對了"
                in 1..10 -> "小一點"
                else -> "大一點"
            }
            times++
            binding.counter.setText(times.toString())
            binding.message.setText(text)
        }*/
    }
}