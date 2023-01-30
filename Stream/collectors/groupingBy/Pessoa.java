package stream.collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pessoa {
	
	private Integer id;
	private String nome;
	private Character sexo;
	
	
	public static void main(String[] args) {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		lista.add(new Pessoa(1, "João", 'M'));
		lista.add(new Pessoa(2, "Maria", 'F'));
		lista.add(new Pessoa(3, "José", 'M'));
		lista.add(new Pessoa(4, "Tatiana", 'F'));
		lista.add(new Pessoa(5, "Logan", 'M'));
		lista.add(new Pessoa(6, "Angela", 'F'));
		
		Map<Object, List<Pessoa>> agrupar = lista.stream()
				.collect(Collectors.groupingBy(p -> p.getSexo()));
		
		agrupar.forEach((sexo, p) -> System.out.format("Sexo %s: %s\n", sexo, p));
		
	}
}
