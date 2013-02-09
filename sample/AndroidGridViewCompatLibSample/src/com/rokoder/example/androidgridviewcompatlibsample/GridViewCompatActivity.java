
package com.rokoder.example.androidgridviewcompatlibsample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import com.rokoder.android.lib.support.v4.widget.GridViewCompat;

public class GridViewCompatActivity extends Activity {

    GridViewCompat gridView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_compat);

        gridView = (GridViewCompat) findViewById(R.id.gridView1);

        // NOTE: We are using setChoiceMode, as I said, its a drop-in replacement
        gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        gridView.setAdapter(new ImageAdapter(getApplicationContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> view, View arg1, int pos, long id) {
                // We need to invalidate all views on 4.x versions
                GridViewCompat gridView = (GridViewCompat) view;
                gridView.invalidateViews();
            }

        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SparseBooleanArray checkArray;
                checkArray = gridView.getCheckedItemPositions();

                String selectedPos = "Selected positions: ";
                int count = checkArray.size();
                for (int i = 0; i < count; i++) {
                    if (checkArray.valueAt(i))
                        selectedPos += checkArray.keyAt(i) + ",";
                }

                Intent intent = new Intent();
                intent.putExtra("result", selectedPos);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new grid view item for each item referenced by the Adapter
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            CheckBox checkBox;
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.grid_view_item, parent, false);
            }
            imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);

            GridViewCompat gvc = (GridViewCompat) parent;
            if (gvc.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
                SparseBooleanArray checkArray;
                checkArray = gvc.getCheckedItemPositions();

                checkBox.setChecked(false);
                if (checkArray != null) {
                    if (checkArray.get(position)) {
                        checkBox.setChecked(true);
                    }
                }

            }
            imageView.setImageResource(mThumbIds[position]);
            return convertView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7
        };
    }
}
