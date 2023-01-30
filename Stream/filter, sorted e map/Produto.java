package stream.filterESortedEMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class Produto implements Comparable<Produto>{
	
	private Integer idProduto;
	private String nome;
	private Double preco;
	
	@Override
	public int compareTo(Produto p) {
		return this.nome.compareTo(p.getNome());
	}

	public static void main(String[] args) {
		Produto p1 = new Produto(1, "Arroz", 4.50);
		Produto p2 = new Produto(2, "Feijão", 9.8);
		Produto p3 = new Produto(3, "Açucar", 3.90);
		Produto p4 = new Produto(4, "Macarrão", 5.25);
		Produto p5 = new Produto(5, "Frango", 15.50);
		Produto p6 = new Produto(7, "Peixe", 25.00);
		Produto p7 = new Produto(6, "Carne", 55.00);
		
		List<Produto> produtos = Arrays.asList(p1,p2,p3,p4,p5,p6, p7);
		
		/*
		 https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
		 https://www.oracle.com/br/technical-resources/articles/java-stream-api.html
		 
		 Fluxo de processamento de uma stream:
		 filter (Predicate) -> sorted (Comparator) -> map (Function) - > collect 
		
		 stream() - Acessando os elementos.
		 filter() - Filtrando os produtos que correspondem a expressão lambda.
		 sorted() - Ordenando os elementos de forma crescente/descrescente, levando em consideração o valor da operação.
		 forEach() - Iterando a lista imprimindo o resultado.
		 map() - Manipulando o sorted() obtendo as informações desejadas.
		 collect() - Devolvendo uma lista de String que retorna uma nova stream como resultado do processamento.
		*/
		
		//Imprimindo os produtos com preço maior ou igual a 10 em ordem Alfabética.
		
		//Exemplo 1:
		System.out.println("Produtos com preço maior ou igual a 10 em ordem Alfabética");
		produtos.stream()
		.filter(p -> p.getPreco() >= 10.)
		.sorted()
		.forEach(System.out::println);
		
		System.out.println("**-**");
		
		//Exemplo 2:
		System.out.println("Construindo uma nova Stream com base no resultado." +
			produtos.stream()
			.filter(p -> p.getPreco() <= 10.)
			.sorted()
			.map(p -> p.getNome())
			.collect(Collectors.toList())
		);
		
		System.out.println(" ---- ");
		
		//Imprimindo em ordem decrescente
		//Exemplo 3:
		produtos.stream()
		.filter(p -> p.getPreco() >= 10.)
		.sorted(Comparator.reverseOrder())
		.forEach(System.out::println);
		
		System.out.println("**-**");
		
		//Exemplo 4:
		System.out.println(
			produtos.stream()
			.filter(p -> p.getPreco() >= 10.)
			.sorted(Comparator.reverseOrder())
			.map(p -> p.getNome())
			.collect(Collectors.toList())
		);	
	}
	
}
