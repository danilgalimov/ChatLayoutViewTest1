package name.danilgalimov.chattest;

;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment extends Fragment {

    private static final String ARG_CONTACT_ID = "contact_id";

    private Contact mContact;
    private RecyclerView mChatRecyclerView;
    private ChatAdapter mAdapter;

    public static ChatFragment newInstance(int contactId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT_ID, contactId);

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contactId = (int) getArguments().getSerializable(ARG_CONTACT_ID);
        mContact = ContactLab.get(getActivity()).getContact(contactId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        getActivity().setTitle(mContact.getDisplayName());

        ImageView imageViewBack = getActivity().findViewById(R.id.imageView_back);
        imageViewBack.setVisibility(View.VISIBLE);
        TextView name = getActivity().findViewById(R.id.name);
        name.setText(mContact.getDisplayName());
        CircleImageView toolbarAvatar = getActivity().findViewById(R.id.toolbar_avatar);
        toolbarAvatar.setImageResource(mContact.getAvatar());
        toolbarAvatar.setVisibility(View.VISIBLE);

        mChatRecyclerView = v.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        MessageLab messageLab = MessageLab.get(getActivity());
        List<Message> messages = messageLab.getMessages();

        if (mAdapter == null) {
            mAdapter = new ChatAdapter(messages);
            mChatRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TheirMessageHolder extends RecyclerView.ViewHolder {

        private TextView mTheirMessageBody;
        private CircleImageView mTheirMessageAvatar;
        private Message mMessage;

        public TheirMessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_theirmessage, parent, false));

            mTheirMessageBody = itemView.findViewById(R.id.their_message_body);
            mTheirMessageAvatar = itemView.findViewById(R.id.their_message_avatar);
        }

        public void bind(Message message) {
            mMessage = message;
            mTheirMessageBody.setText(mMessage.getText());
            mTheirMessageAvatar.setImageResource(mContact.getAvatar());
        }
    }

    private class MyMessageHolder extends RecyclerView.ViewHolder {

        private TextView mMyMessageBody;
        private Message mMessage;

        public MyMessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_mymessage, parent, false));

            mMyMessageBody = itemView.findViewById(R.id.my_message_body);
        }

        public void bind(Message message) {
            mMessage = message;
            mMyMessageBody.setText(message.getText());
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Message> mMessages;

        public ChatAdapter(List<Message> messages) {
            mMessages = messages;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            if (viewType == 0) {
                return new TheirMessageHolder(layoutInflater, parent);
            }
            return new MyMessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder.getItemViewType() == 0) {
                TheirMessageHolder theirMessageHolder = (TheirMessageHolder) holder;
                theirMessageHolder.bind(mMessages.get(position));
            } else {
                MyMessageHolder myMessageHolder = (MyMessageHolder) holder;
                myMessageHolder.bind(mMessages.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mMessages.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mMessages.get(position).getSender().equals("their")) return 0;
            else return 1;
        }
    }
}
