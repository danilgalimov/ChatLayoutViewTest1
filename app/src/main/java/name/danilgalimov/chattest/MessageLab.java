package name.danilgalimov.chattest;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageLab {
    private static MessageLab sMessageLab;

    private List<Message> mMessages;

    public static MessageLab get(Context context) {
        if (sMessageLab == null) {
            sMessageLab = new MessageLab(context);
        }
        return sMessageLab;
    }
    private MessageLab(Context context) {
        mMessages = new ArrayList<>();
        mMessages.add(new Message("their", "Как говорилось ранее, синглеты часто используются некорректно."));
        mMessages.add(new Message("their", "У программиста возникает искушение применять синглеты везде и повсюду, потому что они удобны: к ним можно обращаться откуда угодно и в них можно хранить любую информацию, которая может понадобиться позднее."));
        mMessages.add(new Message("me", "Но при этом вы не пытаетесь ответить на важные вопросы: где используются эти данные? чем важен этот метод?"));
        mMessages.add(new Message("their", "Синглет обходит эти вопросы."));
        mMessages.add(new Message("me", "Любой разработчик, который придет после вас, откроет синглет и обнаружит некое подобие сундука, в который сваливают всякий  хлам: батарейки, стяжки для кабелей, старые фотографии."));
        mMessages.add(new Message("me", "Как говорилось ранее, синглеты часто используются некорректно."));
        mMessages.add(new Message("their", "Зачем здесь все это?"));
        mMessages.add(new Message("their", "Убедитесь в том, что все содержимое синглета действительно глобально и для хранения данных в синглете существуют веские причины."));
        mMessages.add(new Message("me", "Впрочем, с учетом всего сказанного синглеты являются ключевым компонентом хорошо спланированного приложения Android — при условии правильного использования."));

    }

    public List<Message> getMessages() {
        return mMessages;
    }

}
