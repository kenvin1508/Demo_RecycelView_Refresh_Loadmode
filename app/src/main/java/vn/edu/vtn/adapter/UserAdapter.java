package vn.edu.vtn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.demo_recycelview_refresh_loadmode.R;
import vn.edu.vtn.model.Message;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    ArrayList<Message> listMessages;
    public static final int SENDER = 0;
    public static final int RECEIVER = 1;


    public UserAdapter(Context context, ArrayList<Message> listMessages) {
        this.context = context;
        this.listMessages = listMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (i == 0) {
            View view = inflater.inflate(R.layout.send_item, viewGroup, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            View view = inflater.inflate(R.layout.receive_item, viewGroup, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindata(i);
    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txtMessage);
        }

        public void bindata(int position) {
            Message message = listMessages.get(position);
            txtMessage.setText(message.getContentMessage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = listMessages.get(position);
        if (message.getSenderName().equals("Nghia")) {
            return SENDER;
        } else {
            return RECEIVER;
        }
    }
}
