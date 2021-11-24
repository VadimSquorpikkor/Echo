package com.squorpikkor.echo_2;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

class BluetoothService {

    public static final String TAG = "tag";
    // Name for the SDP record when creating server socket
    private static final String NAME = "BluetoothChat2";
    // Unique UUID for this application
//    private static final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");//условно работает
//    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static final UUID MY_UUID = UUID.fromString("00001103-0000-1000-8000-00805f9b34fb");//работает
//    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//    private static final UUID MY_UUID = UUID.fromString("0000110a-0000-1000-8000-00805f9b34fb");

    private DataRegister dataRegister;

    private MutableLiveData<String> info;


    public BluetoothService(DataRegister dataRegister, MutableLiveData<String> info) {
        Log.e(TAG, "♣START BluetoothService");
        this.mState = STATE_NONE;
        mAdapter = BluetoothAdapter.getDefaultAdapter();

//        mBuffer = new byte[3084];
        mTotalBytes = 50;//MESSAGE_DEFAULT_LENGTH;
        this.dataRegister = dataRegister;

        this.info = info;
    }

    private int mState;
    private final BluetoothAdapter mAdapter;
    private AcceptThread mAcceptThread;
    private ConnectedThread mConnectedThread;

    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device

    public synchronized void start() {

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start the thread to listen on a BluetoothServerSocket
        if (mAcceptThread == null) {
            mAcceptThread = new AcceptThread();
            mAcceptThread.start();
        }
        setState(STATE_LISTEN);
    }

    private synchronized void setState(int state) {
        mState = state;
    }

    private class AcceptThread extends Thread {
        // The local server socket
        private final BluetoothServerSocket mmServerSocket;

        boolean isRunning = true;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;

            // Create a new listening server socket
            try {
                tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
                Log.e(TAG, "♦AcceptThread: LISTENING...");
            } catch (IOException e) {
                Log.e(TAG, "listen() failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            setName("AcceptThread");
            BluetoothSocket socket = null;
            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED && isRunning) {
            //int count = 0;
                //Log.e(TAG, "run: "+count++);
                try {
                    Log.e(TAG, "run: Wait connection...");
                    info.postValue("Wait connection...");
                    //Thread.sleep(1000);//это только для проверки, потом убрать
                    socket = mmServerSocket.accept();
                } catch (Exception e2) {
                    Log.e(TAG, "run: Exception e2");
                }

                Log.e(TAG, "run: ACCEPTED");

                // If a connection was accepted
                if (socket != null) {
                    synchronized (BluetoothService.this) {
                        switch (mState) {
                            case STATE_LISTEN:
                            case STATE_CONNECTING:
                                // Situation normal. Start the connected thread.
                                Log.e(TAG, "run: OK");
                                connected(socket, socket.getRemoteDevice());
                                break;
                            case STATE_NONE:
                            case STATE_CONNECTED:
                                // Either not ready or already connected. Terminate new socket.
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    Log.e(TAG, "Could not close unwanted socket", e);
                                }
                                break;
                        }
                    }
                }
            }
            Log.e(TAG, "run: completed");
        }

        public void cancel() {
            try {
                mmServerSocket.close();
                isRunning = false;
            } catch (IOException e) {
                Log.e(TAG, "close() of server failed", e);
            }
        }
    }

    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
        Log.e(TAG, "♣connected: ");
        info.postValue("Connected");

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Cancel the accept thread because we only want to connect to one device
        if (mAcceptThread != null) {
            mAcceptThread.cancel();
            mAcceptThread = null;
        }

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        setState(STATE_CONNECTED);
    }



    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */
    private class ConnectedThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;


        private NotificationManager mNotificationManager;

            private final byte[] mBuffer;

            boolean isRunning = true;

        Response response1 = new Response(dataRegister);

        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "create ConnectedThread");

            mBuffer = new byte[3084];

            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;


            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;

//            Response response = new Response()
        }

        public void run() {
            Log.e(TAG, "BEGIN mConnectedThread");
//            while (!mConnectedThread.isInterrupted()) {
            while (isRunning) {
                try {
                    // Read from the InputStream
                    int x1 = mmInStream.read();

                    byte[] buffer = this.mBuffer;
                    buffer[0]=(byte)x1;
                    int currentPosition = 1;//0
                    long startTime = System.currentTimeMillis();
                    //until time passes or we get all the bytes
                    while ((System.currentTimeMillis() - startTime < TIMEOUT_DEFAULT) && currentPosition != mTotalBytes) {
                        if (mmInStream.available() > 0) {
                            int x = mmInStream.read();
                            //Log.e(TAG, "run: x = "+x);
                            buffer[currentPosition] = (byte) x;
                            currentPosition++;
                        }
                    }

                    byte[] request = Arrays.copyOf(buffer, currentPosition);

                    info.postValue(">>> "+Arrays.toString(request));

                    //Log.e(TAG, ">>> "+Arrays.toString(request));



                    byte[] response = response1.getResponseByRequest(request);
                    if (response!=null) mmOutStream.write(response);
                    else info.postValue("Unknown request");

                    ///mmOutStream.write(new byte[]{1, 17, 18, 48, 0, 1, 114, 21, 10, 4, 0, 12, 0, 12, 6, 0, 0, 0, 0, 0, 0, 51, 46});


                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    info.postValue("Disconnected");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    connectionLost();
                    break;
                }
            }
        }

        public void cancel() {
            try {
                isRunning = false;
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    private void connectionLost() {
        start();
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        if (mAcceptThread != null) {
            mAcceptThread.cancel();
            mAcceptThread = null;
        }
        setState(STATE_NONE);
    }

    private static final int MESSAGE_DEFAULT_LENGTH = 7;
    private static final int TIMEOUT_DEFAULT = 200;

    private int mTotalBytes;

}
