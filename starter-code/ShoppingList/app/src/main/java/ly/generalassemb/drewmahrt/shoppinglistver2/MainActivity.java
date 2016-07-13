package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;
import ly.generalassemb.drewmahrt.shoppinglistver2.setup.GroceryHelper;

public class MainActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();



        Cursor cursor = GroceryHelper.getInstance(MainActivity.this).getNames();

        CursorAdapter adapter = new CursorAdapter(MainActivity.this, cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context)
                        .inflate(android.R.layout.simple_list_item_1,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setText(cursor.getString(cursor.getColumnIndex(GroceryHelper.COL_NAME)));
            }
        };

        mListView = (ListView)findViewById(R.id.shopping_list_view);
        mListView.setAdapter(adapter);

        cursor.close();

    }
}
