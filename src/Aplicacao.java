import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Bem-vindo à Aplicação Bancária!");
		System.out.print("Por favor, digite seu nome: ");
		String nome = sc.nextLine().trim();
		
		System.out.println("\nOlá " + nome + "! Vamos abrir sua conta?");
		System.out.println("Digite o caracter referente ao tipo da pessoa: <F>ísica ou <J>urídica");
		char tipoPessoa = sc.nextLine().charAt(0);
		
		Cliente cliente = null;
		Conta conta = null;
		if (tipoPessoa == 'F') {
			
			System.out.println("Informe CPF completo do Cliente: ");
			String cpf = sc.nextLine().trim();

			System.out.print("Informe um telefone para contato:");
			String telefone = sc.nextLine(); 

			cliente = new PF(nome, cpf, telefone);
			conta = ((PF) cliente).abrirConta();		
		}

		if (tipoPessoa == 'J') {
			
			System.out.println("Informe CNPJ completo do Cliente: ");
			String cnpj = sc.nextLine().trim();

			System.out.print("Informe um telefone para contato:");
			String telefone = sc.nextLine(); 

			cliente = new PJ(nome, cnpj, telefone);
			conta = ((PJ) cliente).abrirConta();		
		}
						
		char opcao = ' ';
		//Laço de Navegação
		while (opcao != 'r') {
			//Se pessoa física
			if (cliente instanceof PF) {
				
				System.out.printf("Cliente Pessoa Física: %s", ((PF) cliente).getNome());
					
				if (conta instanceof Corrente) {
					
					System.out.println("<A>brir Conta");
					System.out.println("<D>epositar");
					System.out.println("<S>acar");
					System.out.println("<T>ransferir");
					System.out.println("<C>onsultar saldo");
					System.out.println("Sai<r>");
					opcao = sc.nextLine().toLowerCase().charAt(0);
				
					switch (opcao) {

						case 'a':	conta = ((PF) cliente).abrirConta();
				    				break;
				    	case 's':	//((Corrente) conta).sacar();
		            				break;
				    	case 'd':	//((Corrente) conta).depositar();
	            					break;
				    	case 't':	//((Corrente) conta).transferir();
									break;
				    	case 'c':	((Corrente) conta).consultarSaldo();
									break;
				    	case 'r':   break;
				    	default: 	System.out.println("Erro ao definir opção!");
				    				break;
					} 
				}else if (conta instanceof Poupanca){
					
					System.out.println("Conta Poupança - Cliente Pessoa Física:");
					System.out.println("<D>epositar");
					System.out.println("<S>acar");
					System.out.println("<T>ransferir");
					System.out.println("<C>onsultar saldo");
					System.out.println("Sai<r>");
					opcao = sc.nextLine().charAt(0);
				
					switch (Character.toLowerCase(opcao)) {
						
				    	case 's':	//((Poupanca) conta).sacar();
		            				break;
				    	case 'd':	//((Poupanca) conta).depositar();
	            					break;
				    	case 't':	//((Poupanca) conta).transferir();
									break;
				    	case 'c':	//((Poupanca) conta).consultarSaldo();
									break;
				    	case 'r':   break;
				    	default: 	System.out.println("Erro ao definir opção!");
				    				break;
					}
				} else if (conta instanceof Investimento){

					System.out.println("Conta Investimento - Cliente Pessoa Física:");
					System.out.println("<D>epositar");
					System.out.println("<S>acar");
					System.out.println("<T>ransferir");
					System.out.println("<C>onsultar saldo");
					System.out.println("Sai<r>");opcao = sc.nextLine().charAt(0);
				
					switch (Character.toLowerCase(opcao)) {

				    	case 's':	//((Investimento) conta).sacar();
		            				break;
				    	case 'd':	//((Investimento) conta).depositar();
	            					break;
				    	case 't':	//((Investimento) conta).transferir();
									break;
				    	case 'c':	((Investimento) conta).consultarSaldo();
									break;
				    	case 'r':   break;
				    	default: 	System.out.println("Erro ao definir opção!");
				    				break;
					} 					
				}
			//Se pessoa jurídica
			}else if (cliente instanceof PJ){
				
				if (conta instanceof Corrente) {

					System.out.println("Conta Corrente - Cliente Pessoa Jurídica:");
					System.out.println("Sai<r>");
					opcao = sc.nextLine().charAt(0);
				
					switch (Character.toLowerCase(opcao)) {

						case 'a':	//((Corrente) conta).abrirConta();
				    				break;
				    	case 's':	//((Corrente) conta).sacar(); //taxa 0,5%
		            				break;
				    	case 'd':	//((Corrente) conta).depositar();
	            					break;
				    	case 't':	//((Corrente) conta).transferir(); //taxa 0,5%
									break;
				    	case 'c':	((Corrente) conta).consultarSaldo();
									break;
				    	case 'r':   break;
				    	default: 	System.out.println("Erro ao definir opção!");
				    				break;
					}
				}else if (conta instanceof  Investimento){

					System.out.println("Conta Investimento - Cliente Pessoa Jurídica:");
					System.out.println("Sai<r>");
					opcao = sc.nextLine().charAt(0);
				
					switch (Character.toLowerCase(opcao)) {

				    	case 's':	//((Investimento) conta).sacar();
		            				break;
				    	case 'd':	//((Investimento) conta).depositar();
	            					break;
				    	case 't':	//((Investimento) conta).transferir();
									break;
				    	case 'c':	((Investimento) conta).consultarSaldo();
									break;
				    	case 'r':   break;
				    	default: 	System.out.println("Erro ao definir opção!");
				    				break;
					} 					
				}
			//Erro
			} else {
	        	System.out.println("Erro ao resgatar tipo de cliente");
	        	break;
			}
			
			if (opcao!='r')
				System.out.println("Digite o caracter referente operação desejada:");
		}
		sc.close();
		System.out.println("Obrigado por escolher nosso banco! Até mais...");
	}
}
