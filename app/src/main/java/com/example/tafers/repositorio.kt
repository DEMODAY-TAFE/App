// app/src/main/java/com/example/tafe/repositorio.kt
package com.example.tafers

data class QuizQuestion(
    val pergunta: String,
    val alternativas: List<String>,
    val respostaCorreta: Int // índice da alternativa correta
)

data class CursoRepositorio(
    val titulo: String,
    val imagemResId: Int,
    val descricao: String,
    val categoria: String,
    val topicos: List<String>,
    val videoUrl: String? = null,
    val quiz: List<QuizQuestion>
)

object CursosRepositorio {
    val cursos = listOf(
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA AS MÃOS",
            imagemResId = R.drawable.imgmao,
            descricao = "Treinamento sobre o uso correto de luvas de proteção e equipamentos para segurança das mãos.",
            categoria = "Elétrica",
            topicos = listOf(
                "Tipos de luvas",
                "Epis específicas",
                "Procedimentos de uso",
                "Cuidados e manutenção"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o principal objetivo das luvas de proteção?",
                    alternativas = listOf("Conforto térmico", "Segurança das mãos", "Estética", "Aumentar a força"),
                    respostaCorreta = 1
                ),
                QuizQuestion(
                    pergunta = "Quando as luvas devem ser substituídas?",
                    alternativas = listOf("Quando estiverem sujas", "Quando apresentarem danos", "A cada semana", "Nunca"),
                    respostaCorreta = 1
                )
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA OS OLHOS",
            imagemResId = R.drawable.image,
            descricao = "Aprenda a utilizar óculos de proteção e equipamentos para segurança ocular.",
            categoria = "Construção",
            topicos = listOf(
                "Óculos de proteção",
                "Riscos comuns",
                "Limpeza e conservação"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o principal objetivo das luvas de proteção?",
                    alternativas = listOf("Conforto térmico", "Segurança das mãos", "Estética", "Aumentar a força"),
                    respostaCorreta = 1
                ),
                QuizQuestion(
                    pergunta = "Quando as luvas devem ser substituídas?",
                    alternativas = listOf("Quando estiverem sujas", "Quando apresentarem danos", "A cada semana", "Nunca"),
                    respostaCorreta = 1
                )
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA OS PÉS",
            imagemResId = R.drawable.logo,
            descricao = "Calçados de segurança e proteção para os pés em ambientes de trabalho.",
            categoria = "Construção",
            topicos = listOf(
                "Tipos de calçados",
                "Normas de segurança",
                "Cuidados no uso"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o principal objetivo das luvas de proteção?",
                    alternativas = listOf("Conforto térmico", "Segurança das mãos", "Estética", "Aumentar a força"),
                    respostaCorreta = 1
                ),
                QuizQuestion(
                    pergunta = "Quando as luvas devem ser substituídas?",
                    alternativas = listOf("Quando estiverem sujas", "Quando apresentarem danos", "A cada semana", "Nunca"),
                    respostaCorreta = 1
                )
            )
        ),
        CursoRepositorio(
            titulo = "TRABALHO EM ALTURA",
            imagemResId = R.drawable.logo,
            descricao = "Normas e procedimentos para trabalho em altura com segurança.",
            categoria = "Altura",
            topicos = listOf(
                "Segurança na altura",
                "Equipamentos obrigatórios",
                "Procedimentos de emergência"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o principal objetivo das luvas de proteção?",
                    alternativas = listOf("Conforto térmico", "Segurança das mãos", "Estética", "Aumentar a força"),
                    respostaCorreta = 1
                ),
                QuizQuestion(
                    pergunta = "Quando as luvas devem ser substituídas?",
                    alternativas = listOf("Quando estiverem sujas", "Quando apresentarem danos", "A cada semana", "Nunca"),
                    respostaCorreta = 1
                )
            )
        ),
        CursoRepositorio(
            titulo = "CHOQUES ELÉTRICOS",
            imagemResId = R.drawable.logo,
            descricao = "Prevenção e proteção contra choques elétricos em ambientes industriais.",
            categoria = "Elétrica",
            topicos = listOf(
                "Riscos elétricos",
                "Prevenção de acidentes",
                "Primeiros socorros"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o principal objetivo das luvas de proteção?",
                    alternativas = listOf("Conforto térmico", "Segurança das mãos", "Estética", "Aumentar a força"),
                    respostaCorreta = 1
                ),
                QuizQuestion(
                    pergunta = "Quando as luvas devem ser substituídas?",
                    alternativas = listOf("Quando estiverem sujas", "Quando apresentarem danos", "A cada semana", "Nunca"),
                    respostaCorreta = 1
                )
            )
            ),
        CursoRepositorio(
            titulo = "ESPAÇO CONFINADO",
            imagemResId = R.drawable.logo,
            descricao = "Procedimentos seguros para trabalho em espaços confinados.",
            categoria = "Outros",
            topicos = listOf(
                "Definição de espaço confinado",
                "Riscos e prevenção",
                "Equipamentos de segurança"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o principal objetivo das luvas de proteção?",
                    alternativas = listOf("Conforto térmico", "Segurança das mãos", "Estética", "Aumentar a força"),
                    respostaCorreta = 1
                ),
                QuizQuestion(
                    pergunta = "Quando as luvas devem ser substituídas?",
                    alternativas = listOf("Quando estiverem sujas", "Quando apresentarem danos", "A cada semana", "Nunca"),
                    respostaCorreta = 1
                )
            )
        )
    )
}