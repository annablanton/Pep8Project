package model;

public interface Convertible {
	Number convert(Binary x);

	Number convert(Hex x);

	Number convert(Decimal x);
}
