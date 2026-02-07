package com.uisrael.karateika_frontend.modelo.enums;

public enum Cinturon {
	BLANCO_10MO_KYU("BLANCO - 10mo. KYU"),
	BLANCO_AMARILLO_9NO_KYU("BLANCO AMARILLO - 9no. KYU"),
	AMARILLO_8VO_KYU("AMARILLO - 8vo. KYU"),
	NARANJA_7MO_KYU("NARANJA - 7mo. KYU"),
	VERDE_6TO_KYU("VERDE - 6to. KYU"),
	AZUL_5TO_KYU("AZUL - 5to. KYU"),
	PURPURA_4TO_KYU("PÚRPURA - 4to. KYU"),
	CAFE_3ER_KYU("CAFÉ - 3er. KYU"),
	CAFE_2DO_KYU("CAFÉ - 2do. KYU"),
	CAFE_1ER_KYU("CAFÉ - 1er. KYU"),
	NEGRO_1ER_DAN("NEGRO - 1er. DAN"),
	NEGRO_2DO_DAN("NEGRO - 2do. DAN"),
	NEGRO_3ER_DAN("NEGRO - 3er. DAN"),
	NEGRO_4TO_DAN("NEGRO - 4to. DAN"),
	NEGRO_5TO_DAN("NEGRO - 5to. DAN"),
	NEGRO_6TO_DAN("NEGRO - 6to. DAN"),
	NEGRO_7MO_DAN("NEGRO - 7mo. DAN"),
	NEGRO_8VO_DAN("NEGRO - 8vo. DAN"),
	NEGRO_9NO_DAN("NEGRO - 9no. DAN"),
	NEGRO_10MO_DAN("NEGRO - 10mo. DAN");

	private final String nombre;

	Cinturon(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
