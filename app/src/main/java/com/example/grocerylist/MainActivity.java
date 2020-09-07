package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> groceryItems;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groceryItems = new ArrayList<String>();
        adapter = new CustomAdapter(this, groceryItems);
        //android.R.layout.simple_list_item_1
        ListView listView = (ListView) findViewById(R.id.groceryList);
        //addFooter(listView);
        listView.setOnItemClickListener(deleteItem);
        listView.setAdapter(adapter);
    }

    class CustomAdapter extends ArrayAdapter<String> {
        //Context context;
        ArrayList<String> items;

        public CustomAdapter (Context context, ArrayList<String> i) {
            super(context, R.layout.listview_item, R.id.listItem, i);
            //context = c;
            items = i;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listviewItem = layoutInflater.inflate(R.layout.listview_item, parent, false);
            TextView textView = (TextView) listviewItem.findViewById(R.id.listItem);
            textView.setText(items.get(position));
            ImageView imageView = (ImageView) listviewItem.findViewById(R.id.deleteItem);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    groceryItems.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            return listviewItem;
        }
    }

    private AdapterView.OnItemClickListener deleteItem = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            groceryItems.add("add");
            adapter.notifyDataSetChanged();
        }
    };

    public void addItem(View view) {
        EditText editText = (EditText) findViewById(R.id.addItemView);
        String item = editText.getText().toString();
        if (!(item.isEmpty())) {
            groceryItems.add(item);
            editText.setText(null);
            //InputMethodManager imm = (InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            adapter.notifyDataSetChanged();
        }
    }

    //public void addFooter(ListView listView) {
        //LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View listviewFooter = layoutInflater.inflate(R.layout.listview_footer, listView, false);
        //final EditText editText = (EditText) listviewFooter.findViewById(R.id.itemNameView);
        //final String item = editText.getText().toString();
        //ImageView imageView = (ImageView) listviewFooter.findViewById(R.id.addItemView);
        //imageView.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //if (!(item.isEmpty())) {
                    //groceryItems.add(item);
                    //editText.setText(null);
                    //InputMethodManager imm = (InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    //adapter.notifyDataSetChanged();
                //}
            //}
        //});
        //listView.addFooterView(listviewFooter);
    //}

    //public void createNew(View view) {
    //    Intent intent = new Intent(this, addItem.class);
    //    startActivity(intent);
    //}
}