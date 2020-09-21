package com.openpayd.clientapi.common.mapper;

import com.openpayd.clientapi.common.model.BusinessObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for mappers, implementing common behaviours
 *
 * @author ahmad.shabib
 * @param <A>
 * @param <B>
 */
public abstract class AbstractBoMapper<A, B extends BusinessObject> implements BoMapper<A, B> {

  @Override
  public List<B> convertToBoList(final List<A> aList) {
    final List<B> bList = new ArrayList<>();
    if (aList == null) {
      return bList;
    }
    for (final A a : aList) {
      bList.add(convert(a));
    }
    return bList;
  }

  @Override
  public List<A> convertToObjectList(final List<B> bList) {
    final List<A> aList = new ArrayList<>();
    if (bList == null) {
      return aList;
    }
    for (final B b : bList) {
      aList.add(convert(b));
    }
    return aList;
  }

  public <T extends Enum<T>, P extends Enum<P>> T convert(P object, Class<T> mappedClass) {
    return EnumMapper.convert(object, mappedClass);
  }
}
