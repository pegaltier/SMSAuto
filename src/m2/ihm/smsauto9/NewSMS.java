package m2.ihm.smsauto9;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewSMS extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_sms, menu);
        return true;
    }
    //COMMENT PE
    //aaa
}
