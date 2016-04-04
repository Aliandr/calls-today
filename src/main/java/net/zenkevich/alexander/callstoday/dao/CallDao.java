package net.zenkevich.alexander.callstoday.dao;

import net.zenkevich.alexander.callstoday.model.Call;

/**
 * Call dao interface
 */
public interface CallDao {

  /**
   * Saves Call
   *
   * @param call object to save
   */
  void add(Call call);

}
