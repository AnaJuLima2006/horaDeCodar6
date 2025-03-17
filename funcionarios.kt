//Funcionários
//Considerando os registros de 20 funcionários, contendo os campos: matrícula,
// nome e salário, desenvolver um programa que, por meio de um menu,
// execute as seguintes etapas:
//a) Cadastrar os 20 empregados e classificar os registros por número de matrícula.
//b) Pesquisar um determinado funcionário pelo número de matrícula.
//c) Apresentar de forma ordenada (por matrícula) os registros dos trabalhadores
// que trabalham
//acima de R $ 1.000,00 .
//d) Apresentar de forma ordenada (por matrícula) os registros dos trabalhadores
// que trabalham abaixo de R $ 1.000,00.
//e) Apresentar de forma ordenada (por matrícula) os registros dos trabalhadores
// que trabalham iguais a R $ 1.000,00 .
//f) Sair do programa.

data class Funcionario(
    val matricula : Int,
    val nome : String,
    val salario : Double
)

fun main() {
    val funcionarios = mutableListOf<Funcionario>()

    do {
        println("Escolha uma opção:\n")
        println("1. Cadastrar os 20 funcionários")
        println("2. Pesquisar funcionário por matrícula")
        println("3. Mostrar funcionários com salário acima de R$ 1.000,00")
        println("4. Mostrar funcionários com salário abaixo de R$ 1.000,00")
        println("5. Mostrar funcionários com salário igual a R$ 1.000,00")
        println("6. Sair do programa\n")

        var opcao = readln().toInt()
        when (opcao) {
            1 -> cadastrarFuncionarios(funcionarios)
            2 -> pesquisarPorMatricula(funcionarios)
            3 -> mostrarFuncionariosAcimaDe1000(funcionarios)
            4 -> mostrarFuncionariosAbaixoDe1000(funcionarios)
            5 -> mostrarFuncionariosCom1000(funcionarios)
           6 -> println("Saindo do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    } while (opcao != 6)


}

//Função para ler inteiros
fun inserirInteiro(mensagem: String): Int {
    while (true) {
        println(mensagem)
        try {
            return readln().toInt()
        } catch (e: NumberFormatException) {
            println("Valor inválido. Digite um número inteiro.")
        }
    }
}

//Função para ler números reais
fun inserirDouble(mensagem: String): Double {
    while (true) {
        println(mensagem)
        try {
            return readln().toDouble()
        } catch (e: NumberFormatException) {
            println("Valor inválido. Digite um número.")
        }
    }
}

//Função para ler texto
fun inserirTexto(mensagem: String): String {
    while (true) {
        println(mensagem)
        val valor = readln().trim()
        if (valor.isNotEmpty()) {
            return valor
        } else {
            println("Este campo é obrigatório. Por favor, insira um valor.")
        }
    }
}

//Função Menu 1
fun cadastrarFuncionarios(funcionarios: MutableList<Funcionario>) {
    funcionarios.clear() // Limpa a lista antes de cadastrar novos funcionários

    for (i in 1..20) {
        println("Cadastro do funcionário $i:")
        val matricula = inserirInteiro("Digite a matrícula:")
        val nome = inserirTexto("Digite o nome:")
        val salario = inserirDouble("Digite o salário:")

        val funcionario = Funcionario(matricula, nome, salario)
        funcionarios.add(funcionario)
    }
    funcionarios.sortBy { it.matricula } // Ordena a lista por matrícula
    println("Cadastro concluído!\n")
}
//Função Menu 2
fun pesquisarPorMatricula(funcionarios: List<Funcionario>) {
    if (funcionarios.isEmpty()) {
        println("Nenhum funcionário cadastrado.\n")
        return
    }

    val matricula = inserirInteiro("Digite a matrícula do funcionário:")
    val funcionario = funcionarios.find { it.matricula == matricula }

    if (funcionario != null) {
        println("Funcionário encontrado:")
        println("Matrícula: ${funcionario.matricula}, Nome: ${funcionario.nome}, Salário: R$ ${"%.2f".format(funcionario.salario)}\n")
    } else {
        println("Funcionário com matrícula $matricula não encontrado.\n")
    }
}

//Função Menu 3
fun mostrarFuncionariosAcimaDe1000(funcionarios: List<Funcionario>) {
    val filtrados = funcionarios.filter { it.salario > 1000.0 }
    if (filtrados.isEmpty()) {
        println("Nenhum funcionário com salário acima de R$ 1.000,00.\n")
    } else {
        println("Funcionários com salário acima de R$ 1.000,00:")
        filtrados.forEach { println("Matrícula: ${it.matricula}, Nome: ${it.nome}, Salário: R$ ${"%.2f".format(it.salario)}") }
        println()
    }
}
//Função Menu 4
fun mostrarFuncionariosAbaixoDe1000(funcionarios: List<Funcionario>) {
    val filtrados = funcionarios.filter { it.salario < 1000.0 }
    if (filtrados.isEmpty()) {
        println("Nenhum funcionário com salário abaixo de R$ 1.000,00.\n")
    } else {
        println("Funcionários com salário abaixo de R$ 1.000,00:")
        filtrados.forEach { println("Matrícula: ${it.matricula}, Nome: ${it.nome}, Salário: R$ ${"%.2f".format(it.salario)}") }
        println()
    }
}
//Função Menu 5
fun mostrarFuncionariosCom1000(funcionarios: List<Funcionario>) {
    val filtrados = funcionarios.filter { it.salario == 1000.0 }
    if (filtrados.isEmpty()) {
        println("Nenhum funcionário com salário igual a R$ 1.000,00.\n")
    } else {
        println("Funcionários com salário igual a R$ 1.000,00:")
        filtrados.forEach { println("Matrícula: ${it.matricula}, Nome: ${it.nome}, Salário: R$ ${"%.2f".format(it.salario)}") }
        println()
    }
}

