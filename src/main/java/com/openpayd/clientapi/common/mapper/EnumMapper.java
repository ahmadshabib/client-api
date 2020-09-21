package com.openpayd.clientapi.common.mapper;

/**
 * converts one enum value in the value of another enum which has the same name
 *
 * @author ahmad.shabib
 */
final class EnumMapper {

  /** constructor */
  private EnumMapper() {}

  /**
   * converts one enum value in the value of another enum which has the same name
   *
   * <p>to retain generality (so to be able to use this method with all enumerations) we are calling
   * static 'valueOf' method from an instance and not from in a static way. Thus, if P enum defines
   * an instance scoped 'valueOf' method it can leads to errors
   *
   * @param object enum to be converted
   * @param mappedClass enum converted
   * @return
   */
  @SuppressWarnings("static-access")
  public static <T extends Enum<T>, P extends Enum<P>> T convert(P object, Class<T> mappedClass) {
    if (object != null) {
      try {
        return object.valueOf(mappedClass, object.name());
      } catch (IllegalArgumentException e) {
        //        LOGGER.info(e.getLocalizedMessage(), e);
      }
    }
    return null;
  }
}
