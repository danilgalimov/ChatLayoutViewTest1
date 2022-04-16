package name.danilgalimov.chattest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactLab extends AppCompatActivity {


    private String[] mName;
    private String[] mPhone;
    private int[] mAvatar;
    private static ContactLab sContactLab;
    private List<Contact> mContacts;

    public static ContactLab get(Context context) {
        if (sContactLab == null) {
            sContactLab = new ContactLab(context);
        }
        return sContactLab;
    }

    private ContactLab(Context context) {
        mContacts = new ArrayList<>();
        mName = new String[]{"Леонардо Ди Каприо", "Уилл Смит", "Роберт Дауни — младший", "Том Круз",
        "Дуэйн Джонсон", "Марк Уолберг", "Брэд Питт", "Джонни Депп", "Крис Хемсворт", "Роберт Де Ниро",
        "Бен Аффлек", "Сильвестр Сталлоне", "Ченнинг Татум", "Майкл Дуглас", "Джек Николсон", "Николас Кейдж",
        "Шайа Лабаф"};
        mPhone = new String[]{"4(904)839-50-98", "200(830)885-26-94", "0(7991)754-25-40", "61(208)171-79-87",
        "17(872)204-63-00", "47(995)375-44-42", "8(912)418-56-58", "62(38)348-61-40", "839(7899)314-98-97",
        "60(986)259-49-91", "305(94)483-97-18", "65(244)773-62-36", "19(614)380-67-77", "341(6644)189-41-58",
        "5(077)061-74-94", "2(2259)928-82-20", "134(312)297-93-64"};
        mAvatar = new int[]{R.drawable.leonardo_di_kaprio, R.drawable.uill_smit, R.drawable.robert_dauni_mladshiy,
        R.drawable.tom_kruz, R.drawable.dueyn_dzhonson, R.drawable.mark_uolberg, R.drawable.bred_pitt,
        R.drawable.dzhonni_depp, R.drawable.kris_khemsvort, R.drawable.robert_de_niro, R.drawable.ben_afflek,
        R.drawable.silvestr_stallone, R.drawable.chenning_tatum, R.drawable.maykl_duglas, R.drawable.dzhek_nikolson,
        R.drawable.nikolas_keydzh, R.drawable.shaya_labaf};
        for (int i = 0; i < mName.length; i++) {
            mContacts.add(new Contact(i, mName[i], mPhone[i], mAvatar[i]));
        }
    }

    public List<Contact> getContacts() { return mContacts; }

    public Contact getContact(int id) {
        for (Contact contact : mContacts) {
            if (contact.getContactId() == id) {
                return contact;
            }
        }
        return null;
    }
}
