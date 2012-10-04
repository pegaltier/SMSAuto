package m2.ihm.smsauto9;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class NewSMS extends Activity {
	private Button btnSaveSMS;
	private EditText txtPhoneNo;
	private EditText txtMessage;
    private TimePicker timePicker1;
    private static final int CONTACT_PICKER_RESULT = 1001;
	public final static String EXTRA_MESSAGE = "caa";
	
	
	private TextView tvDisplayTime;
	private TextView tvDisplayDate;
	private Button btnChangeTime;
	private Button btnChangeDate;
	
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	
	static final int TIME_DIALOG_ID = 999;
	static final int DATE_DIALOG_ID = 998;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sms);
        btnSaveSMS = (Button) findViewById(R.id.btnSaveSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        //timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        //timePicker1.setIs24HourView(true);
        
		setCurrentDateTimeOnView();
		addListenerOnButtonTime();
		addListenerOnButtonDate();
        
        btnSaveSMS.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
            {     
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();                 
                if (phoneNo.length()>0 && message.length()>0)                
                    System.out.print("ok");//sendSMS(phoneNo, message);                
                else
      
                	Toast.makeText(getBaseContext(), 
                        "Please enter both phone number and message.", 
                        Toast.LENGTH_SHORT).show();
            }
        });        

        
         
    }   // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_sms, menu);
        return true;
    }
    

    
    /** Called when the user clicks the Send button */
    public void openActivityNewSMS2(View view) {
        Intent intent = new Intent(this, NewSMS2.class);
        /*
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        
        */
        startActivity(intent);
        NewSMS.this.finish();
        
    }
    
    
    
    
    /*
     * 
     * 
     * Popup TimePicker
     * 
     * 
     */
    
    
    
	// display current date  & time
	public void setCurrentDateTimeOnView() {

		tvDisplayTime = (TextView) findViewById(R.id.tvTime);
		tvDisplayDate = (TextView) findViewById(R.id.tvDate);
		//timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

		final Calendar c = Calendar.getInstance();		
		day = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);
		tvDisplayDate.setText(new StringBuilder().append(pad(day)).append("/")
		.append(pad(month)).append("/").append(pad(year)));
				
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		// set current time into textview
		tvDisplayTime.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));
		// set current time into timepicker
		//timePicker1.setCurrentHour(hour);
		//timePicker1.setCurrentMinute(minute);

	}

	public void addListenerOnButtonTime() {

		btnChangeTime = (Button) findViewById(R.id.btnChangeTime);
		btnChangeTime.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);
		
		case DATE_DIALOG_ID:
			// set date picker as current time
			return new DatePickerDialog(this, datePickerListener, year, month, day);
		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			// set current time into textview
			tvDisplayTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));

			// set current time into timepicker
			//timePicker1.setCurrentHour(hour);
			//timePicker1.setCurrentMinute(minute);

		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	
	/*
	 * 
	 * Date Pick Up
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
    
	public void addListenerOnButtonDate() {

		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
		btnChangeDate.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set current time into textview
			tvDisplayDate.setText(new StringBuilder().append(pad(day)).append("/")
					.append(pad(month)).append("/").append(pad(year)));
		}
	};
    
    /*
     * 
     * Contact Picker
     * 
     * 
     * 
     * 
     */
    
    public void doLaunchContactPicker(View view) {  
    	Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);  
    	startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
   }


    @Override   
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {   
        if (resultCode == RESULT_OK) {   
            switch (requestCode) {   
            case CONTACT_PICKER_RESULT: 
                final EditText phoneInput = (EditText) findViewById(R.id.txtPhoneNo); 
                Cursor cursor = null;   
                String phoneNumber = ""; 
                List<String> allNumbers = new ArrayList<String>(); 
                int phoneIdx = 0; 
                try {   
                    Uri result = data.getData();   
                    String id = result.getLastPathSegment();   
                    cursor = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id }, null);   
                    phoneIdx = cursor.getColumnIndex(Phone.DATA); 
                    if (cursor.moveToFirst()) { 
                        while (cursor.isAfterLast() == false) { 
                            phoneNumber = cursor.getString(phoneIdx); 
                            allNumbers.add(phoneNumber); 
                            cursor.moveToNext(); 
                        } 
                    } else { 
                        //no results actions 
                    }   
                } catch (Exception e) {   
                   //error actions 
                } finally {   
                    if (cursor != null) {   
                        cursor.close(); 
                    } 
    
                    final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]); 
                    AlertDialog.Builder builder = new AlertDialog.Builder(this); 
                    builder.setTitle("Choose a number"); 
                    builder.setItems(items, new DialogInterface.OnClickListener() { 
                        public void onClick(DialogInterface dialog, int item) { 
                            String selectedNumber = items[item].toString(); 
                            selectedNumber = selectedNumber.replace("-", ""); 
                            phoneInput.setText(selectedNumber); 
                        } 
                    }); 
                    
                    AlertDialog alert = builder.create(); 
                    if(allNumbers.size() > 1) { 
                        alert.show(); 
                    } else { 
                        String selectedNumber = phoneNumber.toString(); 
                        selectedNumber = selectedNumber.replace("-", ""); 
                        phoneInput.setText(selectedNumber); 
                    } 
     
                    if (phoneNumber.length() == 0) {   
                        //no numbers found actions   
                    }   
                }   
                break;   
            }   
        } else { 
           //activity result error actions 
        }   
    } 
}
