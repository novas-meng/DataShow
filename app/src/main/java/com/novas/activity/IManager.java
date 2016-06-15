/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/novas/Downloads/DataShow/app/src/main/aidl/com/novas/datashow/IBookManager.aidl
 */
package com.novas.activity;
// Declare any non-default types here with import statements

import android.os.RemoteException;

public interface IManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
     public static abstract class Stub extends android.os.Binder implements IManager
    {
        private static final java.lang.String DESCRIPTOR = "com.novas.datashow.Inovas";
/** Construct the stub at attach it to the interface. */
        public Stub()
      {
          System.out.println("this="+this);
          this.attachInterface(this, DESCRIPTOR);
      }
        @Override public android.os.IBinder asBinder()
        {
           // System.out.println("asbinder");
            return null;
        }
/**
 * Cast an IBinder object into an com.novas.datashow.IBookManager interface,
 * generating a proxy if needed.
 */
     public static IManager asInterface(android.os.IBinder obj)
    {
         if ((obj==null)) {
         return null;
         }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
     if (((iin!=null)&&(iin instanceof com.novas.datashow.IBookManager))) {
     return ((IManager)iin);
     }
      return new IManager.Stub.Proxy(obj);
     }

@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_addBook:
{
data.enforceInterface(DESCRIPTOR);
this.addBook();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements IManager
{
     private android.os.IBinder mRemote;
     Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
     @Override public android.os.IBinder asBinder()
{
return mRemote;
}
     public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     @Override public void addBook() throws android.os.RemoteException
    {
     android.os.Parcel _data = android.os.Parcel.obtain();
     android.os.Parcel _reply = android.os.Parcel.obtain();
       try {
       _data.writeInterfaceToken(DESCRIPTOR);
       mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
       _reply.readException();
          }
       finally {
       _reply.recycle();
           _data.recycle();
      }
    }
}
static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
public void addBook() throws android.os.RemoteException;

}
