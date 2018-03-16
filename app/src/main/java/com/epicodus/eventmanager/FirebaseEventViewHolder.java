package com.epicodus.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by alenagolovina on 3/16/18.
 */

public class FirebaseEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    //public ImageView mEventImageView;

    public FirebaseEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindEvent(Event event) {
        ImageView eventImageView = (ImageView) mView.findViewById(R.id.eventImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.eventNameTextView);
        TextView typeTextView = (TextView) mView.findViewById(R.id.typeTextView);
        TextView dateTextView = (TextView) mView.findViewById(R.id.dateTextView);

//        if (!event.getImageUrl().contains("http")) {
//            try {
//                Bitmap imageBitmap = decodeFromFirebaseBase64(restaurant.getImageUrl());
//                mRestaurantImageView.setImageBitmap(imageBitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//
//            Picasso.with(mContext)
//                    .load(restaurant.getImageUrl())
//                    .resize(MAX_WIDTH, MAX_HEIGHT)
//                    .centerCrop()
//                    .into(mRestaurantImageView);

            nameTextView.setText(event.getName());
            typeTextView.setText(event.getType());
            dateTextView.setText(event.getDate());
        }

//        mNameTextView.setText(restaurant.getName());
//        mCategoryTextView.setText(restaurant.getCategories().get(0));
//        mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
//    }

//    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
//        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
//    }
    //////////Use this for View Property Animation
//    @Override
//    public void onItemSelected() {
//        itemView.animate()
//                .alpha(0.7f)
//                .scaleX(0.9f)
//                .scaleY(0.9f)
//                .setDuration(500);
//    }
//
//    @Override
//    public void onItemClear() {
//        itemView.animate()
//                .alpha(1f)
//                .scaleX(1f)
//                .scaleY(1f);
//    }


//    @Override
//    public void onItemSelected() {
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
//                R.animator.drag_scale_on);
//        set.setTarget(itemView);
//        set.start();
//    }
//
//    @Override
//    public void onItemClear() {
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
//                R.animator.drag_scale_off);
//        set.setTarget(itemView);
//        set.start();
//    }

    @Override
    public void onClick(View view) {
        final ArrayList<Event> events = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("events");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    events.add(snapshot.getValue(Event.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("events", Parcels.wrap(events));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}