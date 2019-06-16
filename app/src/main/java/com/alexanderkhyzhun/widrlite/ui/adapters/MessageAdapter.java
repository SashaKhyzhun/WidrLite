package com.alexanderkhyzhun.widrlite.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alexanderkhyzhun.widrlite.R;
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message;
import com.alexanderkhyzhun.widrlite.ui.adapters.viewholders.ChatMessageViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Khyzhun
 * Created on 16 June, 2019
 */
public class MessageAdapter extends BaseAdapter {

    private List<Message> messages = new ArrayList<Message>();
    private Callback callback;
    private Context context;

    public interface Callback {
        void onLongClicked(String text);
    }

    public MessageAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }


    public void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    public void remove(String text) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getText().equals(text)) {
                messages.remove(messages.get(i));
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ChatMessageViewHolder holder = new ChatMessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if (messageInflater != null) {

            if (message.isBelongsToCurrentUser()) {
                convertView = messageInflater.inflate(R.layout.item_message_owner, null);
                holder.messageBody = convertView.findViewById(R.id.item_message_owner_tv);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getText());
            } else {
                convertView = messageInflater.inflate(R.layout.item_message_sender, null);
                holder.messageBody = convertView.findViewById(R.id.item_message_sender_tv);
                convertView.setTag(holder);

                holder.name.setText(message.getMemberData().getName());
                holder.messageBody.setText(message.getText());
                GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
                drawable.setColor(Color.parseColor(message.getMemberData().getColor()));
            }
        }

        convertView.setOnLongClickListener(view -> {

            callback.onLongClicked(holder.messageBody.getText().toString());
            return false;
        });

        return convertView;
    }

}