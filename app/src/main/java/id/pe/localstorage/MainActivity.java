package id.pe.localstorage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button newFile;
    Button openFile;
    Button saveFile;
    EditText nameFile;
    EditText inputText;
    File path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newFile = (Button) findViewById(R.id.newFile);
        openFile = (Button) findViewById(R.id.openFile);
        saveFile = (Button) findViewById(R.id.saveFile);
        nameFile = (EditText) findViewById(R.id.nameFile);
        inputText = (EditText) findViewById(R.id.editableText);
        path = getFilesDir();
        newFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFile();
            }
        });
        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile();
            }
        });
        saveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });
    }
    public void newFile() {
        nameFile.setText("");
        inputText.setText("");
        Toast.makeText(this, "Clearing file", Toast.LENGTH_SHORT).show();
    }
    private void loadData(String title) {
        String text = FileHelper.readFromFile(this, title);
        nameFile.setText(title);
        inputText.setText(text);
        Toast.makeText(this, "Loading " + title + " data", Toast.LENGTH_SHORT).show();
    }
    public void openFile() {
        showList();
    }
	private void showList() {
        final ArrayList<String> arrayList = new ArrayList<String>();
        for (String file : path.list()) {
            arrayList.add(file);
        }
        final CharSequence[] items = arrayList.toArray(new CharSequence[arrayList.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih file yang diinginkan");
        builder.setItems(items, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int item) {
                loadData(items[item].toString());
	        }
	    });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void saveFile() {
        if (nameFile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Title harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
            String title = nameFile.getText().toString();
            String text = inputText.getText().toString();
            FileHelper.writeToFile(title, text, this);
            Toast.makeText(this, "Saving " + nameFile.getText().toString() + " file", Toast.LENGTH_SHORT).show();
        }
    }

}