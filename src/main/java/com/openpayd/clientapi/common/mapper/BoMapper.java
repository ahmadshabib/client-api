package com.openpayd.clientapi.common.mapper;

import com.openpayd.clientapi.common.model.BusinessObject;

import java.util.List;

/**
 * Interface for the generic mapper between Json objects and Bom objects
 *
 * @author ahmad.shabib
 * @param <A> any object
 * @param <B> Bom object
 */
public interface BoMapper<A, B extends BusinessObject> extends GeneralBoMapper<A, B> {

  /**
   * Transforms a Bom object to any supported object
   *
   * @param object Bom object to transform
   * @return Bom object mapped onto implemented A type object
   */
  A convert(B object);

  /**
   * Transforms a List<Bom> object to any supported object
   *
   * @param bList of object Bom object to transform
   * @return List of Bom object mapped onto implemented A type object
   */
  List<A> convertToObjectList(List<B> bList);
}
