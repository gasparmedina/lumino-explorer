package org.rif.lumino.explorer.models;

import org.web3j.abi.datatypes.Type;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class EventData {

  private List<Type> values;
  private BigInteger blockNumber;

  private String eventName;

  private String contractAddress;

  public EventData(
      String eventName, List<Type> values, BigInteger blockNumber, String contractAddress) {
    this.eventName = eventName;
    this.values = values;
    this.blockNumber = blockNumber;
    this.contractAddress = contractAddress;
  }

  public List<Type> getValues() {
    return values;
  }

  public BigInteger getBlockNumber() {
    return blockNumber;
  }

  public String getEventName() {
    return eventName;
  }

  public String getContractAddress() {
    return contractAddress;
  }

  @Override
  public String toString() {
    return "EventData{"
        + "values="
        + values.toString()
        + ", blockNumber="
        + blockNumber
        + ", eventName="
        + eventName
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EventData eventData = (EventData) o;
    // compares by channel id
    return Objects.equals(values.get(0), eventData.values.get(0))
        && Objects.equals(eventName, eventData.eventName)
        && Objects.equals(contractAddress, eventData.contractAddress);
  }

  @Override
  public int hashCode() {
    // channel id hash
    return Objects.hash(values.get(0), contractAddress, eventName) * 17;
  }
}
