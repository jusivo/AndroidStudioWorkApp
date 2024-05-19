package com.example.bandymas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.bandymas.other_classes.JobInformation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class job_fill_form extends AppCompatActivity {
    //private job_db db;
    //private FirebaseAuth mAuth;
    private EditText title, decription, price;
    private String selectCatgOne;
    private String selectCatgTwo;
   // private ArrayList<JobInformation>list;
    private Uri imageUrl;
    private AppCompatButton btn_add_job;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_job_fill_form);
        //inflater of menu:
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //spinner - job specification choice of user:
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.job_select,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);
        //image picker for user:
        FloatingActionButton image_pick_button =findViewById(R.id.floatingActionButton4);

        //get the text from spinner for database:
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCatgOne = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText();
            }
        });
        //spinner for regions:
        Spinner spinner_2 = (Spinner) findViewById(R.id.spinner_sec);
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(
                this,
                R.array.town_select,
                android.R.layout.simple_spinner_item
        );
        adapter_2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner_2.setAdapter(adapter_2);

        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCatgTwo = spinner_2.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //methods for saving and passing information:
        pickImage();
        btn_add_job = findViewById(R.id.add_aJob_btn);
        btn_add_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = findViewById(R.id.title_txt);
                decription = findViewById(R.id.job_descript_txt);
                price = findViewById(R.id.priceInput);

                if (imageUrl!=null)
                {
                    String imageUrlString = imageUrl.toString();
                    String title_tx = title.getText().toString();
                    String descriptionn = decription.getText().toString();
                    String priceString = price.getText().toString();
                    //JobInformation jobInformation = new JobInformation(title_tx, imageUrl);
                    //jobs.addJobInformation(jobInformation);
                    Intent intent = new Intent(job_fill_form.this, jobs.class);
                /*intent.putExtra("titleEx", title_tx);
                intent.putExtra("imageUri", imageUrlString);
                intent.putExtra("descriptionx", descriptionn);
                intent.putExtra("selectionOne", selectCatgOne);
                intent.putExtra("selectionTwo", selectCatgTwo);
                intent.putExtra("pricePass", priceString);
                 */
                    SharedPreferences preferences = getSharedPreferences("MyPrefsJob", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("selectedURL", imageUrlString);
                    editor.putString("selectedTitle", title_tx);
                    editor.putString("selectedDescription", descriptionn);
                    editor.putString("selectedPrice", priceString);
                    editor.putString("selectedCatOne", selectCatgOne);
                    editor.putString("selectedCatTwo", selectCatgTwo);
                    editor.apply();
                    startActivity(intent);

                }
                else
                {
                    int defaultDrawableResId = R.drawable.no_ft;
                    imageUrl = Uri.parse("android.resource://" + getPackageName() + "/" + defaultDrawableResId);
                    String title_tx = title.getText().toString();
                    String descriptionn = decription.getText().toString();
                    String priceString = price.getText().toString();
                    //JobInformation jobInformation = new JobInformation(title_tx, imageUrl);
                    //jobs.addJobInformation(jobInformation);
                    Intent intent = new Intent(job_fill_form.this, jobs.class);
                /*intent.putExtra("titleEx", title_tx);
                intent.putExtra("imageUri", imageUrlString);
                intent.putExtra("descriptionx", descriptionn);
                intent.putExtra("selectionOne", selectCatgOne);
                intent.putExtra("selectionTwo", selectCatgTwo);
                intent.putExtra("pricePass", priceString);
                 */
                    SharedPreferences preferences = getSharedPreferences("MyPrefsJob", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("selectedURL", imageUrl.toString());
                    editor.putString("selectedTitle", title_tx);
                    editor.putString("selectedDescription", descriptionn);
                    editor.putString("selectedPrice", priceString);
                    editor.putString("selectedCatOne", selectCatgOne);
                    editor.putString("selectedCatTwo", selectCatgTwo);
                    editor.apply();
                    startActivity(intent);

                }
            }
        });
        //back to home page:
        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(job_fill_form.this, home_page.class);
                startActivity(intent);
            }
        });
    }
    //paimti image url:
    private void pickImage()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ImageView view = findViewById(R.id.imageUserUploaded);
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent data = o.getData();
                            if (data!= null)
                            {
                                Uri photo = data.getData();
                                 imageUrl =  photo;
                                //use imageview to change uri?
                                view.setImageURI(photo);
                            }
                        }
                    }
                }
        );
        FloatingActionButton open_gallery = findViewById(R.id.floatingActionButton4);
        open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someActivityResultLauncher.launch(gallery);
            }
        });
    }

}