package stream.collectors.reduce;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Produto {

	private Integer id;
	private String nome;
	private Double preco;
	private Integer estoque;
	
	public static void main(String[] args) {
		
	  List<Produto> lista = new ArrayList<Produto>();  
    
		  lista.add(new Produto(1,"HP Laptop",2500.89, 256));  
		  lista.add(new Produto(2,"Dell Laptop",3000.98, 320));  
		  lista.add(new Produto(3,"Lenevo Laptop",2899.32, 400));  
		  lista.add(new Produto(4,"Sony Laptop",2899.90, 650));  
		  lista.add(new Produto(5,"Apple Laptop",9990.99, 56));
		  
		  /*
		  Refer�ncia:
		  https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
		   
		   reduce - Utiliza os seguintes conceitos:
		   Identidade: Um elemento que � o valor inicial da opera��o e o
		   resultado padr�o se o fluxo estiver vazio.
		   
		   Acumulador: A fun��o do acumulador aceita dois param�tros: um � o resultado
		   parcial da opera��o de redu��o e o pr�ximo elemento do fluxo.
		   
		   Combinador: Uma fun��o usada para combinar o resultado parcial da opera��o de redu��o,
		   quando a redu��o � paralelizada ou quando h� uma incompatibilidade entre os tipos de argumentos
		   do acumulador e os tipos de implementa��o do acumulador.
		   
		   */
	
		  Double total = 0.;
		  //Forma compacta...
		  // 0 = identidade | soma = subtotal/acumulador | preco = elemento combinador
		  total = lista.stream()
				  .map(produto -> produto.getPreco())
				  .reduce(0.,(soma, preco) -> soma + preco);//Somando/acumulando o preco
		  
		  System.out.format("Exemplo 1 -> Total: %.2f %n%n" , total);
		  
		  //Utilizando lambda
		  total = lista.stream()
				  .map(produto -> produto.getPreco())
				  .reduce(0.,Double::sum);//Acumulando o preco e referenciando o m�todo da clase Double
		  
		  System.out.format("Exemplo 2 -> Total: %.2f %n%n", total);
		  
		  //Utilizando reduce com Integer
		  Integer estoque = lista.stream()
				  	.map(produto -> produto.getEstoque())
				  	.reduce(0,Integer::sum);
		  
		  System.out.format("Exemplo 3 -> Total geral de produtos no estoque: %d %n%n", estoque);
		  
		  
		 //Utilizando reduce com String
		  
		  String produtos = lista.stream()
				  .map(produto -> produto.getNome() + " | ")
				  .reduce("", String::concat);
				  
		  System.out.format("Exemplo 4 -> Produtos: %s", produtos);
	}
	
}
