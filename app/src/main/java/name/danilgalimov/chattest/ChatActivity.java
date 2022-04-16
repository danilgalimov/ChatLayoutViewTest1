package name.danilgalimov.chattest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.UUID;

public class ChatActivity extends SingleFragmentActivity {

    private static final String EXTRA_CONTACT_ID = "name.danilgalimov.chattest.contact_id";

    public static Intent newIntent(Context packageContext, int contactId) {
        Intent intent = new Intent(packageContext, ChatActivity.class);
        intent.putExtra(EXTRA_CONTACT_ID, contactId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        int contactId = (int) getIntent().getSerializableExtra(EXTRA_CONTACT_ID);
        return ChatFragment.newInstance(contactId);
    }
}