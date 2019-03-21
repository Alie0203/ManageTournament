package com.apps.managetournament;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class tournamentAdapter extends ArrayAdapter<User> {

      private Activity context;
      private List<User>users;
public tournamentAdapter(Activity context , List<User>users){
    super(context,R.layout.tournamen_list,users);
    this.context = context;
    this.users = users;

}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.tournamen_list,null,true);

        TextView textView2 = (TextView)listview.findViewById(R.id.textView2);
        TextView textView3 = (TextView)listview.findViewById(R.id.textView3);

        User user = users.get(position);

        textView2.setText(user.getName());
        textView3.setText(user.getUserName());

        return listview;
    }
}
