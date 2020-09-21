package com.openpayd.clientapi.common.mapper;

import com.openpayd.clientapi.common.model.BusinessObject;

import java.util.List;

/**
 * Interface for the generic mapper between Json objects and Bom objects
 * contains the common methods to convert Json to Bom
 * is extended for converting Bom to Json by specialized interface
 *
 *
 * @author ahmad.shabib
 *
 * @param <A> any object
 * @param <B> Bom object
 */
public interface GeneralBoMapper<A, B extends BusinessObject> {

  /**
   * Transforms implemented A type object to a Bom object
   * @param object implemented A type object to transform
   * @return implemented A type object mapped onto a Bom object
   */
  B convert(A object);

  /**
   * Transforms implemented List<A> type object to a List<Bom> object
   * @param aList of object implemented A type object to transform
   * @return list of implemented A type object mapped onto a Bom object
   */
  List<B> convertToBoList(List<A> aList);

}