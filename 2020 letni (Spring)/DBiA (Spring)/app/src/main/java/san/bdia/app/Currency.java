package san.bdia.app;

public enum Currency {

  USD(1), PLN(4.2), EUR(1.1025);

  public final double usdRatio;

  Currency(double usdRatio) {
    this.usdRatio = usdRatio;
  }

}
