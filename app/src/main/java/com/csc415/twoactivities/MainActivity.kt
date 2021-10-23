package com.csc415.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.widget.TextView
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children

const val ITEM_REQUEST = 1

class MainActivity : AppCompatActivity()
{
	private val textViews = ArrayList<TextView>()
	private var items = ArrayList<String>()

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		for (view in findViewById<ViewGroup>(R.id.items_layout).children)
			if (view is TextView && view !is Button)
				textViews.add(view)

		if (savedInstanceState != null)
		{
			items = savedInstanceState.getStringArrayList("ItemsList")!!
			rebuildItems()
		}

		findViewById<Button>(R.id.add_item).setOnClickListener {
			startActivityForResult(Intent(this, SecondActivity::class.java), ITEM_REQUEST)
		}
	}

	override fun onSaveInstanceState(outState: Bundle)
	{
		super.onSaveInstanceState(outState)
		outState.putStringArrayList("ItemsList", items)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
	{
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == ITEM_REQUEST && resultCode == RESULT_OK && data != null)
		{
			items.add(data.getStringExtra(EXTRA_ITEM)!!)
			rebuildItems()
		}
	}

	private fun rebuildItems()
	{
		var i = 0
		Log.d("test", textViews.size.toString())

		for (item in items)
		{
			Log.d("test", i.toString())
			if (i < textViews.size) textViews[i++].text = item
			else Toast.makeText(applicationContext, "Cannot add any more items to the list.", Toast.LENGTH_SHORT).show()
		}
	}
}