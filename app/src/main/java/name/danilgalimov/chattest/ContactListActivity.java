package name.danilgalimov.chattest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ContactListFragment();
    }
}