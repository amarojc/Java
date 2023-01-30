package stream.collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
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
		
		/*
		 	Referência:
		 	https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-
		 	
			O método groupingBy() da classe Collectors em Java é usado para agrupar objetos 
			por alguma propriedade e armazenar resultados em uma instância de Mapa. 
			Para usá-lo, sempre precisamos especificar uma propriedade pela qual 
			o agrupamento seria realizado. 
			Este método fornece funcionalidade semelhante à cláusula GROUP BY do SQL.
			
			Estrutura do Map:
			public static Collector<T,?,Map<K,List>> groupingBy(Classificador de função)
			
			T -> É o tipo dos elementos de entrada
			K -> É o tipo dos elementos de entrada a serem convertidos.
			
			parâmentro: Aceita dos parâmetros obrigatórios:
			Função: É a propriedade que deve ser aplicada aos elementos de entrada.
			Classificador: é usado para mapear os elementos de entrda no mapa de destino.
			
			Valor retorno -> Retorna um coletor como um mapa.
		*/
		Map<Object, List<Pessoa>> agrupar = lista.stream()
				.collect(Collectors.groupingBy(p -> p.getSexo()));
		
		agrupar.forEach((sexo, p) -> System.out.format("Sexo %s: %s\n", sexo, p));
		
		Map<Character, Long> grupo = lista.stream()
				.map(p -> p.getSexo())
				.collect(Collectors
							.groupingBy(
								Function.identity(),Collectors.counting()
							)
						 );
		
		System.out.println("Quantidade por grupo: " + grupo);
		
	}
}
