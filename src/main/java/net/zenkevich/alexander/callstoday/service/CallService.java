package net.zenkevich.alexander.callstoday.service;

import net.zenkevich.alexander.callstoday.dao.CallDao;
import net.zenkevich.alexander.callstoday.model.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Call service
 */
@Service
public class CallService {

  @Autowired
  private PhoneNumberNormalizer normalizer;
  @Autowired
  private CallDao repository;


  /**
   * Normalize phone number and save to repository.
   *
   * @param call
   * @return call
   */
  public Call add(Call call) {
    String normalizedPhoneNumber = normalizer.normalize(call.getTelephone());
    call.setTelephone(normalizedPhoneNumber);
    repository.add(call);
    return call;
  }
}
