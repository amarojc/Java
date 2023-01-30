package predicate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Aluno {

	private Integer idAluno;
	private String nome;
	private Double nota1;
	private Double nota2;
	
	private transient Double media;
	private transient String situacaoAluno;
	
		
	private static Double mediaAluno(Aluno a) {
		a.setMedia((a.getNota1() + a.getNota2())/2);
	  return a.getMedia();
	}
	
	private static void pred(List<Aluno> alunos, Predicate<Aluno> pred) {
		alunos.stream().filter(pred).map(a -> a).forEach(System.out::println);
	}
	
	private static Boolean resultadoAlunos(Aluno aluno) {
		Double media = mediaAluno(aluno);		
		if(media < 5) {
			aluno.setSituacaoAluno("Reprovado");
			return true;
		}else if(media >= 5 && media < 7) {
			aluno.setSituacaoAluno("Recuperação");
			return true;
		}
		aluno.setSituacaoAluno("Aprovado");
		return true;
	}
	
	public static void main(String[] args) {
				
		Aluno aluno1 = new Aluno(1, "João", 7.5, 8.8, 0.,"");
		Aluno aluno2 = new Aluno(2, "Maria", 8.5, 9., 0.,"");
		Aluno aluno3 = new Aluno(3, "José", 4.5, 10., 0.,"");
		Aluno aluno4 = new Aluno(4, "Sílvio", 6.0, 7.8, 0.,"");
		Aluno aluno5 = new Aluno(5, "Logan", 9.5, 5.0, 0.,"");
		Aluno aluno6 = new Aluno(6, "Ivo", 4.5, 5.0, 0.,"");
		
		List<Aluno> alunos = Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6);
		
		
		/*
		 https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
		 
		 Predicate<T> - É uma interface funcional, podendo ser usada como destino de atribuição para uma expresão lambda
		 ou como uma referência de método.
		 
		 T - Tipo de entrada para o predicado.
		 
		 Retorna um booleano no saída.
		 
		 */
		

		Predicate<Aluno> aprovados = a ->{
			return mediaAluno(a) >= 7;
		};
		
		Predicate<Aluno> recuperacao = a ->{
			return mediaAluno(a) >= 5 && mediaAluno(a) < 7;
		};
		
		Predicate<Aluno> reprovados = a ->{
			return mediaAluno(a) < 5;
		};
		
		Predicate<Aluno> situacao = aluno ->{
			return resultadoAlunos(aluno);
		};

		//Imprimindo os resultado de todos os alunos chamando o método pred criado
		System.out.println("TODOS RESULTADOS");
		pred(alunos, situacao);
		
				
		//Imprimindo somente os alunos aprovados com base na regra do Predicate
		System.out.println();
		System.out.println("*** APROVADOS ***");
		alunos.stream().filter(aprovados).forEach(System.out::println);
		
		
		//Imprimindo somente os alunos em recuperação com base na regra do Predicate
		System.out.println();
		System.out.println("*** EM RECUPERAÇÃO ***");
		alunos.stream().filter(recuperacao).forEach(System.out::println);

		
		//Imprimindo somente os alunos reprovados com base na regra do Predicate
		System.out.println();
		System.out.println("*** REPROVADOS ***");
		alunos.stream().filter(reprovados).forEach(System.out::println);
		
		
		/* SAIDA:
		
		TODOS RESULTADOS
		Aluno(idAluno=1, nome=João, nota1=7.5, nota2=8.8, media=8.15, situacaoAluno=Aprovado)
		Aluno(idAluno=2, nome=Maria, nota1=8.5, nota2=9.0, media=8.75, situacaoAluno=Aprovado)
		Aluno(idAluno=3, nome=José, nota1=4.5, nota2=10.0, media=7.25, situacaoAluno=Aprovado)
		Aluno(idAluno=4, nome=Sílvio, nota1=6.0, nota2=7.8, media=6.9, situacaoAluno=Recuperação)
		Aluno(idAluno=5, nome=Logan, nota1=9.5, nota2=5.0, media=7.25, situacaoAluno=Aprovado)
		Aluno(idAluno=6, nome=Ivo, nota1=4.5, nota2=5.0, media=4.75, situacaoAluno=Reprovado)

		*** APROVADOS ***
		Aluno(idAluno=1, nome=João, nota1=7.5, nota2=8.8, media=8.15, situacaoAluno=Aprovado)
		Aluno(idAluno=2, nome=Maria, nota1=8.5, nota2=9.0, media=8.75, situacaoAluno=Aprovado)
		Aluno(idAluno=3, nome=José, nota1=4.5, nota2=10.0, media=7.25, situacaoAluno=Aprovado)
		Aluno(idAluno=5, nome=Logan, nota1=9.5, nota2=5.0, media=7.25, situacaoAluno=Aprovado)

		*** EM RECUPERAÇÃO ***
		Aluno(idAluno=4, nome=Sílvio, nota1=6.0, nota2=7.8, media=6.9, situacaoAluno=Recuperação)

		*** REPROVADOS ***
		Aluno(idAluno=6, nome=Ivo, nota1=4.5, nota2=5.0, media=4.75, situacaoAluno=Reprovado)

		*/
	}
	
}
