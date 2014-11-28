package skyxxzc.wittread;

import android.os.Bundle;
import java.io.*;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	ResProvider res;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		int pos = intent.getIntExtra("pos", 0);
		try {
			res = new ResProvider(this,pos);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, res.contents);
			setListAdapter(adapter);
		} catch (IOException e){
			Toast.makeText(this, "Failed to open text file", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
		if(!ResProvider.items[res.indexMap.get(position)].expandable)
			return;
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("pos", res.indexMap.get(position)+1);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_settings:
			break;
		case R.id.action_about:
			new AlertDialog.Builder(this).setMessage("Zhang Cheng 2014")
				.create().show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
