package org.rif.lumino.explorer.models.enums;

public enum ChannelState {
  Opened("opened"),
  Closed("closed"),
  WaitingForClose("waiting_for_close"),
  Settled("settled");

  private String channelState;

  ChannelState(String channelState) {
    this.channelState = channelState;
  }
}
