package com.cmsc434.f4t.food4thought;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

public class MyCouponActivity extends MyCouponListActivity {
		String passedBusiness;
		String passedTitle;
		String passedDescription;
		String passedCost;
        private ImageView passedViewImage = null;
		private TextView passedViewBusi = null;
		private TextView passedViewTitle = null;
		private TextView passedViewDesc = null;

		Button use;
        AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
				
        use = (Button) findViewById(R.id.button);

        Bundle extras = getIntent().getExtras();
        passedBusiness = extras.getString(business);
        passedTitle = extras.getString(title);
        passedDescription = extras.getString(description);
        passedCost = extras.getString(cost);

        passedViewImage = (ImageView) findViewById(R.id.image);
        passedViewBusi = (TextView) findViewById(R.id.business);
        passedViewTitle = (TextView) findViewById(R.id.title);
        passedViewDesc = (TextView) findViewById(R.id.description);

        if (passedBusiness.equals("McDonalds")){
            passedViewImage.setImageResource(R.drawable.mcdonalds_logo);
        } else if (passedBusiness.equals("Taco Bell")){
            passedViewImage.setImageResource(R.drawable.taco_bell_logo);
        } else if (passedBusiness.equals("Chipotle")){
            passedViewImage.setImageResource(R.drawable.chipotle_logo);
        }
        passedViewBusi.setText(passedBusiness);
        passedViewTitle.setText(passedTitle);
        passedViewDesc.setText(passedDescription);

        /*use.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder popup = new AlertDialog.Builder(
                        MyCouponActivity.this);
                popup.setTitle("Use this Coupon?");

                // PositiveButton, deletes current position.
                popup.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                int deletePos = MyCouponListActivity.deletePos;
                                MyCouponListActivity.dataAdapter.remove(dataAdapter.getItem(deletePos));
                                MyCouponListActivity.dataAdapter.notifyDataSetChanged();
                                finish();
                            }
                        });

                // NegativeButton, closes the pop-up.
                popup.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                dialog.dismiss();
                            }
                        });

                dialog = popup.create();
                dialog.show();
            }
        });*/
    }
}
