//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fm.sdk.deviceid;

import android.content.Context;
import android.util.Log;

import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;

public class MiitHelper implements IIdentifierListener {
    private static final String TAG = MiitHelper.class.getSimpleName();
    private static volatile MiitHelper sMiitHelper;
    private static boolean isSupportOaid;
    private static volatile String oaid;
    private static volatile String vaid;
    private static volatile String aaid;

    public static MiitHelper getInstance() {
        if (sMiitHelper == null) {
            Class var0 = MiitHelper.class;
            synchronized(MiitHelper.class) {
                if (sMiitHelper == null) {
                    sMiitHelper = new MiitHelper();
                }
            }
        }

        return sMiitHelper;
    }

    public MiitHelper() {
    }

    public void setIsSupportOaid(boolean supportOaid) {
        isSupportOaid = supportOaid;
    }

    public boolean isSupportOaid() {
        return isSupportOaid;
    }

    public String getOaid() {
        return oaid;
    }

    public String getVaid() {
        return vaid;
    }

    public String getAaid() {
        return aaid;
    }

    public void init(Context context) {
        long timeb = System.currentTimeMillis();
        int nres = this.CallFromReflect(context);
        long timee = System.currentTimeMillis();
        long offset = timee - timeb;
        if (nres == 1008612) {
            this.setIsSupportOaid(false);
        } else if (nres == 1008613) {
            this.setIsSupportOaid(false);
        } else if (nres == 1008611) {
            this.setIsSupportOaid(false);
        } else if (nres == 1008614) {
            this.setIsSupportOaid(false);
        } else if (nres == 1008615) {
            this.setIsSupportOaid(false);
        } else if (nres == 0) {
            this.setIsSupportOaid(true);
        }

        Log.d(TAG, "return value: " + String.valueOf(nres) + ", getTime = " + offset);
    }

    private int CallFromReflect(Context context) {
        return MdidSdkHelper.InitSdk(context, true, this);
    }

    public void OnSupport(boolean isSupport, IdSupplier _supplier) {
        if (_supplier != null) {
            this.setIsSupportOaid(_supplier.isSupported());
            oaid = _supplier.getOAID();
            vaid = _supplier.getVAID();
            aaid = _supplier.getAAID();
        }
    }
}
