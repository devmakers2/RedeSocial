package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Profile> profiles = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("escolha uma opção:");
		System.out.println("\"C\" para cadastrar-se");
		System.out.println("\"E\" para entrar");
		System.out.println("\"F\" para fechar");
		
		Scanner scanner = new Scanner(System.in);
		char option = scanner.next().charAt(0);
		
		switch (option) {
			case 'C':
				try {
					registerProfile();
				} catch (EmptyNameException e) {
					System.out.println("o nome não pode ser vazio, tente novamente");
				} catch (EmptyUsernameException e) {
					System.out.println("o login não pode ser vazio, tente novamente");
				} catch (UsernameNotAvailableException e) {
					System.out.println("o login escolhido já está sendo usado, tente novamente");
				} catch (EmptyPasswordException e) {
					System.out.println("a senha não pode ser vazia, tente novamente");
				}
				break;
			case 'E':
				try {
					Profile profile = login();
					UserMenu(profile);
				} catch (NoRegisteredUsersException e) {
					System.out.println("Não há nenhum usuário cadastrado.");
					System.out.println("Por favor efetue o cadastro antes de continuar.");
				} catch (UserNotFoundException e) {
					System.out.println("não há usuário com o nome informado");
				} catch (InvalidPasswordException e) {
					System.out.println("senha não corresponde à informada");
				}
				break;
			case 'F':
				System.out.println("até mais");
				return;
			default:
				System.out.println("opção inválida");
		}
		
		System.out.println("==========");		
		main(args);
	}

	private static void registerProfile() {
		Scanner scanner = new Scanner(System.in);		

		System.out.println("----------");
		System.out.println("por favos informe seus dados:");
		System.out.print("nome: ");
		String name = scanner.nextLine();
		if (name.isEmpty()) {
			throw new EmptyNameException();
		}
		
		System.out.print("login: ");
		String username = scanner.nextLine();
		if (username.isEmpty()) {
			throw new EmptyUsernameException();
		}
		
		if (profiles.stream().anyMatch(u -> u.getUsername().equals(username))) {
			throw new UsernameNotAvailableException();
		}
		
		System.out.print("senha: ");
		String password = scanner.nextLine();
		if (password.isEmpty()) {
			throw new EmptyPasswordException();
		}

		profiles.add(new Profile(name, username, password));
		System.out.println("cadastro realizado com sucesso");
	}
	
	private static Profile login() {  // ask user credentials, depois attempt login; get Profile If Credentials Correct ?
		System.out.println("----------");

		if (profiles.isEmpty()) {
			throw new NoRegisteredUsersException();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("digite seu login: ");
		String username = scanner.nextLine();
		
		Profile profile = getProfileFromUsername(username);
		if (profile == null) {
			throw new UserNotFoundException();
		}
		
		System.out.print("digite sua senha: ");
		String password = scanner.nextLine();		
		if (!profile.isPasswordEqualTo(password)) {
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
					profile.addAPost();
					System.out.println("==========");
					break;
				case 'T':
					System.out.println("----------");
					profile.printPosts();
					System.out.println("==========");
					break;
				case 'S':
					return;
				default:
					System.out.println("opção inválida");
					System.out.println("----------");
			}
		}
	}
}
