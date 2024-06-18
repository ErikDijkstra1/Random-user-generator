package com.example.randomusergenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private static final String LOGTAG = PersonAdapter.class.getName();
    private LayoutInflater inflater;
    private OnItemClickListener clickListener;
    private List<Person> personList;

    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView nameView;
        public final TextView basicDataView;
        public final ImageView pictureView;

        public PersonViewHolder(View view, PersonAdapter adapter) {
            super(view);
            nameView = view.findViewById(R.id.nameView);
            basicDataView = view.findViewById(R.id.smallDataView);
            pictureView = view.findViewById(R.id.pictureView);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPos = getAdapterPosition();
            System.out.println(clickedPos);
            Log.i(LOGTAG, "Person " + clickedPos + " clicked");
            clickListener.onItemClick(clickedPos);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    public PersonAdapter(Context context, List<Person> PersonList, OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        personList = PersonList;
        clickListener = listener;
    }

    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(LOGTAG, "onCreateViewHolder() called");
        View view = inflater.inflate(R.layout.item_person, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Log.d(LOGTAG, "onBindViewHolder() called for position " + position);
        Person person = personList.get(position);
        Log.d(LOGTAG, "Zodiac sign = " + person.getFirstName());
        holder.nameView.setText(person.getFullName());
        holder.basicDataView.setText(person.getBasicData());
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(person.getPicture()).getContent());
            holder.pictureView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(LOGTAG, "couldnt find image");
        }
    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, "getItemCount() called");
        return personList.size();
    }
}
