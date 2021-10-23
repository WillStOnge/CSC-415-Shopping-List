package com.csc415.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.TextView
import android.widget.EditText
import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children

const val EXTRA_ITEM = "com.csc415.twoactivities.extra.ITEM"

class SecondActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)

		for (view in findViewById<ViewGroup>(R.id.items_choose).children)
			if (view is Button)
				view.setOnClickListener { returnItem(it as Button) }
	}

	private fun returnItem(view: Button)
	{
		val replyIntent = Intent()
		replyIntent.putExtra(EXTRA_ITEM, view.text)
		setResult(RESULT_OK, replyIntent)
		finish()
	}
}