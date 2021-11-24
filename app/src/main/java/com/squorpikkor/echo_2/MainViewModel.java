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
    private final MutableLiveData<String> info;

    public MainViewModel() {
        dataRegister = new DataRegister();

        this.letDiscovery = new MutableLiveData<>(false);
        this.info = new MutableLiveData<>();

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Initialize the BluetoothService to perform bluetooth connections
        mService = new BluetoothService(dataRegister, info);

        ensureDiscoverable();
    }



    public DataRegister getDataRegisters() {
        return dataRegister;
    }
    public MutableLiveData<Boolean> getLetDiscovery() {
        return letDiscovery;
    }
    public MutableLiveData<String> getInfo() {
        return info;
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
