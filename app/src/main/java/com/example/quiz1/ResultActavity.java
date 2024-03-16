package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActavity extends AppCompatActivity {

    TextView tvNama, tvTipeMember, tvKodeBarang, tvNamaBarang, tvHargaBarang, tvTotalHarga, tvDiskonHarga, tvDiskonMember, tvJumlahBayar;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_actavity);

        tvNama = findViewById(R.id.tvNama);
        tvTipeMember = findViewById(R.id.tvTipeMember);
        tvKodeBarang = findViewById(R.id.tvKodeBarang);
        tvNamaBarang = findViewById(R.id.tvNamaBarang);
        tvHargaBarang = findViewById(R.id.tvHargaBarang);
        tvTotalHarga = findViewById(R.id.tvTotalHarga);
        tvDiskonHarga = findViewById(R.id.tvDiskonHarga);
        tvDiskonMember = findViewById(R.id.tvDiskonMember);
        tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        btnShare = findViewById(R.id.btnShare);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            tvNama.setText("Nama Pelanggan: " + extras.getString("Nama"));
            tvTipeMember.setText("Tipe Member: " + extras.getString("Tipe Member"));
            tvKodeBarang.setText("Kode Barang: " + extras.getString("Kode Barang"));
            tvNamaBarang.setText("Nama Barang: " + extras.getString("Nama Barang"));
            tvHargaBarang.setText("Harga Barang: " + extras.getLong("Harga Barang"));
            tvTotalHarga.setText("Total Harga: " + extras.getLong("Total Harga"));
            tvDiskonHarga.setText("Diskon Harga: " + extras.getLong("Diskon Harga"));
            tvDiskonMember.setText("Diskon Member: " + extras.getLong("Diskon Member"));
            tvJumlahBayar.setText("Jumlah Bayar: " + extras.getLong("Jumlah Bayar"));
        }

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareResult();
            }
        });
    }

    private void shareResult() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareMessage = "Nama: " + tvNama.getText().toString() +
                "\nTipe Member: " + tvTipeMember.getText().toString() +
                "\nKode Barang: " + tvKodeBarang.getText().toString() +
                "\nNama Barang: " + tvNamaBarang.getText().toString() +
                "\nHarga Barang: " + tvHargaBarang.getText().toString() +
                "\nTotal Harga: " + tvTotalHarga.getText().toString() +
                "\nDiskon Harga: " + tvDiskonHarga.getText().toString() +
                "\nDiskon Member: " + tvDiskonMember.getText().toString() +
                "\nJumlah Bayar: " + tvJumlahBayar.getText().toString();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share Result"));
    }
}