package com.example.quiz1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3;
    RadioGroup rg;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        rg = findViewById(R.id.rg);
        b1 = findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        String nama = et1.getText().toString();
        String kodeBarang = et2.getText().toString();
        int jumlah = Integer.parseInt(et3.getText().toString());

        long harga = getHarga(kodeBarang);
        if (harga == -1) {
            Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        long totalHarga = harga * jumlah;
        long diskonHarga = discharga(totalHarga);
        long diskonMember = discmember(totalHarga);
        long price = totalHarga - diskonHarga - diskonMember;

        RadioButton selectedRadioButton = findViewById(rg.getCheckedRadioButtonId());
        String tipeMember = selectedRadioButton.getText().toString();

        Intent intent = new Intent(MainActivity.this, ResultActavity.class);
        intent.putExtra("Nama", nama);
        intent.putExtra("Tipe Member", tipeMember);
        intent.putExtra("Kode Barang", kodeBarang);
        intent.putExtra("Nama Barang", namaBarang(kodeBarang));
        intent.putExtra("Harga Barang", harga);
        intent.putExtra("Total Harga", totalHarga);
        intent.putExtra("Diskon Harga", diskonHarga);
        intent.putExtra("Jumlah Diskon Member", diskonMember);
        intent.putExtra("Jumlah Bayar", price);
        startActivity(intent);
    }
    private long getHarga(String kodeBarang) {
        switch (kodeBarang) {
            case "OAS":
                return 1989123;
            case "AAE":
                return 8676981;
            case "LV3":
                return 6666666;
            default:
                return -1;
        }
    }


    private long discharga(long totalHarga) {
        if (totalHarga > 10000000) {
            return 100000;
        }
        return 0;
    }

    private long discmember(long totalHarga) {
        RadioButton radioButton = findViewById(rg.getCheckedRadioButtonId());
        String diskonmember = radioButton.getText().toString();
        switch (diskonmember) {
            case "Gold":
                return (long) (totalHarga * 0.10);
            case "Silver":
                return (long) (totalHarga * 0.05);
            case "Biasa":
                return (long) (totalHarga * 0.02);
            default:
                return 0;
        }
    }

    private String namaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "OAS":
                return "Oppo a5s";
            case "AAE":
                return "Acer Aspire E14";
            case "LV3":
                return "Lenovo V14 Gen 3";
            default:
                return "";
        }
    }
}