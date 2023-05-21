package com.myunidays.klipper.flipper.testing

import com.myunidays.klipper.flipper.core.FlipperArray
import com.myunidays.klipper.flipper.core.FlipperObject
import com.myunidays.klipper.flipper.core.FlipperResponder

class FlipperResponderMock : FlipperResponder {
    val successes: MutableList<Any> = mutableListOf()
    val errors: MutableList<Any> = mutableListOf()

    override fun success(response: FlipperObject) {
        successes.add(response)
    }

    override fun success(response: FlipperArray) {
        successes.add(response)
    }

    override fun success() {
        successes.add(FlipperObject(""))
    }

    override fun error(response: FlipperObject) {
        errors.add(response)
    }
}

//public class FlipperResponderMock implements FlipperResponder {
//  public final List<Object> successes = new LinkedList<>();
//  public final List<FlipperObject> errors = new LinkedList<>();
//
//  @Override
//  public void success(FlipperObject response) {
//    successes.add(response);
//  }
//
//  @Override
//  public void success(FlipperArray response) {
//    successes.add(response);
//  }
//
//  @Override
//  public void success() {
//    successes.add(new FlipperObject.Builder().build());
//  }
//
//  @Override
//  public void error(FlipperObject response) {
//    errors.add(response);
//  }
//}
