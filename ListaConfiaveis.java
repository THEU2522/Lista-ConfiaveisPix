import java.util.Scanner;

public class ListaConfiaveis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("⚠️ [ATUALIZAÇÃO] O sistema agora reconhece o tipo da chave Pix, aceita variações de resposta (Sim/Não) e bloqueia após 3 tentativas de senha.");
        System.out.println();

        String[] confiaveis = {
            "38766509797",
            "teteu@gmail.com",
            "40028922"
        };

        String senhaCorreta = "1234";
        String chave;

        // Pedir a chave Pix até ser válida
        while (true) {
            System.out.print("Digite a chave Pix: ");
            chave = scanner.nextLine();

            if (chave.matches("\\d{11}")) {
                System.out.println("→ Tipo de chave: CPF");
                break;
            } else if (chave.matches("\\d{14}")) {
                System.out.println("→ Tipo de chave: CNPJ");
                break;
            } else if (chave.startsWith("+55") && chave.length() == 14) {
                System.out.println("→ Tipo de chave: Telefone");
                break;
            } else if (chave.contains("@")) {
                System.out.println("→ Tipo de chave: Email");
                break;
            } else if (chave.length() >= 32) {
                System.out.println("→ Tipo de chave: Aleatória");
                break;
            } else {
                System.out.println("❌ Chave inválida. Tente novamente.");
            }
        }

        // Valor da transferência
        System.out.print("Digite o valor da transferência: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // limpar o buffer

        // Tentativas de senha (máximo 3)
        int tentativas = 0;
        boolean senhaCorretaDigitada = false;

        while (tentativas < 3) {
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            if (senha.equals(senhaCorreta)) {
                senhaCorretaDigitada = true;
                break;
            } else {
                tentativas++;
                System.out.println("❌ Senha incorreta. Tentativa " + tentativas + " de 3.");
            }
        }

        if (!senhaCorretaDigitada) {
            System.out.println("🚫 Você excedeu o número de tentativas. Tente novamente mais tarde.");
            return;
        }

        // Verifica se a chave é confiável
        boolean chaveConfiavel = false;
        for (String c : confiaveis) {
            if (c.equals(chave)) {
                chaveConfiavel = true;
                break;
            }
        }

        // Se não for confiável, perguntar se deseja continuar
        if (!chaveConfiavel) {
            System.out.print("⚠️ Essa chave não está na lista de confiáveis. Deseja continuar? (S/N): ");
            String resposta = scanner.nextLine().toLowerCase();

            if (!(resposta.equals("s") || resposta.equals("sim"))) {
                System.out.println("✅ Operação cancelada.");
                return;
            }
        }

        // Confirmação final
        System.out.printf("✅ Transferência de R$ %.2f enviada para %s.%n", valor, chave);
    }
}
