package san.messaging;

public interface IPump {

  void send(Object message, Object source);

  void register(Target target, Class... messageTypes);

}
