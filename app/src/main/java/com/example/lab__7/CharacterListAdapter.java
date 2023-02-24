package com.example.lab__7;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
//
public class CharacterListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Character> characters;

    public CharacterListAdapter(Context context, List<Character> characters) {
        this.context = context;
        this.characters = characters;
    }

    @Override
    public int getCount() {
        return characters.size();
    }

    @Override
    public Object getItem(int position) {
        return characters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        Character character = characters.get(position);
        String text = character.getName();
        textView.setText(text);
        textView.setTextSize(20);
        return textView;
    }
}