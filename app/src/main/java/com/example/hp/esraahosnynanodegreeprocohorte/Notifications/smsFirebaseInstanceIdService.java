package com.example.hp.esraahosnynanodegreeprocohorte.Notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by HP on 14/01/2017.
 */

public class smsFirebaseInstanceIdService extends FirebaseInstanceIdService {

    //eBET5AIX-ec:APA91bEv3VwM-TtMw-wL9IfiSTlUxdIAt5Lb7bUCc55CRCdKMX2l_tlITXMR5Csk1mt1JiKBnDS03yng4lSFSUvg50vb3KhxoDcDgbM4OekO7EFmoaXCQ0teXNkE3Oi5sf_7lOMUzd1r
    private static final String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);
    }
}
