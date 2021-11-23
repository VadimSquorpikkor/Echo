package com.squorpikkor.echo_2;

import android.bluetooth.BluetoothAdapter;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothService mService = null;

    private DataRegister dataRegister;

    private final MutableLiveData<Boolean> letDiscovery;

    public MainViewModel() {
        dataRegister = new DataRegister();

        this.letDiscovery = new MutableLiveData<>(false);

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Initialize the BluetoothService to perform bluetooth connections
        mService = new BluetoothService(dataRegister);

        ensureDiscoverable();
    }



    public DataRegister getDataRegisters() {
        return dataRegister;
    }
    public MutableLiveData<Boolean> getLetDiscovery() {
        return letDiscovery;
    }

    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            getLetDiscovery().setValue(true);
            // Start the Bluetooth chat services
            mService.start();
        } else {
            // Start the Bluetooth chat services
            mService.start();
        }
    }

    @Override
    protected void onCleared() {
        Log.e("TAG", "onDestroy");
        // Stop the Bluetooth chat services
        if (mService != null) mService.stop();
        super.onCleared();
    }
}
