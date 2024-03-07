package sistema;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.Pessoa;

public class sistema {
    // ArrayList de funcionários cadastrados
    private static ArrayList<Pessoa> listaFuncionarios = new ArrayList<>();
    // Scanner para entrada do usuário
    private static Scanner scanner = new Scanner(System.in);
    // Variável para armazenar o desconto do plano de saúde
    private static double descontoPlanoSaude = 0;

    public static void main(String[] args) {
        menu(); // Método principal que exibe o menu e gerencia as operações
    }

    // Exibe o menu de opções e gerencia as operações conforme a escolha do usuário
    private static void menu() {
        int opcao;
        do {
            // Exibe o menu de opções
            System.out.println("Menu de Opções:");
            System.out.println("");
            System.out.println("1. Criar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Editar Funcionário");
            System.out.println("4. Deletar Funcionário");
            System.out.println("5. Visualizar Informações de um Funcionário");
            System.out.println("6. Calcular Salário Líquido");
            System.out.println("7. Sair");
            System.out.println("");
            opcao = verificacao(); // Solicita ao usuário a escolha de uma opção e verifica se é um número inteiro
            switch (opcao) {
                case 1:
                    criarFuncionario(); // Chama o método para criar um novo funcionário
                    break;
                case 2:
                    listarFuncionarios(); // Chama o método para listar os funcionários cadastrados
                    break;
                case 3:
                    editarFuncionario(); // Chama o método para editar as informações de um funcionário
                    break;
                case 4:
                    deletarFuncionario(); // Chama o método para deletar um funcionário
                    break;
                case 5:
                    visualizarInformacoes(); // Chama o método para visualizar as informações de um funcionário
                    break;
                case 6:
                    calcularSalarioLiquido(); // Chama o método para calcular o salário líquido de um funcionário
                    break;
                case 7:
                    System.out.println("");
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("");
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("");
            }
        } while (opcao != 7); // Repete o menu até que o usuário escolha a opção de sair
    }

