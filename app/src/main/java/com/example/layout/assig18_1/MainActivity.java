package com.example.layout.assig18_1;
//Package objects contain version information about the implementation and specification of a Java package
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //public keyword is used in the declaration of a class,method or field;public classes,method and fields can be accessed by the members of any class.
//extends is for extending a class. implements is for implementing an interface
//AppCompatActivity is a class from e v7 appcompat library. This is a compatibility library that back ports some features of recent versions of
// Android to older devices.
    //CREATING OBJECT OF TEXTVIEW
    TextView batteryPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Variables, methods, and constructors, which are declared protected in a superclass can be accessed only by the subclasses
        // in other package or any class within the package of the protected members class.
        //void is a Java keyword.  Used at method declaration and definition to specify that the method does not return any type,
        // the method returns void.
        //onCreate Called when the activity is first created. This is where you should do all of your normal static set up: create views,
        // bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
        // if there was one.Always followed by onStart().
        //Bundle is most often used for passing data through various Activities.
// This callback is called only when there is a saved instance previously saved using onSaveInstanceState().
// We restore some state in onCreate() while we can optionally restore other state here, possibly usable after onStart() has
// completed.The savedInstanceState Bundle is same as the one used in onCreate().
        // call the super class onCreate to complete the creation of activity like the view hierarchy
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //  main is the xml you have created under res->layout->main.xml
        //  Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        // The other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        // the design
        ///findViewById:A user interface element that displays text to the user.

        //initilazing the texview
        batteryPercent = (TextView) findViewById(R.id.txtPercentage);
        //calling getPercentage() method
        getPercentage();

    }

    //getPercentage method
    private void getPercentage() {
        //creating object of BroadcastReciver
        //Base class for code that receives and handles broadcast intents sent by sendBroadcast(Intent).
        BroadcastReceiver myReciever = new BroadcastReceiver() {
            //overriding the onReceive method
            @Override
            public void onReceive(Context context, Intent intent) {
//This method is called when the BroadcastReceiver is receiving an Intent broadcast.
                //Parameters
                //context	Context: The Context in which the receiver is running.
                //intent	Intent: The Intent being received.
                context.unregisterReceiver(this);
                //Unregister a previously registered BroadcastReceiver. All filters that have been registered for this BroadcastReceiver will be removed.
               //Parameters
                //receiver	BroadcastReceiver: The BroadcastReceiver to unregister.
                /**Retrieve extended data from the intent.

                 Parameters
                 name	String: The name of the desired item.
                 defaultValue	int: the value to be returned if no value of the desired type is stored with the given name.
                 Returns
                 int	the value of an item that previously added with putExtra() or the default value if none was found.
**/
                 int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                 //Extra for ACTION_BATTERY_CHANGED: integer field containing the current battery level, from 0 to EXTRA_SCALE.
                //Constant Value: "level"
                int scale = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int level = -1;
                //we need togive level as -1
                //if currentLevel and scale are greater then level need to be calculated
                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }
                //setting the texview to current battery level
                batteryPercent.setText("Battery Level Remaining: " + level + "%");

            }
        };
       //Action_battery_changed:Broadcast Action: This is a sticky broadcast containing the charging state, level, and other information about the battery.
        // See BatteryManager for documentation on the contents of the Intent
        //passing the intentFilter and in it passing Action_battery_changed
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //Specifies the types of intents that an activity, service, or broadcast receiver can respond to
        registerReceiver(myReciever, batteryLevelFilter);
//Register a BroadcastReceiver to be run in the main activity thread.
    }
}












