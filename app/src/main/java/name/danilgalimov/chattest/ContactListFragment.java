package name.danilgalimov.chattest;

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

public class ContactListFragment extends Fragment {

    private RecyclerView mContactRecyclerView;
    private ContactAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        mContactRecyclerView = view.findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TextView name = getActivity().findViewById(R.id.name);
        name.setText(R.string.app_name);
        ImageView imageViewBack = getActivity().findViewById(R.id.imageView_back);
        imageViewBack.setVisibility(View.GONE);
        CircleImageView toolbarAvatar = getActivity().findViewById(R.id.toolbar_avatar);
        toolbarAvatar.setVisibility(View.GONE);

        updateUI();

        return view;
    }

    private void updateUI() {
        ContactLab contactLab = ContactLab.get(getActivity());
        List<Contact> contacts = contactLab.getContacts();

        if (mAdapter == null) {
            mAdapter = new ContactAdapter(contacts);
            mContactRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mInfoTextView;
        private CircleImageView mContactAvatar;
        private Contact mContact;

        public ContactHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_contact, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.chat_title);
            mInfoTextView = itemView.findViewById(R.id.chat_info);
            mContactAvatar = itemView.findViewById(R.id.contact_avatar);
        }

        public void bind(Contact contact) {
            mContact = contact;
            mTitleTextView.setText(mContact.getDisplayName());
            mInfoTextView.setText(mContact.getPhoneNumber());
            mContactAvatar.setImageResource(mContact.getAvatar());
        }

        @Override
        public void onClick(View view) {
            Intent intent = ChatActivity.newIntent(getActivity(), mContact.getContactId());
            startActivity(intent);
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

        private List<Contact> mContacts;

        public ContactAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @NonNull
        @Override
        public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ContactHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
            Contact contact = mContacts.get(position);
            holder.bind(contact);
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }
    }
}