    // Método para verificar se a entrada do usuário é um número inteiro válido
    private static int verificacao() {
        while (true) {
            try {
                return scanner.nextInt(); // Lê a entrada do usuário como um número inteiro
            } catch (InputMismatchException e) { // Captura a exceção se o usuário digitar algo que não seja um número inteiro
                System.out.println("");
                System.out.println("Por favor, digite um número inteiro válido.");
                System.out.println("");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }

    // Método para criar um novo funcionário com base nas informações fornecidas pelo usuário
    private static void criarFuncionario() {
        // Solicita ao usuário as informações necessárias para criar um novo funcionário
        System.out.println();
        System.out.println("Digite o nome do funcionário: ");
        String nome = scanner.next();
        
        // Verifica se já existe um funcionário com o mesmo nome
        for (Pessoa funcionario : listaFuncionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Já existe um funcionário cadastrado com esse nome. Por favor, escolha outro nome.");
                return;
            }
        }
        double salarioBruto = lerSalario("salário");
        int numeroDependentes = lerInteiroPositivo("número de dependentes");

        // Verifica se o funcionário possui plano de saúde
        System.out.println("O funcionário possui plano de saúde? (S/N): ");
        String opcaoPlanoSaude = lerOpcaoSN();
        boolean planoSaude = opcaoPlanoSaude.equalsIgnoreCase("S");

        // Se o funcionário possui plano de saúde, permite selecionar o tipo de plano
        if (planoSaude) {
            System.out.println("Escolha o plano de saúde:");
            System.out.println("");
            System.out.println("1. Básico (R$ 100,00)");
            System.out.println("2. Premium (R$ 250,00)");
            System.out.println("3. Platinum Premium Plus (R$ 500,00)");
            int opcaoPlanoSaudeEscolhido = verificacao();
            switch (opcaoPlanoSaudeEscolhido) {
                case 1:
                    descontoPlanoSaude = 100;
                    break;
                case 2:
                    descontoPlanoSaude = 250;
                    break;
                case 3:
                    descontoPlanoSaude = 500;
                    break;
                default:
                    System.out.println("Opção inválida. O plano de saúde será definido como Básico.");
                    descontoPlanoSaude = 100;
            }
            System.out.println("");
        }


        // Verifica se o funcionário possui vale-refeição
        System.out.println("O funcionário possui vale-refeição? (S/N): ");
        String opcaoVr = lerOpcaoSN();
        boolean vr = opcaoVr.equalsIgnoreCase("S");

        // Verifica se o funcionário possui vale-alimentação
        System.out.println("O funcionário possui vale-alimentação? (S/N): ");
        String opcaoVa = lerOpcaoSN();
        boolean va = opcaoVa.equalsIgnoreCase("S");
      
        // Verifica se o funcionário possui vale-transporte
        System.out.println("O funcionário possui vale-transporte? (S/N): ");
        String opcaoVt = lerOpcaoSN();
        boolean vt = opcaoVt.equalsIgnoreCase("S");

        // Adiciona o funcionário à lista
        listaFuncionarios.add(new Pessoa(nome, salarioBruto, numeroDependentes, vr, va, vt, planoSaude));
        System.out.println("Funcionário criado com sucesso!");
        System.out.println("");
    
    }

    // Método para ler o salário do funcionário
    private static double lerSalario(String tipo) {
        double salario;
        while (true) {
            try {
                System.out.println("Digite o " + tipo + " do funcionário: ");
                salario = scanner.nextDouble(); // Lê o salário do funcionário
                if (salario < 0) {
                    System.out.println(tipo + " inválido.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um valor numérico para o " + tipo + ".");
                scanner.next();
            }
        }
        return salario;
    }

    // Método para ler um número inteiro positivo
    private static int lerInteiroPositivo(String tipo) {
        int numero;
        while (true) {
            try {
                System.out.println("Digite o " + tipo + " do funcionário: ");
                numero = scanner.nextInt(); // Lê um número inteiro positivo
                if (numero < 0) {
                    System.out.println("O " + tipo + " deve ser um número positivo.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um valor numérico para o " + tipo + ".");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
        return numero;
    }
    // Método para ler uma opção S/N do usuário
    private static String lerOpcaoSN() {
        while (true) {
            String opcao = scanner.next();
            if (opcao.equalsIgnoreCase("S") || opcao.equalsIgnoreCase("N")) {
                return opcao;
            } else {
                System.out.println("");
                System.out.print("Por favor, digite uma opção válida (S/N): ");
            }
        }
    }

    // Método para listar os funcionários cadastrados
    private static void listarFuncionarios() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        } else {
            for (Pessoa funcionario : listaFuncionarios) {
                System.out.println("");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Salário Bruto: " + funcionario.getSalarioBruto());
                System.out.println("Número de Dependentes: " + funcionario.getNumeroDependentes());
                System.out.println("Plano de Saúde: " + (funcionario.getPlanoDeSaude() ? "Sim" : "Não"));
                System.out.println("VR: " + (funcionario.getVr() ? "Sim" : "Não"));
                System.out.println("VA: " + (funcionario.getVa() ? "Sim" : "Não"));
                System.out.println("VT: " + (funcionario.getVt() ? "Sim" : "Não"));
                System.out.println("");
            }
        }
    }

 // Método para editar as informações de um funcionário
    private static void editarFuncionario() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para editar.");
            return;
        }

        do {
            System.out.println("Funcionários:");
            for (Pessoa funcionario : listaFuncionarios) {
                System.out.println(funcionario.getNome());
            }
            System.out.println();
            System.out.println("Digite o nome do funcionário a ser editado: ");
            String nome = scanner.next();
            Pessoa funcionario = encontrarFuncionarioPorNome(nome);
            if (funcionario != null) {
                System.out.println("Deseja editar as características do funcionário? (S/N): ");
                String opcaoEditar = lerOpcaoSN();
                if (opcaoEditar.equalsIgnoreCase("S")) {
                    // Loop para editar características
                    boolean continuarEdicao = true;
                    while (continuarEdicao) {
                        // Solicita ao usuário quais características deseja editar
                        System.out.println("Quais características deseja editar?");
                        System.out.println("1. Nome");
                        System.out.println("2. Salário Bruto");
                        System.out.println("3. Número de Dependentes");
                        System.out.println("4. Plano de Saúde");
                        System.out.println("5. Benefícios (VR, VA, VT)");
                        System.out.println("0. Cancelar");
                        int opcaoCaracteristicas = verificacao();
                        switch (opcaoCaracteristicas) {
                            case 1:
                                System.out.println("Digite o novo nome do funcionário: ");
                                funcionario.setNome(scanner.next());
                                break;
                            case 2:
                                double novoSalario = lerSalario("novo salário");
                                funcionario.setSalarioBruto(novoSalario);
                                break;
                            case 3:
                                int novoNumeroDependentes = lerInteiroPositivo("novo número de dependentes");
                                funcionario.setNumeroDependentes(novoNumeroDependentes);
                                break;
                            case 4:
                                System.out.println("O funcionário possui plano de saúde? (S/N): ");
                                String opcaoPlanoSaude = lerOpcaoSN();
                                boolean planoSaude = opcaoPlanoSaude.equalsIgnoreCase("S");
                                funcionario.setPlanoDeSaude(planoSaude);
                                if (planoSaude) {
                                    // Se o funcionário possui plano de saúde, permite selecionar o tipo de plano
                                    System.out.println("Escolha o plano de saúde:");
                                    System.out.println("1. Básico (R$ 100,00)");
                                    System.out.println("2. Premium (R$ 250,00)");
                                    System.out.println("3. Platinum Premium Plus (R$ 500,00)");
                                    int opcaoPlanoSaudeEscolhido = verificacao();
                                    switch (opcaoPlanoSaudeEscolhido) {
                                        case 1:
                                            descontoPlanoSaude = 100;
                                            break;
                                        case 2:
                                            descontoPlanoSaude = 250;
                                            break;
                                        case 3:
                                            descontoPlanoSaude = 500;
                                            break;
                                        default:
                                            System.out.println("Opção inválida. O plano de saúde será definido como Básico.");
                                            descontoPlanoSaude = 100;
                                    }
                                }
                                break;
                            case 5:
                                // Edita os benefícios do funcionário
                                System.out.println("O funcionário possui vale-refeição? (S/N): ");
                                String opcaoVr = lerOpcaoSN();
                                funcionario.setVr(opcaoVr.equalsIgnoreCase("S"));

                                System.out.println("O funcionário possui vale-alimentação? (S/N): ");
                                String opcaoVa = lerOpcaoSN();
                                funcionario.setVa(opcaoVa.equalsIgnoreCase("S"));

                                System.out.println("O funcionário possui vale-transporte? (S/N): ");
                                String opcaoVt = lerOpcaoSN();
                                funcionario.setVt(opcaoVt.equalsIgnoreCase("S"));
                                break;
                            case 0:
                                continuarEdicao = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }
                    System.out.println("Características editadas com sucesso!");
                } else {
                    System.out.println("Edição de características cancelada.");
                }
            } else {
                System.out.println("Funcionário não encontrado.");
            }
            System.out.println("Deseja editar outro funcionário? (S/N): ");
        } while (lerOpcaoSN().equalsIgnoreCase("S"));
    }
    
    // Método para deletar um funcionário da lista
    private static void deletarFuncionario() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para deletar.");
            return;
        }
        System.out.println("funcionarios: ");
        for (Pessoa funcionario : listaFuncionarios) {
            System.out.println(funcionario.getNome());
        }
        System.out.println();
        System.out.println("Digite o nome do funcionário a ser deletado: ");
        String nome = scanner.next();
        Pessoa funcionario = encontrarFuncionarioPorNome(nome);
        if (funcionario != null) {
            listaFuncionarios.remove(funcionario);
            System.out.println("Funcionário deletado com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // Método para visualizar as informações de um funcionário
    private static void visualizarInformacoes() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para visualizar informações.");
            return;
        }
        System.out.println("funcionarios: ");
        for (Pessoa funcionario : listaFuncionarios) {
            System.out.println(funcionario.getNome());
        }
        System.out.println();
        System.out.println("Digite o nome do funcionário a ser visualizado: ");
        String nome = scanner.next();
        Pessoa funcionario = encontrarFuncionarioPorNome(nome);
        if (funcionario != null) {
            // Exibe as informações do funcionário
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salário Bruto: " + funcionario.getSalarioBruto());
            System.out.println("Número de Dependentes: " + funcionario.getNumeroDependentes());
            System.out.println("Plano de Saúde: " + (funcionario.getPlanoDeSaude() ? "Sim" : "Não"));
            System.out.println("VR: " + (funcionario.getVr() ? "Sim" : "Não"));
            System.out.println("VA: " + (funcionario.getVa() ? "Sim" : "Não"));
            System.out.println("VT: " + (funcionario.getVt() ? "Sim" : "Não"));
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // Método para calcular o salário líquido de um funcionário
    private static void calcularSalarioLiquido() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para calcular o salário líquido.");
            return;
        }
        System.out.println("funcionarios: ");
        for (Pessoa funcionario : listaFuncionarios) {
            System.out.println(funcionario.getNome());
        }
        System.out.println();
        System.out.println("Digite o nome do funcionário para calcular o salário líquido: ");
        String nome = scanner.next();
        Pessoa funcionario = encontrarFuncionarioPorNome(nome);
        if (funcionario != null) {
            // Calcula o salário líquido do funcionário com base em suas informações
            double salarioBruto = funcionario.getSalarioBruto();
            int numeroDependentes = funcionario.getNumeroDependentes();
            boolean planoDeSaude = funcionario.getPlanoDeSaude();
            double descontoTotal = 0;

            if (planoDeSaude == true) {
                descontoTotal += descontoPlanoSaude;
            }

            if (funcionario.getVr()) {
                descontoTotal += salarioBruto * 0.03;
            }
            if (funcionario.getVa()) {
                descontoTotal += salarioBruto * 0.05;
            }
            if (funcionario.getVt()) {
                descontoTotal += salarioBruto * 0.06;
            }

            descontoTotal += salarioBruto * 0.11 + salarioBruto * 0.15;
            descontoTotal -= numeroDependentes * 50;

            double salarioLiquido = salarioBruto - descontoTotal;

            // Exibe o salário líquido calculado
            System.out.println("Salário Líquido: " + salarioLiquido);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // Método auxiliar para encontrar um funcionário na lista pelo nome
    private static Pessoa encontrarFuncionarioPorNome(String nome) {
        for (Pessoa funcionario : listaFuncionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                return funcionario;
            }
        }
        return null;
    }
}	