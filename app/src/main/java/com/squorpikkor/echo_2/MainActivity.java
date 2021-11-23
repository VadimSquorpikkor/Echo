package com.squorpikkor.echo_2;

import static com.squorpikkor.echo_2.Constant.DEF_DATA_REGISTERS;
import static com.squorpikkor.echo_2.Constant.DEF_DATA_REGISTERS_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(MainActivity.this).get(MainViewModel.class);
        mViewModel.getLetDiscovery().observe(this, this::startDiscovery);


        Log.e("TAG", "onCreate: byte->int = "+Converter.toInt32(new byte[]{101, 1, 0, 0}, 0));
        Log.e("TAG", "onCreate: int->byte = "+ Arrays.toString(Converter.integerToByte(357)));

        Log.e("TAG", "onCreate: byte->float = "+Converter.toFloat(new byte[]{72, -31, 122, 63}, 0));
        Log.e("TAG", "onCreate: float->byte = "+ Arrays.toString(Converter.floatToByte(0.98f)));

        Log.e("TAG", "onCreate: byte->float = "+Converter.toInt16(new byte[]{89, 1}, 0));
        Log.e("TAG", "onCreate: float->byte = "+ Arrays.toString(Converter.shortToByte((short)345)));

        Log.e("TAG", "onCreate: 1 & 255 & 0x01 = "+ (1 & 255 & 0x01));
        Log.e("TAG", "onCreate: 1 & 255 >> 1 & 1 = "+ (2 & 255 >> 1 & 1));



//        Log.e("TAG", "onCreate: crc = "+ Converter.calcCRC(DEF_DATA_REGISTERS));
//        Log.e("TAG", "onCreate: crc = "+ Arrays.toString(Converter.integerToByte(Converter.calcCRC(DEF_DATA_REGISTERS))));
//        Log.e("TAG", "onCreate: crc = "+ Arrays.toString(Converter.integerToByte(Converter.calcCRC(DEF_DATA_REGISTERS_2))));

        EditText countText = findViewById(R.id.count);

//        findViewById(R.id.button).setOnClickListener(v->mViewModel.getDataRegisters().setG_momcps(Integer.parseInt(countText.getText().toString())));
        findViewById(R.id.button).setOnClickListener(v->mViewModel.getDataRegisters().setG_dr(Float.parseFloat(countText.getText().toString())));

    }

    void startDiscovery(Boolean aBoolean) {
        if (!aBoolean) return;
        mViewModel.getLetDiscovery().setValue(false);
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }


}