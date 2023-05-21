package com.myunidays.klipper.flipper.testing

import com.myunidays.klipper.flipper.core.FlipperArray
import com.myunidays.klipper.flipper.core.FlipperConnection
import com.myunidays.klipper.flipper.core.FlipperObject
import com.myunidays.klipper.flipper.core.FlipperReceiver

class FlipperConnectionMock : FlipperConnection {
    val receivers: MutableMap<String, FlipperReceiver> = mutableMapOf()
    val sent: MutableMap<String, List<Any>> = mutableMapOf()
    val errors: MutableList<Throwable> = arrayListOf()
    override fun send(method: String, params: FlipperObject) {
        val paramList: MutableList<Any>
        if (sent.containsKey(method)) {
            paramList = sent[method]!!.toMutableList()
        } else {
            paramList = ArrayList()
            sent[method] = paramList
        }
        paramList.add(params)
    }

    override fun send(method: String, params: FlipperArray) {
        val paramList: MutableList<Any>
        if (sent.containsKey(method)) {
            paramList = sent[method]!!.toMutableList()
        } else {
            paramList = ArrayList()
            sent[method] = paramList
        }
        paramList.add(params)
    }

    override fun send(method: String, message: String) {
        val paramList: MutableList<Any>
        if (sent.containsKey(method)) {
            paramList = sent[method]!!.toMutableList()
        } else {
            paramList = ArrayList()
            sent[method] = paramList
        }
        paramList.add(message)
    }

    override fun reportErrorWithMetadata(reason: String, stackTrace: String) {
        errors.add(Throwable(reason))
    }

    override fun reportError(throwable: Throwable) {
        errors.add(throwable)
    }

    override fun receive(method: String, receiver: FlipperReceiver) {
        receivers[method] = receiver
    }
}

//public class FlipperConnectionMock implements FlipperConnection {
//  public final Map<String, FlipperReceiver> receivers = new HashMap<>();
//  public final Map<String, List<Object>> sent = new HashMap<>();
//  public final List<Throwable> errors = new ArrayList<>();
//
//  @Override
//  public void send(String method, FlipperObject params) {
//    final List<Object> paramList;
//    if (sent.containsKey(method)) {
//      paramList = sent.get(method);
//    } else {
//      paramList = new ArrayList<>();
//      sent.put(method, paramList);
//    }
//
//    paramList.add(params);
//  }
//
//  @Override
//  public void send(String method, FlipperArray params) {
//    final List<Object> paramList;
//    if (sent.containsKey(method)) {
//      paramList = sent.get(method);
//    } else {
//      paramList = new ArrayList<>();
//      sent.put(method, paramList);
//    }
//
//    paramList.add(params);
//  }
//
//  @Override
//  public void send(String method, String params) {
//    final List<Object> paramList;
//    if (sent.containsKey(method)) {
//      paramList = sent.get(method);
//    } else {
//      paramList = new ArrayList<>();
//      sent.put(method, paramList);
//    }
//
//    paramList.add(params);
//  }
//
//  @Override
//  public void reportErrorWithMetadata(String reason, String stackTrace) {
//    errors.add(new Throwable(reason));
//  }
//
//  @Override
//  public void reportError(Throwable throwable) {
//    errors.add(throwable);
//  }
//
//  @Override
//  public void receive(String method, FlipperReceiver receiver) {
//    receivers.put(method, receiver);
//  }
//}
