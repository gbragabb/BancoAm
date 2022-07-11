package br.com.letscode;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Aplicacao {

	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		List<Conta> contas = new ArrayList<Conta>();
		
		Util.limpaConsole(sc);
		System.out.println("Bem-vindo à Aplicação Bancária!");
		System.out.print("Por favor, digite seu nome: ");
		String nome = sc.nextLine().trim();
		
		System.out.println("\nOlá " + nome + "! Vamos abrir sua conta?");
		System.out.println("Digite o caracter referente ao tipo da pessoa: <F>ísica ou <J>urídica");
		char tipoPessoa = sc.nextLine().toLowerCase().charAt(0);
			
		Cliente cliente = null;
		Conta conta = null;
		if (tipoPessoa == 'f') {
			
			System.out.println("Informe CPF completo do Cliente: ");
			String cpf = sc.nextLine().trim();
				
			System.out.print("Informe um telefone para contato:");
			String telefone = sc.nextLine(); 
		
			cliente = new PF(nome, cpf, telefone);
			conta = abrirConta(sc, cliente);		
		}

		if (tipoPessoa == 'j') {
			
			System.out.println("Informe CNPJ completo do Cliente: ");
			String cnpj = sc.nextLine().trim();

			System.out.print("Informe um telefone para contato:");
			String telefone = sc.nextLine(); 
		
			cliente = new PJ(nome, cnpj, telefone);
			conta = abrirConta(sc, cliente);		
		}

		if (conta == null) 
			System.exit(0);

		contas.add(conta);
		Util.limpaConsole(sc);
		//BigDecimal valor = new BigDecimal(0);				
		char operacao = ' ';
		//Laço de Navegação
		while (operacao !='r') {
			//Se pessoa física
			//sc = new Scanner(System.in);
			if (cliente instanceof PF) {
				
				System.out.printf("Cliente Pessoa Física: %s %n", ((PF) cliente).getNome());
				System.out.printf("Conta nº - %02d %n", conta.codigo);
				
				mostraOpcoes(contas.size());
				operacao = sc.nextLine().toLowerCase().charAt(0);
				conta = executaOperacoes(sc, cliente, conta, contas, operacao);
			}
			//Se pessoa jurídica
			else if (cliente instanceof PJ) {
				
				System.out.printf("Cliente Pessoa Jurídica: %s %n", ((PJ) cliente).getRazaoSocial());
				System.out.printf("Conta nº - %02d %n", conta.codigo);
				
				mostraOpcoes(contas.size());
				operacao = sc.nextLine().toLowerCase().charAt(0);
				conta = executaOperacoes(sc, cliente, conta, contas, operacao);
			} //Erro
			else {
	        	System.out.println("Erro ao resgatar tipo de cliente");
	        	break;
			}
			
			
			Util.limpaConsole(sc);
			
		}
		
	sc.close();
		System.out.println("Obrigado por escolher nosso banco! Até mais...");
	}

	private static void mostraOpcoes(int numContas) {

		System.out.println("<A>brir Conta");
		if (numContas > 1)
			System.out.println("<E>scolher Conta");
		System.out.println("<D>epositar");
		System.out.println("<S>acar");
		System.out.println("<T>ransferir");
		System.out.println("<C>onsultar saldo");
		System.out.println("Sai<r>");
	}


	public static BigDecimal defineValor(Scanner sc, String operacao){
		
		System.out.printf("Qual o valor que gostaria de %s?%n", operacao);
		String numero = sc.nextLine().replace(',','.');
		BigDecimal retorno = Double.isNaN(Double.parseDouble(numero)) ? new BigDecimal(0) : new BigDecimal(numero);
		return retorno;
	}

	public static Conta abrirConta(Scanner sc, Cliente cliente) {       

        System.out.println("Qual conta deseja criar?");
		
		if (cliente instanceof PF)
			System.out.println("<C>orrente - <P>oupanca - <I>nvestimento");
		else 
			System.out.println("<C>orrente - <I>nvestimento");
        char tipo = sc.nextLine().toLowerCase().charAt(0);
        
        if (tipo == 'p' && cliente instanceof PF){
            Poupanca novaConta =  new Poupanca(cliente);
			System.out.printf("Conta Poupança nº%d criada para o cliente %s", 
			novaConta.codigo, ((PF)cliente).getNome());
			return novaConta;
		}
        else if (tipo == 'i'){
			Investimento novaConta =  new Investimento(cliente);
			System.out.printf("Conta Investimento nº%d criada para o cliente %s", 
			novaConta.codigo, (cliente instanceof PF)?((PF)cliente).getNome():((PJ)cliente).getRazaoSocial());
			return novaConta;
		}
        else if (tipo == 'c'){
			Corrente novaConta =  new Corrente(cliente);
			System.out.printf("Conta Corrente nº%d criada para o cliente %s", 
			novaConta.codigo, (cliente instanceof PF)?((PF)cliente).getNome():((PJ)cliente).getRazaoSocial());
			return novaConta;
		}else{
			System.out.printf("Erro ao criar Conta... Acesse o sistema novamente!"); 
			return null;
			
		}

    }
	private static Conta executaOperacoes(Scanner sc, Cliente cliente, Conta conta, List<Conta> contas, char opcao) {
	
		BigDecimal valor = new BigDecimal(0);
		BigDecimal saldo = conta.getSaldo();
						
		switch (opcao) {
		
			case 'a':	conta = abrirConta(sc, cliente);
						if (conta == null)
							System.exit(0);
						contas.add(conta);	
						break;

			case 'e':   conta = escolherConta(sc, conta, contas);
						if (conta == null)
							System.exit(0);
						break;

			case 's':	valor = defineValor(sc, "sacar");
						if (valor.intValue() == 0)
							System.out.println("Valor inválido para a operação");
						conta.sacar(valor);
						if (saldo != conta.getSaldo()){
							System.out.printf("Sacado o valor de %s na conta %s %n", 
							Util.formataValores(valor), conta.codigo );
							System.out.print("Saldo: " + Util.formataValores(conta.getSaldo()));
							if(conta instanceof Corrente && conta.getTitular() instanceof PJ) 
								System.out.print("\nTaxa de 5%!");
						}
						break;

			case 'd':	valor = defineValor(sc, "depositar");
						if (valor.intValue() == 0)
							System.out.println("Valor inválido para a operação");
						if (conta instanceof Poupanca)
							((Poupanca) conta).depositar(valor);
						else if (conta instanceof Investimento)
							((Investimento) conta).depositar(valor);
						else 
							conta.depositar(valor);
						if (saldo != conta.getSaldo()){
							System.out.printf("Depositado o valor de %s na conta %s %n", 
							Util.formataValores(valor), conta.codigo );
							System.out.print("Saldo: " + Util.formataValores(conta.getSaldo()));
							if(conta instanceof Poupanca && conta.getTitular() instanceof PF) 
								System.out.print("\nRendimento de 5%!");
							if(conta instanceof Investimento){
								if(conta.getTitular() instanceof PF) 
									System.out.print("\nRendimento de 1%!");
								else
									System.out.print("\nRendimento de 3%!");
							}
						}
						break;
								
			case 't':	if (contas.size() > 1){

							Conta contaDestino = escolherConta(sc, conta, contas);
							valor = defineValor(sc, "transferir");
							
							if (valor.intValue() == 0){

								System.out.println("Valor inválido para a operação");
							} else if ( valor.doubleValue() > 0){

								conta.transferir(valor ,contaDestino);
								System.out.printf("Transferido o valor de %s para a conta %s %n", 
								Util.formataValores(valor), contaDestino.codigo );
								System.out.print("Saldo: " + Util.formataValores(conta.getSaldo()));
								if(conta instanceof Corrente && conta.getTitular() instanceof PJ) 
							
									System.out.print("\nTaxa de 5%!");
							} 
						} else 

							System.out.print("\nPara realizar transferências é necessário abrir outra conta!");
						break;

			case 'c':	System.out.println(conta.consultarSaldo());
						break;
			case 'r':   break;
			default: 	System.out.printf("Erro ao definir opção! %s", String.valueOf(opcao));
						break;
		}
		return conta; 					
	}

	private static Conta escolherConta(Scanner sc2, Conta conta, List<Conta> contas) {
		
		
		System.out.println("Qual conta você quer?");
		String frase = "";
		for (int item = 0; item < contas.size(); item++){

			if (!contas.get(item).equals(conta)){

				if (contas.get(item) instanceof Poupanca)
					frase += "<P>oupanca - ";
				if (contas.get(item) instanceof Investimento)
					frase += "<I>nvestimento - ";
				if (contas.get(item) instanceof Corrente)
					frase += "<C>orrente - ";
			} else 
				contas.set(item,conta);
		}
		
		System.out.println(frase.substring(0,(frase.length()-3)));
		char tipo = sc.nextLine().toLowerCase().charAt(0);
		conta = null;
			
		for (Conta item: contas){

			if (tipo == 'p' && item instanceof Poupanca)
				conta = item;
			if (tipo == 'i' && item instanceof Investimento)
				conta = item;
			if (tipo == 'c' && item instanceof Corrente)
				conta = item;
		}
		
		if (conta == null)
			System.out.printf("Erro ao escolher Conta... Acesse o sistema novamente!"); 
		return conta;
	}
}
