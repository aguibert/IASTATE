// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `Printer.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Demo;

public interface PrinterPrx extends Ice.ObjectPrx
{
    public void printString(String s);

    public void printString(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_printString(String s);

    public Ice.AsyncResult begin_printString(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_printString(String s, Ice.Callback __cb);

    public Ice.AsyncResult begin_printString(String s, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_printString(String s, Callback_Printer_printString __cb);

    public Ice.AsyncResult begin_printString(String s, java.util.Map<String, String> __ctx, Callback_Printer_printString __cb);

    public void end_printString(Ice.AsyncResult __result);
}