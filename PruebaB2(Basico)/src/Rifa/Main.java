package Rifa;

import java.util.*;

public class Main {
	static List<Articulo> articulos = List.of(
		new Articulo("JO-001", "Chocolate Amargo", "dulce", 500),
		new Articulo("JO-002", "Gomitas", "dulce", 300),
		new Articulo("JO-003", "Caramelo", "dulce", 200),
		new Articulo("JO-004", "Chicle Menta", "dulce", 100),
		new Articulo("JO-005", "Agua Mineral", "bebida", 1600),
		new Articulo("JO-006", "Papas Fritas", "snack", 1200),
		new Articulo("JO-007", "Gaseosa", "bebida", 2500),
		new Articulo("JO-008", "Maní Salado", "snack", 500)
	);

	static List<Registro> registros = List.of(
		new Registro("Juan Pérez", "2024-10-01", List.of(new Compra("JO-001", 4), new Compra("JO-004", 5))),
		new Registro("Ana López", "2024-10-02", List.of(new Compra("JO-003", 2), new Compra("JO-005", 3))),
		new Registro(null, "2024-10-03", List.of(new Compra("JO-003", 5), new Compra("JO-001", 6))),
		new Registro("Alejandro Diaz", "2024-10-07", List.of(new Compra("JO-005", 3), new Compra("JO-006", 6)))
	);

	static Articulo buscarArticulo(String codigo) {
		return articulos.stream().filter(a -> a.codigo.equals(codigo)).findFirst().orElse(null);
	}

	static void mejorCompradorPorDia() {
		Map<String, Integer> maximosPorDia = new HashMap<>();
		Map<String, String> mejoresPorDia = new HashMap<>();

		registros.stream().filter(r -> r.nombre != null).forEach(registro -> {
			int total = registro.compras.stream().mapToInt(compra -> {
				Articulo articulo = buscarArticulo(compra.codigo);
				return (articulo != null && articulo.tipo.equals("dulce")) ? articulo.valor * compra.cantidad : 0;
			}).sum();

			String fecha = registro.fecha;
			if (total > maximosPorDia.getOrDefault(fecha, 0)) {
				maximosPorDia.put(fecha, total);
				mejoresPorDia.put(fecha, registro.nombre);
			}
		});
		System.out.println("Los mejores compradores por día son: " + mejoresPorDia);
	}

	static void dulceMasComprado() {
		Map<String, Integer> conteoDulces = new HashMap<>();

		registros.forEach(registro -> registro.compras.forEach(compra -> {
			Articulo articulo = buscarArticulo(compra.codigo);
			if (articulo != null && articulo.tipo.equals("dulce")) {
				conteoDulces.put(articulo.nombre, conteoDulces.getOrDefault(articulo.nombre, 0) + compra.cantidad);
			}
		}));

		String dulceMasComprado = conteoDulces.entrySet().stream()
			.max(Map.Entry.comparingByValue())
			.map(Map.Entry::getKey)
			.orElse(null);

		System.out.println("Dulce más comprado es: " + dulceMasComprado);
	}

	public static void main(String[] args) {
		mejorCompradorPorDia();
		dulceMasComprado();
	}
}

