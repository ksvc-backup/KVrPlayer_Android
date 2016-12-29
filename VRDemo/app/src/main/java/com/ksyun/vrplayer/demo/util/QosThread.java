package com.ksyun.vrplayer.demo.util;

import android.content.Context;
import android.os.Debug;
import android.os.Handler;

import com.ksyun.vrplayer.demo.activity.VideoPlayerActivity;


/**
 * @author xinbaicheng@kingsoft.com
 * 2016/7/27.
 */
public class QosThread extends Thread {

    private Context mContext;
    private Handler mHandler;
    private Cpu mCpuStats;
    private Debug.MemoryInfo mi;
    private QosObject mQosObject;
    private String mPackageName;

    private boolean mRunning;

    public QosThread(Context context, Handler handler) {
        mHandler = handler;
        mCpuStats = new Cpu();
        mi = new Debug.MemoryInfo();
        mRunning = true;
        mQosObject = new QosObject();
        if(context != null)
            mPackageName = context.getPackageName();
    }

    @Override
    public void run() {
        while(mRunning) {
            mCpuStats.parseTopResults(mPackageName);

            Debug.getMemoryInfo(mi);

            if(mHandler != null) {
                mQosObject.cpuUsage = mCpuStats.getProcessCpuUsage();
                mQosObject.pss = mi.getTotalPss();
                mQosObject.vss = mi.getTotalPrivateDirty();
                mHandler.obtainMessage(VideoPlayerActivity.UPDATE_QOSMESS, mQosObject).sendToTarget();
            }
            try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread() {
        mRunning = false;
    }
}
