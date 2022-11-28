package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Profile> profiles = new ArrayList<>();
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("escolha uma opção:");
			System.out.println("\"C\" para cadastrar-se");
			System.out.println("\"E\" para entrar");
			System.out.println("\"F\" para fechar");
			
			Scanner scanner = new Scanner(System.in);
			char option = scanner.next().charAt(0);
			
			switch (option) {
				case 'C':
					registerProfile();
					break;
				case 'E':
					Profile profile;
					try {
						profile = login();
					} catch (UserNotFoundException e) {
						System.out.println("não há usuário com o nome informado");
						System.out.println("==========");
						continue;
					} catch (InvalidPasswordException e) {
						System.out.println("senha não corresponde à informada");
						System.out.println("==========");
						continue;
					}				
					UserMenu(profile);
					break;
				case 'F':
					System.out.println("até mais");
					return;
				default:
					System.out.println("opção inválida");
					System.out.println("==========");
			}		
		}		
	}
	
	private static void registerProfile() {
		String name = null;
		String username = null;
		String password = null;
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("----------");
			System.out.println("por favos informe seus dados:");
			System.out.print("nome: ");
			name = scanner.nextLine();
			if (name.isEmpty()) {
				throw new EmptyNameException();
			}
			
			System.out.print("login: ");
			username = scanner.nextLine();
			if (username.isEmpty()) {
				throw new EmptyUsernameException();
			}
			final String auxUsername = username;
			if (profiles.stream().filter(p -> p.getUsername().equals(auxUsername)).count() > 0) {          //map(p -> p.getUsername()).anyMatch(u -> u.equals(username))) {
				throw new UsernameNotAvailableException();
			}
			
			System.out.print("senha: ");
			password = scanner.nextLine();
			if (password.isEmpty()) {
				throw new EmptyPasswordException();
			}
		} catch (EmptyNameException e) {
			System.out.println("o nome não pode ser vazio, tente novamente");
			System.out.println("==========");
			return;
		} catch (EmptyUsernameException e) {
			System.out.println("o login não pode ser vazio, tente novamente");
			System.out.println("==========");
			return;
		} catch (UsernameNotAvailableException e) {
			System.out.println("o login escolhido já está sendo usado, tente novamente");
			System.out.println("==========");
			return;
		} catch (EmptyPasswordException e) {
			System.out.println("a senha não pode ser vazia, tente novamente");
			System.out.println("==========");
			return;
		}

		profiles.add(new Profile(name, username, password));
		System.out.println("cadastro realizado com sucesso");
		System.out.println("==========");
	}
	
	private static Profile login() {
		if (profiles.isEmpty()) {
			System.out.println("Não há nenhum usuário cadastrado. Por favor efetue o cadastro antes de continuar.");
			return null; // mover isso pra fora de login
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("----------");
		System.out.print("digite seu login: ");
		String username = scanner.nextLine();
		
		Profile profile = getProfileFromUsername(username);
		if (profile == null) {
			throw new UserNotFoundException();
		}
		
//		try {
//			profile = getProfileFromUsername(username);
//		} catch (UserNotFoundException e) {
//			System.out.println("não há usuário com o nome informado");
//			return null; // optional?
//		}
		
		System.out.print("digite sua senha: ");
		String password = scanner.nextLine();		
		if (!profile.getPassword().equals(password) ) {
			throw new InvalidPasswordException();
		}

		return profile;		
	}
	
	private static Profile getProfileFromUsername(String username) {		
		for (Profile profile : profiles) {
			if (profile.getUsername().equals(username)) {
				return profile;
			}
		}
		
		throw new UserNotFoundException();
	}
	
	private static void UserMenu(Profile profile) {
		System.out.println("----------");
		System.out.println("Bem-vindo, " + profile.getName() + ".");
		
		while (true) {
			System.out.println("escolha uma opção:");
			System.out.println("\"P\" para postar");
			System.out.println("\"T\" para timeline");
			System.out.println("\"S\" para sair");
			
			Scanner scanner = new Scanner(System.in);
			char option = scanner.next().charAt(0);
			
			switch (option) {
				case 'P':
					System.out.println("----------");
					profile.makeAPost();
					System.out.println("==========");
					break;
				case 'T':
					System.out.println("----------");
					profile.printPosts();
					System.out.println("==========");
					break;
				case 'S':
					System.out.println("==========");
					return;
				default:
					System.out.println("opção inválida");
					System.out.println("----------");
			}
		}		
	}
}
