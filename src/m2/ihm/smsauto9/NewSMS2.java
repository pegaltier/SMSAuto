package m2.ihm.smsauto9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class NewSMS2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sms2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_sms2, menu);
        return true;
    }
    
    
    
    /** Called when the user clicks the Send button */
    public void openActivityNewSMS(View view) {
        Intent intent = new Intent(this, NewSMS.class);
        startActivity(intent);
        NewSMS2.this.finish();
        
    }
    
    
}
